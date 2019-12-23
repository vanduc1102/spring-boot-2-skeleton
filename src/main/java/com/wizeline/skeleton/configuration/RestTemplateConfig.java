package com.wizeline.skeleton.configuration;

import com.wizeline.skeleton.exception.GithubClientErrorHandler;
import com.wizeline.skeleton.interceptor.RestTemplateHeaderInterceptor;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import lombok.val;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateConfig.class);
  private static final int THIRTY_SECONDS = 30 * 1000;

  @Autowired
  private AppProperties appProperties;

  @Primary
  @Bean(name = "githubRestTemplate")
  public RestTemplate githubClientService()
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    val restTemplate =
        makeRestTemplate(
            appProperties.getGithubClientService().getEndpoint(),
            appProperties.getGithubClientService().getMaxTotal(),
            appProperties.getGithubClientService().getDefaultMaxPerRoute(),
            appProperties.getGithubClientService().getConnectTimeout(),
            appProperties.getGithubClientService().getSocketTimeout(),
            appProperties.getGithubClientService().getKeepAliveHeader());
    restTemplate.setErrorHandler(new GithubClientErrorHandler());
    return restTemplate;
  }

  @Bean
  ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
    return (response, context) -> {
      HeaderElementIterator it =
          new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
      while (it.hasNext()) {
        HeaderElement he = it.nextElement();
        String param = he.getName();
        String value = he.getValue();
        if (value != null && param.equalsIgnoreCase("timeout")) {
          try {
            return Long.parseLong(value) * 1000;
          } catch (NumberFormatException exception) {
            LOGGER.error(
                String.format("Cant not create connection keep alive %s", exception.getMessage()),
                exception);
          }
        }
      }
      return THIRTY_SECONDS;
    };
  }

  private RestTemplate makeRestTemplate(
      final String endpoint,
      final int maxTotal,
      final int maxPerRoute,
      final int connectionTimeout,
      final int socketTimeout,
      final int keepAliveHeader)
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    LOGGER.debug("MaxTotal : {}", maxTotal);
    LOGGER.debug("DefaultMaxPerRoute : {}", maxPerRoute);
    LOGGER.debug("ConnectTimeout : {}", connectionTimeout);
    LOGGER.debug("SocketTimeout : {}", socketTimeout);
    LOGGER.debug("keepAliveHeader : {}", keepAliveHeader);

    val poolingHttpClientConnectionManager =
        poolingHttpClientConnectionManager(maxTotal, maxPerRoute);

    val requestConfig =
        RequestConfig.custom()
            .setConnectTimeout(connectionTimeout)
            .setSocketTimeout(socketTimeout)
            .build();

    val closeableHttpClient =
        HttpClients.custom()
            .setConnectionManager(poolingHttpClientConnectionManager)
            .setKeepAliveStrategy(connectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();

    val clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(closeableHttpClient);

    val restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(clientHttpRequestFactory);
    restTemplate.setInterceptors(getInterceptors());
    restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(endpoint));
    return restTemplate;
  }

  private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(
      final int maxTotal, final int maxPerRoute)
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    HostnameVerifier allPassVerifier =
        (String s, SSLSession sslSession) -> true; // ignore hostname checking

    val sslContext =
        SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build(); // keystore is null, not keystore is used at all

    val csf = new SSLConnectionSocketFactory(sslContext, allPassVerifier);

    val socketFactoryRegistry =
        RegistryBuilder.<ConnectionSocketFactory>create()
            .register("https", csf)
            .register("http", new PlainConnectionSocketFactory())
            .build();

    val poolingHttpClientConnectionManager =
        new PoolingHttpClientConnectionManager(socketFactoryRegistry);

    poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
    poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
    return poolingHttpClientConnectionManager;
  }

  private List<ClientHttpRequestInterceptor> getInterceptors() {
    return Collections.singletonList(new RestTemplateHeaderInterceptor());
  }
}

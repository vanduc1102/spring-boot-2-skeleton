package com.wizeline.skeleton.configuration;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
  private final GithubClientService githubClientService = new GithubClientService();

  @Getter
  @Setter
  public static class ConfigClient {

    private int maxTotal;
    private int defaultMaxPerRoute;
    private int connectTimeout;
    private int socketTimeout;
    private int readTimeoutMs;
    private int keepAliveHeader;
    private Map<String, String> endpoints;
  }

  @Getter
  @Setter
  public static class GithubClientService extends ConfigClient {}
}

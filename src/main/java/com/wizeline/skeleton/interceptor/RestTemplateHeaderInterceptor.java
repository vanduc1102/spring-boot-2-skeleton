package com.wizeline.skeleton.interceptor;

import static com.wizeline.skeleton.constant.CommonConstant.HEADER_X_CORRELATION_ID;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RestTemplateHeaderInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    HttpHeaders headers = request.getHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");
    headers.add(HEADER_X_CORRELATION_ID, getCorrelationId());
    return execution.execute(request, body);
  }

  private HttpServletRequest getHttpServletRequest() {
    val servletRequestAttributes =
        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    return servletRequestAttributes.getRequest();
  }

  private String getCorrelationId() {
    val id = getHttpServletRequest().getHeader(HEADER_X_CORRELATION_ID);
    return StringUtils.isEmpty(id)
        ? (String) getHttpServletRequest().getAttribute(HEADER_X_CORRELATION_ID)
        : id;
  }
}

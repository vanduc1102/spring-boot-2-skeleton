package com.wizeline.skeleton.interceptor;

import static com.wizeline.skeleton.constant.CommonConstant.HEADER_X_CORRELATION_ID;
import static com.wizeline.skeleton.constant.LogEventConstant.MDC_CLIENT_IP;
import static com.wizeline.skeleton.constant.LogEventConstant.MDC_CORRELATION_ID;

import com.wizeline.skeleton.constant.Namespace;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class LoggerInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    final String transactionId = UUID.randomUUID().toString();
    saveClientIp(request);
    saveCorrelationId(request, transactionId);
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView) {
    MDC.clear();
  }

  private void saveClientIp(HttpServletRequest request) {
    String[] clientIps = StringUtils.split(request.getHeader("X-Forwarded-For"), ',');
    String clientIp = request.getRemoteAddr();
    if (clientIps != null) {
      clientIp = clientIps[0];
    }

    MDC.put(MDC_CLIENT_IP, clientIp);
  }

  private void saveCorrelationId(HttpServletRequest request, String transactionId) {
    MDC.put(MDC_CORRELATION_ID, generateCorrelationId(request, transactionId));
  }

  private String generateCorrelationId(HttpServletRequest request, String transactionId) {
    String xid = request.getHeader(HEADER_X_CORRELATION_ID);

    if (xid == null || xid.trim().length() == 0) {
      xid =
          String.format(
              "%s-%s", Namespace.SKELETON_CODE, transactionId.replace("-", "").toLowerCase());
      request.setAttribute(HEADER_X_CORRELATION_ID, xid);
    }
    return xid;
  }
}

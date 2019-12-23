package com.wizeline.skeleton.controller;

import com.wizeline.skeleton.dto.GenericResponse;
import com.wizeline.skeleton.util.ResponseBodyBuilder;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class OverrideErrorWhitelabelController implements ErrorController {

  private static final String ERROR_PATH = "/error";

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @RequestMapping(value = ERROR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericResponse<String> handleError() {
    return ResponseBodyBuilder.notFoundHandler();
  }

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }
}

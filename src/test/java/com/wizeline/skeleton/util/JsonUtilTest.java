package com.wizeline.skeleton.util;

import static org.junit.jupiter.api.Assertions.*;

import com.wizeline.skeleton.dto.AccountDto;
import org.junit.jupiter.api.Test;

class JsonUtilTest {

  @Test
  void stringify_Null() {
    String jsonAccount = JsonUtil.stringify(null);
    assertEquals("null", jsonAccount);
  }

  @Test
  void stringify_Ok() {
    AccountDto accountDto = new AccountDto();
    accountDto.setStatus("ok");
    String jsonAccount = JsonUtil.stringify(accountDto);
    assertEquals(
        "{\"id\":null,\"status\":\"ok\",\"username\":null,\"firstname\":null,\"lastname\":null,\"email\":null}",
        jsonAccount);
  }

  @Test
  void parse_Ok() {
    AccountDto accountDto = JsonUtil.parse("{\"status\":\"ok\"}", AccountDto.class);
    assertEquals("ok", accountDto.getStatus());
  }

  @Test
  void parse_Not_Ok() {
    AccountDto accountDto = JsonUtil.parse("{\"status\":\"ok}", AccountDto.class);
    assertNull(accountDto);
  }

  @Test
  void getObjectMapper() {
    assertNotNull(JsonUtil.getObjectMapper());
  }
}

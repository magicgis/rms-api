package com.rms.api.web.entity;

import java.io.Serializable;

public class HeaderData implements Serializable {
  private static final long serialVersionUID = -6812113228366603615L;
  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

package com.app.service;

import com.app.pojo.DevUser;

public interface DevService {
    DevUser doLogin(String devCode, String devPassword);
}

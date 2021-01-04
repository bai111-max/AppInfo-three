package com.app.service;

import com.app.mapper.DevMapper;
import com.app.pojo.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DevServiceImpl implements DevService {

    @Resource
    private DevMapper devMapper;

    @Override
    public DevUser doLogin(String devCode, String devPassword) {
        return devMapper.doLogin(devCode,devPassword);
    }
}

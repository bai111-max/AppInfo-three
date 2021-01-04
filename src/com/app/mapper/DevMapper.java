package com.app.mapper;

import com.app.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevMapper {
    DevUser doLogin(@Param("devCode") String devCode, @Param("devPassword") String devPassword);
}

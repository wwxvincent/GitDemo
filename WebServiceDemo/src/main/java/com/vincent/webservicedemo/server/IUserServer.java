package com.vincent.webservicedemo.server;

import com.vincent.webservicedemo.DTO.UserDto;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/27/24
 * @Description:
 */
public interface IUserServer {
    UserDto getUser(Long str);
}

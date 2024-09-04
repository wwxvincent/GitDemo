package com.vincent.webservicedemo.server.impl;

import com.vincent.webservicedemo.DTO.UserDto;
import com.vincent.webservicedemo.server.IUserServer;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/27/24
 * @Description:
 */
@Service
@WebService
public class UserServerImpl implements IUserServer {
    @Override
    public UserDto getUser(Long id) {
        return UserDto.builder().id(id).address("武汉市江岸区")
                .age(25).name("guanxi").build();
    }
}

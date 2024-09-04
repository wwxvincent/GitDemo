package com.vincent.webservicedemo.DTO;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/27/24
 * @Description:
 */

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
    private String address;
}

package com.vincent.apiclient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/15/24
 * @Description:
 */
public class whatever {

    public static void main(String[] args) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 获取当前日期
        Date today = new Date();

        // 格式化当前日期
        String formattedDate = dateFormat.format(today);

        System.out.println(formattedDate);
    }
}

package com.vincent.apiclient.allStockRealTime.dorisSearch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/15/24
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "real_time_stock_data")
public class realTimeCertainStock {
    private String code;
    private String date;
    private String recordTime;
    private String market;
    private String price;
    private String f1;
    private String f2;
    private String f3;
}

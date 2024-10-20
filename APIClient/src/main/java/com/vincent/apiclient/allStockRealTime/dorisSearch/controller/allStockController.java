package com.vincent.apiclient.allStockRealTime.dorisSearch.controller;

import com.vincent.apiclient.allStockRealTime.dorisSearch.entity.AllStockData;
import com.vincent.apiclient.allStockRealTime.dorisSearch.service.AllStockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/15/24
 * @Description:
 */
@RestController
@RequestMapping("/allStockData")
public class allStockController {

    @Autowired
    private AllStockDataService allStockService;

    @GetMapping("/getAllStock")
    public void getAllStock(){

        for(AllStockData s : allStockService.getAllStock()) {
            System.out.println(s.toString());
        }
    }
}

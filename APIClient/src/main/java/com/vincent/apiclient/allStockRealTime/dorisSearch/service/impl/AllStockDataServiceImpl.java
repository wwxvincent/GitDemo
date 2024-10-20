package com.vincent.apiclient.allStockRealTime.dorisSearch.service.impl;

import com.vincent.apiclient.allStockRealTime.dorisSearch.entity.AllStockData;
import com.vincent.apiclient.allStockRealTime.dorisSearch.mapper.allStockDataMapper;
import com.vincent.apiclient.allStockRealTime.dorisSearch.service.AllStockDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/15/24
 * @Description:
 */
public class AllStockDataServiceImpl implements AllStockDataService {

    @Autowired
    private allStockDataMapper allStockDataMapper;

    @Override
    public List<AllStockData> getAllStock() {
        return allStockDataMapper.getAllStock();
    }
}

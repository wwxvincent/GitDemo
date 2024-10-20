package com.vincent.apiclient.allStockRealTime.dorisSearch.service.impl;

import com.vincent.apiclient.allStockRealTime.dorisSearch.service.realTimeCertainStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vincent.apiclient.allStockRealTime.dorisSearch.mapper.realTimeCertainStockMapper;
/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/15/24
 * @Description:
 */
@Service
public class realTimeCertainStockServiceImpl implements realTimeCertainStockService {
    @Autowired
    private realTimeCertainStockMapper realTimeCertainStockMapper;


}

package com.vincent.apiclient.allStockRealTime.dorisSearch.mapper;

import com.vincent.apiclient.allStockRealTime.dorisSearch.entity.AllStockData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/15/24
 * @Description:
 */
@Mapper
public interface allStockDataMapper {
     List<AllStockData> getAllStock();
}

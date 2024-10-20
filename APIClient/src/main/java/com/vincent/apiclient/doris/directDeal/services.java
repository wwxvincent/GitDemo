package com.vincent.apiclient.doris.directDeal;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/19/24
 * @Description:
 */
public class services {
    private final static String ALL_STOCK_INFO = "allStockInfo";
    private final static String ALL_HISTORY_INFO = "stockHistory";
    private final static String REAL_TIME_INFO = "certainStock";
    private static int targetNum = 0;
    private static int count = 0;

    public static void main(String[] args) throws Exception {

        starter(ALL_HISTORY_INFO);
//        starter(REAL_TIME_INFO);


    }

    private static void starter(String type) {
        // 开始时间
        long startTime = System.nanoTime();

        try {
            upload(type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 结束时间
            long endTime = System.nanoTime();
            // 计算总耗时
            long duration = (endTime - startTime);
            // 将耗时从纳秒转换为秒
            double seconds = duration / 1_000_000_000.0;
            System.out.println("Total time taken: " + seconds + " seconds");
            System.out.println("Total stock num: " + targetNum);
        }
    }

    public static void upload( String type ) throws Exception {
        List<String> urlList = getUrlList(type);
        targetNum = urlList.size();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (String url : urlList) {
            executor.submit(() -> {
                try {
                    String jsonData = StockUtil.getJSONArray(StockUtil.httpRequest(url, "GET", "", type), type).toString();
                    dorisUtil.loadJson(jsonData, type);
                    synchronized (uploadToDoris.class) {
                        count++;
                        System.out.println("---------------upload count: "+count+" ---------");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
//        try {
//            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
//                executor.shutdownNow(); // 如果任务执行超时，则尝试立即关闭
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // 重新设置中断状态
//            executor.shutdownNow();
//        }
        while (!executor.isTerminated()) {
            // 等待所有任务完成
        }
    }


    public static List<String> getUrlList(String type) {
           return StockUtil.getUrlsList(StockUtil.getPatternList(), type);
    }







    public static void upload(List<String> urlList) throws Exception {
//        int countNum = 0;
//        for(String url : urlList) {
//            String jsonData = StockUtil
//
//            dorisUtil.loadJson(jsonData);
//            countNum++;
//            System.out.println("-----------------upload count: "+countNum+" ---------");
//        }
    }

    public static void uploadMultipleThread() throws Exception {
//        List<String> urlList = getUrlList();
//        stockNum = urlList.size();
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//
//        for (String url : urlList) {
//            executor.submit(() -> {
//                try {
//                    String jsonData = PullRealTimeData.getCertainStockData(url);
//                    loadJson(jsonData);
//                    synchronized (uploadToDoris.class) {
//                        count++;
//                        System.out.println("---------------upload count: " + count + " ---------");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
    }
}

package com.vincent.apiclient.doris.directDeal;



import com.vincent.apiclient.doris.directDeal.PullAllData;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.DefaultRedirectStrategy;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/14/24
 * @Description:
 */
public class uploadToDoris {
    // FE IP Address
    private final static String HOST = "192.168.2.130";
    // FE port
    private final static int PORT = 8030;
    //db name
    private final static String DATABASE = "doris_db";
    //table name
    private final static String TABLE = "real_time_stock_data";
    // user name
    private final static String USER = "root";
    // user password
    private final static String PASSWORD = "";
    // The path of the local file to be imported
    private final static String LOAD_FILE_NAME = "";
    //http path of stream load task submission
    private final static String loadUrl = String.format("http://%s:%s/api/%s/%s/_stream_load", HOST, PORT, DATABASE, TABLE);

    private static int count = 0;
    private static int stockNum = 0;
    //构建HTTP客户端
    private final static HttpClientBuilder httpClientBuilder = HttpClients
            .custom()
            .setRedirectStrategy(new DefaultRedirectStrategy() {
                @Override
                protected boolean isRedirectable(String method) {
                    // If the connection target is FE, you need to deal with 307 redirect.
                    return true;
                }
            });


    /**
     * JSON格式的数据导入
     * @param jsonData
     * @throws Exception
     */
    public static void loadJson(String jsonData) throws Exception {
        try (CloseableHttpClient client = httpClientBuilder.build()) {
            HttpPut put = new HttpPut(loadUrl);
            put.removeHeaders(HttpHeaders.CONTENT_LENGTH);
            put.removeHeaders(HttpHeaders.TRANSFER_ENCODING);
            put.setHeader(HttpHeaders.EXPECT, "100-continue");
            put.setHeader(HttpHeaders.AUTHORIZATION, basicAuthHeader(USER, PASSWORD));
            // You can set stream load related properties in the Header, here we set label and column_separator.
            put.setHeader("label", UUID.randomUUID().toString());
            put.setHeader("column_separator", ",");
            put.setHeader("format", "json");
            // Strip outer array option 字符串导入设置；非字符串得comment掉
            put.setHeader("strip_outer_array", "true");  // 关键设置
            put.setHeader("label", UUID.randomUUID().toString());
            put.setHeader("column_separator", ",");
            put.setHeader("format", "json");
            // 设置 UPDATEMODE 为 APPEND_OR_UPDATE
            // 结合数据表把主key设置为unique，这样相同key的数据，就会覆盖，达到更新的效果
            put.setHeader("updatemode", "APPEND_OR_UPDATE");
            // Set up the import file. Here you can also use StringEntity to transfer arbitrary data.
            StringEntity entity = new StringEntity(jsonData, StandardCharsets.UTF_8);
            put.setEntity(entity);
            try (CloseableHttpResponse response = client.execute(put)) {
                String loadResult = "";
                if (response.getEntity() != null) {
                    loadResult = EntityUtils.toString(response.getEntity());
                }
                final int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new IOException(String.format("Stream load failed. status: %s load result: %s", statusCode, loadResult));
                }
                System.out.println("Get load result: " + loadResult);
            }
        }
    }

    /**
     * 封装认证信息
     * @param username
     * @param password
     * @return
     */
    private static String basicAuthHeader(String username, String password) {
        final String tobeEncode = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(tobeEncode.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encoded);
    }

    public static List<String> getUrlList() {
        List<String> pattern = PullAllData.getAllStockData();
        return pattern;
    }
    public static void getInfo(List<String> urlList) {
        System.out.println(PullRealTimeData.getCertainStockData(urlList.get(0)));
    }

    public static void upload() throws Exception{
        List<String> urlList = getUrlList();
        int countNum = 0;
        for(String url : urlList) {
//            String jsonData = PullRealTimeData.getCertainStockData(url);
            String jsonData = PullRealTimeData.getCertainStockDataAllYear(url);

            loadJson(jsonData);
            countNum++;
            System.out.println("-----------------upload count: "+countNum+" ---------");
        }
    }

    public static void uploadMultipleThread() throws Exception{
        List<String> urlList = getUrlList();
        stockNum = urlList.size();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (String url : urlList) {
            executor.submit(() -> {
                try {
                    String jsonData = PullRealTimeData.getCertainStockData(url);
                    loadJson(jsonData);
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

    public static void main(String[] args) {
        // 开始时间
        long startTime = System.nanoTime();

        try {
            uploadMultipleThread();
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
            System.out.println("Total stock num: " + stockNum);
        }
    }
}

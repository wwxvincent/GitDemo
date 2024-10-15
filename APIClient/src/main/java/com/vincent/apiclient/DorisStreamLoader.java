package com.vincent.apiclient;



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
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.io.IOException;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/14/24
 * @Description:
 */
public class DorisStreamLoader {
    // FE IP Address
    private final static String HOST = "192.168.2.130";
    // FE port
    private final static int PORT = 8030;
    //db name
    private final static String DATABASE = "doris_db";
    //table name
    private final static String TABLE = "all_stock_temp";
    private final static String TABLE_temp = "example_tbl_temp";
    // user name
    private final static String USER = "root";
    // user password
    private final static String PASSWORD = "";
    // The path of the local file to be imported
    private final static String LOAD_FILE_NAME = "";
    //http path of stream load task submission
    private final static String loadUrl = String.format("http://%s:%s/api/%s/%s/_stream_load", HOST, PORT, DATABASE, TABLE);
    private final static String loadUrl_temp = String.format("http://%s:%s/api/%s/%s/_stream_load", HOST, PORT, DATABASE, TABLE_temp);

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
     * 文件数据导入
     * @ param file
     */
//    public void load(File file) throws Exception {
//        try (CloseableHttpClient client = httpClientBuilder.build()) {
//            HttpPut put = new HttpPut(loadUrl);
//            put.removeHeaders(HttpHeaders.CONTENT_LENGTH);
//            put.removeHeaders(HttpHeaders.TRANSFER_ENCODING);
//            put.setHeader(HttpHeaders.EXPECT, "100-continue");
//            put.setHeader(HttpHeaders.AUTHORIZATION, basicAuthHeader(USER, PASSWORD));
//            // You can set stream load related properties in the Header, here we set label and column_separator.
//            put.setHeader("label", UUID.randomUUID().toString());
//            put.setHeader("column_separator", ",");
//            // Set up the import file. Here you can also use StringEntity to transfer arbitrary data.
//            FileEntity entity = new FileEntity(file);
//            put.setEntity(entity);
//            try (CloseableHttpResponse response = client.execute(put)) {
//                String loadResult = "";
//                if (response.getEntity() != null) {
//                    loadResult = EntityUtils.toString(response.getEntity());
//                }
//                final int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode != 200) {
//                    throw new IOException(String.format("Stream load failed. status: %s load result: %s", statusCode, loadResult));
//                }
//                System.out.println("Get load result: " + loadResult);
//            }
//        }
//    }

    /**
     * JSON格式的数据导入
     * @param jsonData
     * @throws Exception
     */
    public void loadJson(String jsonData) throws Exception {
        try (CloseableHttpClient client = httpClientBuilder.build()) {
//            HttpPut put = new HttpPut(loadUrl);
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
    private String basicAuthHeader(String username, String password) {
        final String tobeEncode = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(tobeEncode.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encoded);
    }

    public static String getAllStockData() {
        // 定义 API URL
        String url = "http://73.push2delay.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=0.300059&wbp2u=%7C0%7C0%7C0%7Cweb";
        String url2 = "http://8.push2delay.eastmoney.com/api/qt/clist/get?cb=jQuery112403408140944676902_1605794350039&pn=1&pz=6000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1605794350063";
        try {
            // 创建 HttpClient 实例
            HttpClient client = HttpClient.newHttpClient();

            // 创建 HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url2))
                    .GET() // GET 请求
                    .build();

            // 发送请求并获取响应
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 获取响应内容
            String responseBody = response.body();


            // 去除 JSONP 包裹
            String jsonString = responseBody.substring(responseBody.indexOf("(") + 1, responseBody.lastIndexOf(")"));

            // 解析 JSON 数据并提取 `data` 部分
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray diffArray = dataObject.getJSONArray("diff"); // 获取 diff 数组


            // 创建一个新的 JSON 对象用于存储 diff 数组
            JSONObject resultObject = new JSONObject();
            resultObject.put("diff", diffArray); // 将 diff 数组添加到新的 JSON 对象中

            // 将结果 JSON 对象转换为字符串
            String resultJsonString = resultObject.toString(4); // 4 为缩进级别

            String result = resultJsonString.replaceFirst("^\\{\\s*\"diff\":\\s*\\[", "[").replaceFirst("\\]\\s*\\}$", "]");

            return result;




        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        DorisStreamLoader loader = new DorisStreamLoader();
        //file load
        //File file = new File(LOAD_FILE_NAME);
        //loader.load(file);
        //json load
//        String jsonData = "{\"user_id\":10001,\"date\":\"2017-10-10\",\"city\":\"武汉\",\"age\":226,\"sex\":1,\"cost\":111,\"max_dwell_time\":10,\"min_dwell_time\":2}";
//        loader.loadJson(jsonData);

        loader.loadJson(getAllStockData());
    }
}

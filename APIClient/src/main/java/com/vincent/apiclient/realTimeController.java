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
public class realTimeController {
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
    public void loadJson(String jsonData) throws Exception {
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
    private String basicAuthHeader(String username, String password) {
        final String tobeEncode = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(tobeEncode.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encoded);
    }


    public static void main(String[] args) throws Exception {
        realTimeController loader = new realTimeController();

        String jasonData = realTimeStreamLoader.output();
        System.out.println(jasonData);

        loader.loadJson(jasonData);
    }
}

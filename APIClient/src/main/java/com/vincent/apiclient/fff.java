package com.vincent.apiclient;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/14/24
 * @Description:
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class fff {
    public static void main(String[] args) {
        // 定义 API URL
        String url = "http://73.push2delay.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=0.300059&wbp2u=%7C0%7C0%7C0%7Cweb";
        String url2 = "http://8.push2delay.eastmoney.com/api/qt/clist/get?cb=jQuery112403408140944676902_1605794350039&pn=1&pz=6000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1605794350063";
        try {
            // 创建 HttpClient 实例
            HttpClient client = HttpClient.newHttpClient();

            // 创建 HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET() // GET 请求
                    .build();

            // 发送请求并获取响应
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // 获取响应内容
            String responseBody = response.body();
            System.out.println(responseBody);
//            System.out.println(jsonObject.getJSONObject("data"));

//
//            // 去除 JSONP 包裹
//            String jsonString = responseBody.substring(responseBody.indexOf("(") + 1, responseBody.lastIndexOf(")"));
//
//            // 解析 JSON 数据并提取 `data` 部分
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONObject dataObject = jsonObject.getJSONObject("data");
//            JSONArray diffArray = dataObject.getJSONArray("diff"); // 获取 diff 数组
//
////            // 将处理后的 JSON 内容写入本地文件
////            try (FileWriter fileWriter = new FileWriter("/Users/wenxuanwang/Desktop/diff.json")) {
////                fileWriter.write(diffArray.toString(4)); // 4 为缩进级别
////                System.out.println("JSON 数据已保存到 api_response.json 文件中。");
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//            // 创建一个新的 JSON 对象用于存储 diff 数组
//            JSONObject resultObject = new JSONObject();
//            resultObject.put("diff", diffArray); // 将 diff 数组添加到新的 JSON 对象中
//
//            // 将结果 JSON 对象转换为字符串
//            String resultJsonString = resultObject.toString(4); // 4 为缩进级别
////            String result = resultJsonString.replaceFirst("^\\{\"diff\": \\[", "").replaceFirst("\\]\\}$", "");
//
//            // 输出结果 JSON 字符串
//            System.out.println("提取的 JSON 对象：");
//
////            // 可选：将结果写入文件
////            try (FileWriter fileWriter = new FileWriter("/Users/wenxuanwang/Desktop/extracted_diff.json")) {
////                fileWriter.write(result);
////                System.out.println("提取的 JSON 数据已保存到 extracted_diff.json 文件中。");
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


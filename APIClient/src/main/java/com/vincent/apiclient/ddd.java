package com.vincent.apiclient;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/14/24
 * @Description:
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ddd {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("请输入股票代码（例如：300059）：");
//        String stockCode = scanner.nextLine().trim();

        // 确保股票代码以 "0." 开头
        String secid = "0." + "300059";

        // 构建 URL
        String urlString = "http://73.push2delay.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=" + secid + "&wbp2u=%7C0%7C0%7C0%7Cweb";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 打印返回的数据
                System.out.println("股票信息: " + response.toString());
            } else {
                System.out.println("GET请求失败，响应代码：" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

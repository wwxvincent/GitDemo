package APICatch;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/12/24
 * @Description:
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    public static void main(String[] args) {
        String apiUrl = "http://73.push2.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=0.300059&wbp2u=|0|0|0|web";

        try {
            // 创建 URL 对象
            URL url = new URL(apiUrl);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法
            connection.setRequestMethod("GET");
            // 设置请求头（可选）
            connection.setRequestProperty("Accept", "application/json");

            // 检查响应状态
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 打印获取的 JSON 数据
                System.out.println("来没来这里");
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("GET request not worked. Response Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


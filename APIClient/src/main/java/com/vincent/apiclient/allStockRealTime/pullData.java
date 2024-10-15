package com.vincent.apiclient.allStockRealTime;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/14/24
 * @Description:
 */
public class pullData {
    public static String pullData (String url) {
//        String url = "http://73.push2delay.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=0.300059&wbp2u=%7C0%7C0%7C0%7Cweb";

        JSONObject stockObject = httpRequest(url,"GET","","");
        JSONObject dataObject = stockObject.getJSONObject("data");
        String code = dataObject.getString("code");
        JSONArray details = dataObject.getJSONArray("details");
        //reassemble array to jason
        // 创建一个 JSONArray 来存储所有的 newObject
        JSONArray jsonArray = new JSONArray();
        String recordDate;
        String price = null;
        String f1;
        String f2;
        String f3;
        for (int i = 0; i < details.size(); i++) {
            String[] array = details.getString(i).split(",");
            if(array.length != 5) return null;
            recordDate = array[0];
            price = array[1];
            f1 = array[2];
            f2 = array[3];
            f3 = array[4];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 获取当前日期
            Date today = new Date();
            // 格式化当前日期
            String formattedDate = dateFormat.format(today);
            JSONObject newObject = new JSONObject();
            newObject.put("code", code); // 假设 code 是每条记录共有的
            newObject.put("sector", "0");
            newObject.put("recordDate", recordDate);
            newObject.put("Date", formattedDate);
            newObject.put("price", price);
            newObject.put("f1", f1);
            newObject.put("f2", f2);
            newObject.put("f3", f3);
            jsonArray.add(newObject);
        }
        return jsonArray.toString();
    }


    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr, String isWho) {
        String mydata = "";

        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = null;
            if(!isWho.equals("todayZS")&&!isWho.equals("sinaZS")) {
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            }else {
                inputStreamReader = new InputStreamReader(inputStream, "GBK");
            }
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null  ) {
                // 跳出后续data为null的返回值情况
                if (( str.length() > 5 && str.substring(str.length() - 5, str.length()-1).equals("null")) || str.length()<=0){
                    // 如果 str 的最后 5 个字符是 "null"，可以在这里处理（例如，可以选择跳过或记录）
                    break; // 跳过当前循环的这一轮
                }
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (ConnectException ce) {
            // ce.printStackTrace();
            System.out.println("Weixin server connection timed out");
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("http request error:{}");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
            }
        }

        if (isWho.equals("realTimeTrade")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 1);
        } else if (isWho.equals("realTimeTrade0616")) {
            mydata = buffer.toString();
        } else if (isWho.equals("realTimeMoneyCount")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 1);
        } else if (isWho.equals("fiveBuyAndSell")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 1);
        } else if (isWho.equals("stockBorad")) {
            mydata = buffer.toString();
        } else if (isWho.equals("stockInfo")) {
            /*
             * mydata = buffer.toString(); //System.out.println(mydata); mydata =
             * mydata.substring(mydata.indexOf("=") + 1, mydata.length());
             */
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 2);
        } else if (isWho.equals("stockBorad2")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("=") + 1, mydata.length());
            mydata = "{data:" + mydata + "}";
        } else if (isWho.equals("stockBorad3")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("["), mydata.indexOf("]")+1);
            //System.out.println(mydata);
            mydata = "{data:" + mydata + "}";
        }else if (isWho.equals("stockBorad4")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 2);
            mydata = "{data:" + mydata + "}";
        }else if (isWho.equals("sinaZS")) {
            mydata = buffer.toString();
//            System.out.println(mydata);

            mydata = mydata.substring(mydata.indexOf("\""), mydata.indexOf("\"",mydata.indexOf("\"")+1)+1);

            mydata = "{data:" + mydata + "}";
        } else if (isWho.equals("boardHistory")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 2);
        } else if (isWho.equals("stockBiz")) {
            mydata = buffer.toString();
        } else if (isWho.equals("todayZS")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("\""), mydata.length() - 2);
            mydata = "{\"data\":"+mydata+"\"}";
        } else if (isWho.equals("GN")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("\"diff\":")+7, mydata.length() - 3);
            mydata = "{\"data\":"+mydata+"\"}";
        } else {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1);
        }
//         System.out.println(mydata);
        // trim the mydata string
        mydata = mydata.substring(mydata.indexOf(" ") );
//        System.out.println(mydata);

        return JSONObject.fromObject(mydata);

    }
}

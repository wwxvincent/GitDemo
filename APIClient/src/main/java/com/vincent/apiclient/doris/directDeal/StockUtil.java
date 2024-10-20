package com.vincent.apiclient.doris.directDeal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 10/18/24
 * @Description:
 */
public class StockUtil {
    public static void main (String[] args) {
        String url3 ="https://push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery1124004415451619732835_1674786190165&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61&ut=7eea3edcaed734bea9cbfc24409ed989&klt=101&fqt=1&secid=1.600022&beg=0&end=20500000&_=1674786190416";

        String url2 = "http://8.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112403408140944676902_1605794350039&pn=1&pz=6000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1605794350063\n";

        String url1 = "http://73.push2.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=0.300059&wbp2u=|0|0|0|web\n";
//        JSONObject stockObject = httpRequest(url1,"GET","","certainStock");
//        JSONObject stockObject = httpRequest(url2, "GET", "", "allStockInfo");
//        JSONObject stockObject = httpRequest(url3, "GET", "", "stockHistory");
//        System.out.println(stockObject.toString());

//        System.out.println(getJSONArray(stockObject,"stockHistory"));

//        System.out.println(getUrlsList(getPatternList(), "stockHistory"));
//        System.out.println(getUrlsList(getPatternList(), "certainStock"));

        String url = getUrlsList(getPatternList(), "stockHistory").get(0);
        JSONObject obj = httpRequest(url, "GET", "", "stockHistory");
        System.out.println(getJSONArray(obj, "stockHistory"));



    }

    /**
     *
     * @param requestUrl: urls
     * @param requestMethod: GET or POST
     * @param outputStr: "" for now
     * @param isWho: "certainStock" for one certain stock all info today so far;
     *               "allStockInfo" for all stock's information;
     *               "stockHistory" for one certain stock history price;
     * @return
     */
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
//        System.out.println(buffer.toString());

        if (isWho.equals("certainStock")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf(" ") + 1);
        } else if (isWho.equals("allStockInfo")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 2);
        } else if ( isWho.equals("stockHistory") ) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 2);
        }else if (isWho.equals("realTimeTrade")) {
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
            mydata = "{data:" + mydata + "}";
        }else if (isWho.equals("stockBorad4")) {
            mydata = buffer.toString();
            mydata = mydata.substring(mydata.indexOf("(") + 1, mydata.length() - 2);
            mydata = "{data:" + mydata + "}";
        }else if (isWho.equals("sinaZS")) {
            mydata = buffer.toString();

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

        return JSONObject.fromObject(mydata);

    }

    public static JSONArray getJSONArray(JSONObject stockObject, String type) {

        JSONObject dataObject = stockObject.getJSONObject("data");

        if (type.equals("certainStock")) {

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
                if (array.length != 5) return null;
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
                newObject.put("date", formattedDate);
                newObject.put("recordTime", recordDate);
                newObject.put("market", "0");
                newObject.put("price", price);
                newObject.put("f1", f1);
                newObject.put("f2", f2);
                newObject.put("f3", f3);
                jsonArray.add(newObject);

            }
            return jsonArray;
        } else if (type.equals("allStockInfo")) {
            return dataObject.getJSONArray("diff");
        } else if (type.equals("stockHistory")) {
            String code = dataObject.getString("code");
            String market = dataObject.getString("market");
            String name = dataObject.getString("name");
            String dktotal = dataObject.getString("dktotal");
            String preKPrice = dataObject.getString("preKPrice");
            JSONArray kLines = dataObject.getJSONArray("klines");
            //reassemble array to jason
            // 创建一个 JSONArray 来存储所有的 newObject
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < kLines.size(); i++) {
                String[] array = kLines.getString(i).split(",");
                if (array.length != 11) return null;
                JSONObject newObject = new JSONObject();
                newObject.put("code", code);
                newObject.put("market", market);
                newObject.put("name", name);
                newObject.put("preKPrice", preKPrice);
                newObject.put("date", array[0]);
                newObject.put("f1", array[1]);
                newObject.put("f2", array[2]);
                newObject.put("f3", array[3]);
                newObject.put("f4", array[4]);
                newObject.put("f5", array[5]);
                newObject.put("f6", array[6]);
                newObject.put("f7", array[7]);
                newObject.put("f8", array[8]);
                newObject.put("f9", array[9]);
                newObject.put("f10", array[10]);
                jsonArray.add(newObject);
            }
            return jsonArray;
        }
        return null;
    }

    public static List<String> getUrlsList(List<String> patternList, String type) {
        if (patternList == null) return null;

        StringBuffer head = new StringBuffer();
        StringBuffer tail = new StringBuffer();
        if (type.equals("certainStock")) {
            head.append("http://73.push2delay.eastmoney.com/api/qt/stock/details/sse?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55&mpi=2000&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&pos=-0&secid=");
            tail.append("&wbp2u=%7C0%7C0%7C0%7Cweb");
        } else if (type.equals("stockHistory")) {
            head.append("https://push2his.eastmoney.com/api/qt/stock/kline/get?cb=jQuery1124004415451619732835_1674786190165&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58%2Cf59%2Cf60%2Cf61&ut=7eea3edcaed734bea9cbfc24409ed989&klt=101&fqt=1&secid=");
            tail.append("&beg=0&end=20500000&_=1674786190416");
        }

        List<String> urlsList = new ArrayList<String>();
        for (String pattern : patternList) {
            // 确保pattern不为空，然后构建URL
            if (pattern != null && !pattern.isEmpty()) {
                urlsList.add(head.toString() + pattern + tail.toString());
            }
        }

        return urlsList;
    }


    // 获取所有股票的 市场号。股票代码。 后期修改一下，暂时先用这个
    public static List<String> getPatternList() {
        List<String> pattern = PullAllData.getAllStockData();
        return pattern;
    }
}

import java.io.*;

public class unicodeToString {

    public static void main (String[] args) {

        unicodeToString u = new unicodeToString();
        String str = u.read("/Users/wenxuanwang/Desktop/cn.properties");
        System.out.println(str);
//        u.read("/Users/wenxuanwang/Desktop/cn.properties 3", "/Users/wenxuanwang/Desktop/cn.properties 2");
//        u.read("/Users/wenxuanwang/Desktop/messages_de_AT.properties", "/Users/wenxuanwang/Desktop/cn.properties 2");
//        u.read("/Users/wenxuanwang/Desktop/blank.properties", "/Users/wenxuanwang/Desktop/target.properties");

    }

    public String read(String Originalpath){
        StringBuilder strReturn = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader (new FileReader(Originalpath));
            String str = null;

            while( (str = reader.readLine()) != null) {
                    StringBuilder sbLine = new StringBuilder();
                    String[] arr = str.split("=");
                    sbLine.append(arr[0]+"=");
                    for (int i = 0; i<arr[1].length()-1; i++) {
                        if(arr[1].charAt(i)=='\\' && arr[1].charAt(i+1) =='u'){
                            String temp = arr[1].substring(i, i+6);
                            sbLine.append( replaceUniCode(temp) );
                            i+=5;
                        } else {
                            sbLine.append(arr[1].charAt(i));
                        }
                    }
                    strReturn.append(sbLine+"\n");

            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn.toString();
    }

    private String replaceUniCode (String str) {
        int codePoint = Integer.parseInt(str.substring(2), 16);
        return Character.toString(codePoint);
    }
}

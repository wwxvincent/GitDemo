////import org.apache.commons.lang.StringEscapeUtils;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//
//
//public class uniCode {
//
//    public void read(String Originalpath, String targetPath){
//        try {
//            BufferedReader reader = new BufferedReader (new FileReader(Originalpath));
//            FileWriter writer = new FileWriter(targetPath);
//            String str = null;
//
//            while(true) {
//                str = reader.readLine();
//                if( str != null) {
//                    StringBuilder sbLine = new StringBuilder();
//                    String[] arr = str.split("=");
//                    sbLine.append(arr[0]+"=");
//                    for (int i = 0; i<arr[1].length()-1; i++) {
//                        if(arr[1].charAt(i)=='\\' && arr[1].charAt(i+1) =='u'){
//                            String temp = arr[1].substring(i, i+6);
//                            sbLine.append( replaceUniCode(temp) );
//                            i+=5;
//                        } else {
//                            sbLine.append(arr[1].charAt(i));
//                        }
//                    }
//                    System.out.println(sbLine.toString());
//                    writer.write(sbLine.toString()+"\n");
//                } else {
//                    break;
//                }
//            }
//            reader.close();
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String replaceUniCode (String str) {
//        int codePoint = Integer.parseInt(str.substring(2), 16);
//        return Character.toString(codePoint);
//    }
//
//    public static void main (String[] args) {
//
//        uniCode u = new uniCode();
////        u.read("/Users/wenxuanwang/Desktop/cn.properties 3", "/Users/wenxuanwang/Desktop/cn.properties 2");
////        u.read("/Users/wenxuanwang/Desktop/messages_de_AT.properties", "/Users/wenxuanwang/Desktop/cn.properties 2");
//        u.read("/Users/wenxuanwang/Desktop/blank.properties", "/Users/wenxuanwang/Desktop/target.properties");
//
//    }
//}

import java.io.*;

public class uniCodeBulk {

    public static void main (String[] args) {

        String directoryPath = "/Users/wenxuanwang/Desktop/test";
        File directoryFile = new File(directoryPath);
        File[] javaFile = directoryFile.listFiles();
        read(javaFile);

    }

    public static void read(File[] javaFile) {

        for (File f : javaFile) {
            String filePath = f.toString();
            FileInputStream fis= null;
            InputStreamReader isr = null;
            BufferedReader raf = null;
            BufferedWriter writer = null;

            try {
                fis = new FileInputStream(filePath);
                isr = new InputStreamReader(fis,"GBK");
                raf = new BufferedReader(isr);

                String filename = f.getName();
                File tempFile = new File(f.getParentFile().getAbsolutePath() + "\\"+ filename + ".tmp");
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile),"GBK"));

                String s = null;
                String WithoutUnicodeString = null;

                while ((s = raf.readLine() ) != null) {
                    WithoutUnicodeString = ascii2Native(s);
                    System.out.println(WithoutUnicodeString);
                    writer.write(WithoutUnicodeString+"\n");
                }

                try {
                    raf.close();
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                f.delete();
                tempFile.renameTo(new File(f.getAbsolutePath()));
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String ascii2Native(String str) {
        String PREFIX_UNICODE = "\\u";
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf(PREFIX_UNICODE);
        while (index != -1) {
            sb.append(str.substring(begin,index));
            sb.append((ascii2Char(str.substring(index, index +6))));
            begin = index + 6;
            index = str.indexOf(PREFIX_UNICODE, begin);
        }
        sb.append((str.substring(begin)));
        return sb.toString();
    }
    public static char ascii2Char(String str) {
        String PREFIX_UNICODE = "\\u";
        if (str.length() != 6) {
            throw new IllegalArgumentException("aaa");
        }
        if (!PREFIX_UNICODE.equals(str.substring(0,2))) {
            throw new IllegalArgumentException("sss");
        }
        String tmp = str.substring(2,4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }
}

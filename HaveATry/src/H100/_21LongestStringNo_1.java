package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 *
 * 题目描述：
 * 给你一个字符串 s，字符串s首尾相连成一个环形 ，请你在环中找出 'o' 字符出现了偶数次最长子字符串的长度。
 * 输入：
 * looxdolx
 * 输出：
 * 7
 * 说明：
 * 最长子字符串是 "oxdolxl"，由于是首尾连接在一起的，所以最后一个 'x' 和开头的 'l'是连接在一起的，此字符串包含 2 个'o' 。
 *
 *  这题目屎爆了。
 */
public class _21LongestStringNo_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();

        solution(line);
    }

    private static void solution(String line) {
        int count = 0;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == 'o') count++;
        }
        if (count % 2 == 0) {
            System.out.println(line.length());
        } else {
            System.out.println( line.length()-1 );
        }
    }
}

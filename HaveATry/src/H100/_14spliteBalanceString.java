package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/24/24
 * @Description:
 *
 * 题目描述：
 * 均衡串定义：字符串只包含两种字符，且两种字符的个数相同。
 * 给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 * 约定字符串中只包含大写的'X'和'Y'两种字符。
 */
public class _14spliteBalanceString {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        in.close();

        solution(line);
    }

    private static void solution(String line) {
        int count = 0;
        int xCount = 0;
        int yCount = 0;
        for (int i = 0; i< line.length(); i++) {
            if(line.charAt(i) == 'X') {
                xCount++;
            } else {
                yCount++;
            }
            if (xCount == yCount) {
                count++;
            }
        }
        System.out.println(count);
    }
}

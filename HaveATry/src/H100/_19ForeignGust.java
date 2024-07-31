package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 *
 * 题目描述
 * 有位客人来自异国，在该国便用 m mm 进制计。该客人有个幸运数字 n ( n < m ) n(n<m)n(n<m) 。
 * 每次购物时，其总是喜欢计算本次支付的花费(折算为异国的价格后)中存在多少幸运数字。问：当其购买一个在我国价值 k kk 的产品时，其中包含多少幸运数字。
 * 输入描述
 * 第一行为 k , n , m
 * 其中：
 * k  表示该客人购买的物品价值（以十进制计算的价格）
 * n  表示该客人的幸运数字
 * m  表示该客人所在的国度采用的进制
 * 输出描述
 * 输出幸运数字的个数，行末无空格
 *
 * 样例1
 * 输入
 * 10 2 4
 * 1
 * 输出
 * 2
 * 说明：10  用 4  进制表示时为 22 ，同时，异国的客人的幸运数字时 2  ，故而此处输出为 2  ，表示有 2  个幸运数字。
 */
public class _19ForeignGust {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int price = in.nextInt();
        int luckyNum = in.nextInt();
        int measure = in.nextInt();
        in.close();

        solution(price, measure, luckyNum);
    }
    // 分析，就是把第一个数转化成要求的 进制 然后看这个新形式 有几个幸运数字
    private static void solution(int price, int measure, int luckyNum) {
        if(measure <= luckyNum) {System.out.println("0"); return;}
        int count = 0;
        String str = convert(price, measure);
        for(int i = 0; i < str.length(); i++) {
            int diff = str.charAt(i) - '0' - luckyNum;
            if (diff == 0) count++;
        }

        System.out.println(count);

    }

    private static String convert(int num, int target) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.insert(0,num % target);
            num = num / target;
        }

        return sb.toString();
    }
}

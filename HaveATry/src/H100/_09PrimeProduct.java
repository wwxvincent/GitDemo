package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description:
 *
 * 输入描述：
 * 一个正整数num
 * 0 < num <= 2147483647
 * 输出描述：
 * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1 -1
 * 示例1
 * 输入：
 * 15
 * 输出：
 * 3 5
 */
public class _09PrimeProduct {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        solution(num);
    }
    private static void solution (int num) {
        for (int i = 2; i * i < num; i++) {
            if (num % i == 0 && isPrime(num/i ) ) {
                if (isPrime(num/i)) {
                    System.out.println(i + " "+ num/i);
                    return;
                }
            }
        }
        System.out.println("-1 -1");
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i*i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

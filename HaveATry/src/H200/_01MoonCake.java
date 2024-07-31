package H200;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/28/24
 * @Description:
 *
 * 中秋节，公司分月饼，m个员工，买了n个月饼，m<=n，每个员工至少分1个月饼，但可以分多个，单人分到最多月饼的个数是Max1，
 * 单人分到第二多月饼个数是Max2，Max1-Max2 <= 3，单人分到第n-1多月饼个数是Max(n-1)，
 * 单人分到第n多月饼个数是Max(n)，Max(n-1) – Max(n) <= 3, 问有多少种分月饼的方法？
 *
 */
public class _01MoonCake {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numP = in.nextInt();
        int numM = in.nextInt();

        solution(numP, numM);

    }

    private static void solution(int numP, int numM) {
        int[][] dp = new int[numP+1][numM+1];
        dp[0][0] = 1; // 初始状态，没有员工，没有月饼

        for(int i = 1; i <= numP; i++) {
            for (int j = 1; j <= numM; j++) {

            }
        }

    }
}

// 0 0  0 people 0 cake 0
//

package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/28/24
 * @Description:
 *
 * 要求最大的gem 购买数量。
 * 但是要求连号购买
 */
public class _29GemMax {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        if (N == 0) { System.out.println("0"); return;}
        if (N>10 || N < 0) throw new IllegalArgumentException("number of gem error");
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            if (N>1000 || N < 0) throw new IllegalArgumentException("price of gem "+i+" error");
            arr[i] = in.nextInt();
        }
        int money = in.nextInt();
        if (N>10 || N < 0) throw new IllegalArgumentException("No, you are not rich as you thought, bro!");

        solution (N, arr, money);
    }

    private static void solution (int N, int[] arr, int money) {
        int left = 0;
        int right = 0;
        int total = 0;
        int res = 0;

        for(;right < N; right++) {
            total += arr[right];
            while ( total > money) {
                total -= arr[left];
                left++;
            }
            System.out.println("start "+left+" end "+right);
            res = Math.max(res, right - left +1);
        }

//        while (left < N ) {
//            total += arr[right];
//            if (total <= money) {
//                System.out.println("start "+left+" end "+right);
//                res = Math.max(res, right - left +1);
//                right++;
//            } else {
//                total -= arr[left];
//                left++;
//            }
//        }

        System.out.println(res);
    }
}

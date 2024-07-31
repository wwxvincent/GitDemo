package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/23/24
 * @Description:
 *
 * 输入描述：
 *
 * 输入两行数组array1、array2，每行首个数字为数组大小size(0 < size <= 100);
 * 接下来一行为正整数k
 * 0 < k <= array1.size() * array2.size()
 *
 * 输出描述：
 * 满足要求的最小和
 *
 * 输入：
 * 3 1 1 2
 * 3 1 2 3
 * 2
 * 输出：
 * 4
 *
 *
 * 这个是有点问题的，因为 他的数组是运行重复数字的，还得往回走。除了暴力，目前不太好搞
 */
public class _08minimunSumOfPair {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        int k = in.nextInt();
        in.close();
        int[] arr1 = helper(line1);
        int[] arr2 = helper(line2);

        solution(arr1, arr2, k);
    }

    private static void solution(int[] arr1, int[] arr2, int k) {
        int point1 = 0;
        int point2 = 0;
        // who's next num is smaller, than move this point
        int count = 0;
        int sum = 0;
        while (count < k) {
            System.out.println("(" +arr1[point1]+","+arr2[point2]+")");
            sum += (arr1[point1] + arr2[point2]);
            count++;
            if (point1+1 > arr1.length) {
                point2++;
            } else if (point1+2 > arr2.length) {
                point1++;
            } else if (arr1[point1+1] +arr2[point2] < arr1[point1] + arr2[point2+1]) {
                point1++;
            } else {
                point2++;
            }
        }

        System.out.println(sum);
    }

    private static int[] helper(String line) {
        String[] strArr = line.split(" ");
        int n = Integer.parseInt(strArr[0]);
        int[] arr = new int[strArr.length-1];
        for( int i = 1; i < strArr.length; i++) {
            arr[i-1] = Integer.parseInt(strArr[i]);
        }
        if(n != arr.length) { System.out.println("input error, bro!"); return null; }
        return arr;
    }
}

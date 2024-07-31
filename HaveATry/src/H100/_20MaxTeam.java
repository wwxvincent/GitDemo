package H100;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/25/24
 * @Description:
 *
 * 题目描述
 * 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为 N NN，每个团队可以由1 11 人或 2 22 人组成，且 1 11 个人只能参加 1 11 个团队，
 * 请计算出最多可以派出多少支符合要求的团队
 * 输入描述
 * 5
 * 3 1 5 7 9
 * 输出：3
 * 4
 * 第一行代表总人数，范围[1,500000]
 * 第二行数组代表每个人的能力，每个元素的取值范围为[1,500000],数组的大小范围[1,500000]
 * 第三行数值为团队要求的最低能力值，范围[1,500000]
 */
public class _20MaxTeam {
    private static void valid(int num) throws IOException {
        if (num < 1 || num > 500000) throw new IOException("error input! BRO! -- " + num);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        valid(num);
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = in.nextInt();
            valid(arr[i]);
        }
        int minimum = in.nextInt();
        valid(minimum);
        in.close();

        solution(num, arr, minimum);
    }

    private static void solution(int num, int[] arr, int minimum) {
        Arrays.sort(arr);
        int count = 0;
        int left = 0;
        int right = 0;
        for(int i = arr.length-1; i > 0; i--) {
            if (arr[i] < minimum) {
                right = i;
                break;
            }
            count++;
        }
        while (left < right) {
            if (arr[left] + arr[right] > 0) {
                count++;
                left++;
                right--;
            } else {
                left++;
            }
        }

        System.out.println(count);
    }
}

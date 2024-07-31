package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/24/24
 * @Description:
 *
 *
 * 4
 * 5
 * 5 4 1 1 1
 *
 * 第一秒，执行 4个任务，a[0] 还差一个
 * 第二秒，执行 4个任务， a[0] 完成， a[1] 完成 3 个，remain 1 个
 * 第三秒，执行 2个任务， a[1] 完成， a[2] 完成
 * 第四秒，执行 1个任务， a[3] 完成
 * 第五秒，执行 1个任务， a[4] 完成
 */
public class _17GPU {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int GPU = in.nextInt();
        int num = in.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = in.nextInt();
        }
        in.close();

        solution(GPU, arr);
    }

    private static void solution(int GPU, int[] arr) {
        int remain = 0;
        int time = 0;

        for (int i = 0; i < arr.length; i++) {
//            if ( remain + arr[i] > GPU) {
//                remain = remain + arr[i] - GPU;
//            } else {
//                remain = 0;
//            }
            remain = remain + arr[i] > GPU ? remain = remain + arr[i] - GPU : 0;
            time++;
        }
        while ( remain > 0) {
            remain =- GPU;
            time++;
        }

        System.out.println(time);
    }
}

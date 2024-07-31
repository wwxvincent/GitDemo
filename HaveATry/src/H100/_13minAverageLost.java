package H100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/24/24
 * @Description:
 * 输入描述：
 * 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(" ")分隔，minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
 * 输出描述：
 * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(" ")拼接，多个下标对按下标从小到大排序。
 * 示例1
 *
 * 输入：
 * 1
 * 0 1 2 3 4
 * 输出：
 * 0-2
 * 说明：
 * A、输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
 *
 * B、前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
 *
 */
public class _13minAverageLost {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int ave = in.nextInt();
        in.nextLine(); // 读取并忽略掉此行后面的 转行符
        String line = in.nextLine();
        in.close();

        String[] strArr = line.split(" ");
        int[] numArr = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++) {
            numArr[i] = Integer.parseInt(strArr[i]);
        }

        solution( ave, numArr);
    }

    private static void solution(int avg, int[] arr) {
        int n = arr.length;
        int[] a = new int[n+1];
        int[] sum = new int[n+1];

        int min = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            a[i] = arr[i-1];
            sum[i] = sum[i-1] + a[i];
            min = Math.min(min, a[i]);
        }
        if (min > avg) {
            System.out.println("NULL");
            return;
        }

        List<int[]> res = new ArrayList<>();

        for (int len = n; len >= 1; len--) {
            for (int left = 1, right = len; right <= n; left++, right++) {
                int SUM = sum[right] - sum[left-1];
                if ( SUM <= avg * len) {
                    res.add(new int[]{left, right});
                }
            }
            if (res.size() > 0) break;  // 因为是从最长往小调整，所以有匹配的，就不再找小的，直接跳出
        }

        for (int[] r : res) {
            System.out.print((r[0]-1) + "-" + (r[1]-1) + " ");  // 输出结果，调整索引为从0开始
        }
    }
}


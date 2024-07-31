package H100;

import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/24/24
 * @Description:
 * 程序员小明打了一辆出租车去上班。出于职业敏感，他注意到这辆出租车的计费表有点问题，总是偏大。
 * 出租车司机解释说他不喜欢数字4，所以改装了计费表，任何数字位置遇到数字4就直接跳过，其余功能都正常。
 * 比如：
 * 1. 23再多一块钱就变为25；
 * 2. 39再多一块钱变为50；
 * 3. 399再多一块钱变为500；
 * 小明识破了司机的伎俩，准备利用自己的学识打败司机的阴谋。
 * 给出计费表的表面读数，返回实际产生的费用。
 * 输入描述：
 * 只有一行，数字N，表示里程表的读数。
 * (1<=N<=888888888)。
 * 输出描述：
 * 一个数字，表示实际产生的费用。以回车结束。
 * 示例1
 * 输入：
 * 5
 * 输出：
 * 4
 */
public class _16TaxiKaopu {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.close();

        solution(num);
    }

    private static void solution(int num) {
        int realNum = 0;
        int target = 0;
        while (target < num) {
            realNum++;
            target++;
            //String temp = String.valueOf(target);
            //System.out.println(temp);
            while ( (String.valueOf(target)).contains("4")) {
                target++;
            }
        }
        System.out.println(realNum);
    }


}

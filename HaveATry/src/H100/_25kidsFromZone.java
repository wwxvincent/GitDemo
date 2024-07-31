package H100;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/27/24
 * @Description:
 *
 * 幼儿园组织活动，老师布置了一个任务：每个小朋友去了解与自己同一个小区的小朋友还有几个。我们将这些数量汇总到数组garden中。
 * 请根据这些小朋友给出的信息，计算班级小朋友至少来自几个小区？
 * 输入描述：
 * 输入：garden[] = {2, 2, 3}
 * 说明：
 * garden数组长度最大为999
 * 每个小区的小朋友数量最多1000人，也就是garden[i]的范围为[0,999]
 * 输出描述：
 * 输出：7
 *
 *  2 2 3 2 2 3
 *  1 2
 *      1
 */
public class _25kidsFromZone {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        Map<Integer, Integer> map = new HashMap<>();
        int zone = 0;
        while(in.hasNextInt() && in.hasNextLine()) {
            int cur = in.nextInt();
            map.put(cur, map.getOrDefault(cur,0) + 1);
        }
        in.close();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue() / entry.getKey();
            int remain = entry.getValue() % entry.getKey();
            zone += count;

            if (count < 1 ||remain != 0) zone++;
            System.out.println("zone: " + entry.getKey()+" num: " +entry.getValue());
            System.out.println("total zone" + zone);
        }

        System.out.println(zone);
    }
}

package H100;

import java.util.*;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/22/24
 * @Description:
 */
// 第一行，小明身高 和 班级人数N
// 第二行， 其他人N个人的身高
// 按于小明升高绝对值差，从小到大排序


public class _03XiaomingHeightAbsoluteSort {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        int mingHeight = in.nextInt();
        int numPeople = in.nextInt();

        List<Integer> arr = new ArrayList<>();

        for(int i = 0; i < numPeople; i++) {
            arr.add(in.nextInt());
        }

        Collections.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int diff1 = Math.abs(mingHeight - o1);
                int diff2 = Math.abs(mingHeight - o2);

                if (diff1 == diff2) {
                    return o1 - o2;
                }
                return diff1 - diff2;
            }
        });

        for (int height: arr ) {
            System.out.println(height + " ");
        }
    }


}

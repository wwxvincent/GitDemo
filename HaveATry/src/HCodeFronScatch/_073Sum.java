package HCodeFronScatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/8/24
 * @Description:
 */
public class _073Sum {

    public static void main(String[] args) {
        int[] array= {-1, 0,1,2,-1,-4};

        List<List<Integer>> res = solution(array, 0);

        for (List<Integer> subList : res) {
            for (Integer num : subList) {
                System.out.print(num+ " ");
            }
            System.out.println();
        }


    }

    private static List<List<Integer>> solution(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            if (i > 0 && array[i-1] == array[i]) continue;
            int cur = target - array[i];
            int left = i + 1;
            int right = array.length -1;

            while (left < right) {
                if (  array[left] + array[right] == cur) {
                    res.add(Arrays.asList(array[i], array[left], array[right]));
                    while (left < right && array[left] == array[left+1]) {
                        left++;
                    }
                    while (left < right && array[right-1] == array[right]) {
                        right--;
                    }
                    left++;
                } else if (array[left] + array[right] <cur) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}

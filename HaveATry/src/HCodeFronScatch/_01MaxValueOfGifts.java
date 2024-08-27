package HCodeFronScatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/2/24
 * @Description:
 */
public class _01MaxValueOfGifts {

    public static void main(String[] args) {
        int[] arr = {20, 50, 30, 70, 60, 80, 90, 40};

        solution(arr, 100);
    }

    private static void solution(int[] arr, int target) {

        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int base = arr[i];
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int totalTemp = base + arr[left] + arr[right];
                if (totalTemp <= target) {
                    list.add(new ArrayList<>(Arrays.asList(i, left, right)));
                    // that means all the index smaller than right and larger than left will be right as well
                    for ( int j = right - 1; j > left; j--) {
                        list.add(new ArrayList<>(Arrays.asList(i, left, j)));
                    }
                    left++;
                } else if (totalTemp > target) {
                    right--;
                }
            }
        }
        for (List<Integer> listD : list) {
            for (Integer  num: listD) {
                System.out.print(arr[num] + " ");
            }
            System.out.println();
        }
    }
}

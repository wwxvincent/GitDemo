package Temp;

import java.util.*;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/8/24
 * @Description:
 *
 * input : num=[1,2,3,1,2,4,1,3,5], k=3
 *
 * output: 3
 */
public class aug82 {

    public static void main(String[] args) {

//        int[] num = {1,2,3,1,2,4,1,3,5};
        int[] num = {1,2,3,1,2,4,1,5, 7, 6, 6};
        int k = 3;

        System.out.println( solution(num, k) );
    }

    private static int solution (int[] array, int k) {
        // have a collection to store number of this element smaller than k
        List<Integer> smallList = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() < k) {
                smallList.add(entry.getValue());
            }
        }
        // now, the smallList hold all the number which small than K
        int[] smallArray = new int[smallList.size()];
        for (int j = 0; j < smallArray.length; j++) {
            smallArray[j] = smallList.get(j);
        }
        Arrays.sort(smallArray);

        int num = 0; // count how many number I can delete
        int index = 0;
        while (num < k) {
            num += smallArray[index];
            if (num == k) {
                index++;
                break;
            }
            if (num > k) {
//                index--;
                break;
            }
            index++;
        }
        return map.size() - index;
    }

}

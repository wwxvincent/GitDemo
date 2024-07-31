import java.util.*;

public class twoSum {

    public static void main(String[] args) {
        int[] nums = {2, 3,4,5,6, 7, 11,18};
        int target = 9;
        int[] result = TS2point(nums, target);
        if(result.length != 2) {
            System.out.println("no match");
        } else {
            System.out.println(nums[result[0]] +" " +nums[result[1]]);
        }

    }

//    public static int[] TT(int[] nums, int target) {
//        Arrays.sort(nums);
//        List<List<Integer>> result = new ArrayList<>();
//        for (int i = 0; i < nums.length-2;i++) {
//            if ( i > 0 && nums[i] == nums[i-1]) {
//
//            }
//        }
//    }

    public static int[] TS2point(int[] nums, int target) {
        // 定义左右指针分别指向数组的首尾
        int left = 0;
        int right = nums.length - 1;

        // 使用双指针从数组两端向中间移动
        while (left < right) {
            int sum = nums[left] + nums[right]; // 计算左右指针对应的两个数的和
            // 如果和等于目标值，则返回两个数的索引
            if (sum == target) {
                return new int[]{left, right};
            }
            // 如果和小于目标值，则将左指针向右移动
            else if (sum < target) {
                left++;
            }
            // 如果和大于目标值，则将右指针向左移动
            else {
                right--;
            }
        }
        throw new IllegalArgumentException("No match");
    }

    public static int[] TS(int[] nums,int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            int complementary = target - nums[i];
            if (map.containsKey(complementary)) {
                return new int[] {map.get(complementary), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}

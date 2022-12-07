public class RainbowSort {

    public int[] sortColors(int[] array, int k) {
        // split big question into small pieces
        // 因为排序两种最简单，那就每次的小问题就排序两种颜色

        if (array == null || array.length< 2) return array;

        int left = -1;
        int right = array.length;

        for (int round = 1; round <= k / 2 ; round++) {
            int leftColor = round;          // from 1 2
            int rightColor = k + 1 - round; // from 5 4

            int point = left + 1;
            while ( point < right) {
                if(array[point] == leftColor) {
                    left++;
                    swap(array, point, left);
                    point++;
                } else if (array[point] == rightColor) {
                    right--;
                    swap(array, point, right);
                } else {
                    point++;
                }
            }
        }
        for(Integer i : array) {
            System.out.print(i + " ");
        }
        return array;
    }

    private void swap(int[] array, int point, int left) {
        int temp = array[point];
        array[point] = array[left];
        array[left] = temp;
    }

    public static void main(String[] args) {
        RainbowSort r = new RainbowSort();
        int[] array = {1,2,3,3,4,2,1,1,2,3,2,5,4,3,1};
        System.out.println("ha");
        System.out.println(r.sortColors(array, 5).toString());
        System.out.println("done");
    }
}

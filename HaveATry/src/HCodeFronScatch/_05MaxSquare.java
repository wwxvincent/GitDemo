package HCodeFronScatch;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/6/24
 * @Description:
 */
public class _05MaxSquare {

    public static void main (String[] args) {
        int[][] array = {
                {0, 0, 1, 0}, // 第一行
                {1, 1, 1, 0}, // 第二行
                {1, 1, 0, 0}  // 第三行
        };

        solution(array);
    }



    private static void solution(int[][] array) {
        int maxArea = 0;

        int[][] dp = new int[array.length+1][array[0].length + 1];
        // dp[i][j] store the maximal side of the square util array[i-1][j-1]

        for (int i = 1; i <= array.length; i++) {
            for (int j = 1; j <= array[0].length; j++) {
                //check the array is 1 or 0
                if (array[i-1][j-1] == 1) {
                    dp[i][j] = Math.min( Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1] ) + 1;
                    maxArea = Math.max(maxArea, dp[i][j]);
                }
            }
        }

        System.out.println(maxArea);
    }

}

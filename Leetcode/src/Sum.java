public class Sum {


    public int sum (int[] arr){
        int[] dp = new int[arr.length];
        int max = arr[0];
        dp[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
            dp[i] = Math.max( Math.max(dp[i-1] , arr[i]), dp[i-1] + arr[i]);
            //System.out.println(dp[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }

    public static void main(String[] args) {
        Sum s = new Sum();
        int[] arr = {1, 0, -2, 3, -2,4};
        System.out.println(s.sum(arr));
    }
}

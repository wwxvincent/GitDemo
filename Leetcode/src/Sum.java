public class Sum {
//   求一个array中，最大和

//    public int sum (int[] arr){
//        int[] dp = new int[arr.length];
//        int max = arr[0];
//        dp[0] = arr[0];
//        for(int i = 1; i < arr.length; i++) {
//            dp[i] = Math.max( Math.max(dp[i-1] , arr[i]), dp[i-1] + arr[i]);
//            //System.out.println(dp[i]);
//            max = Math.max(dp[i], max);
//        }
//
//        return max;
//    }

    //    0   1   2  3  4   5
    //dp  -1  0   0  3  3
    //    -1  0  -2  3  -2  4
    //    -1   比较 -1大 还是 0 大， 还是 -1+0 大     = 0
    //dp  -1  0
    // index2       比较 dp[1]的-1大，还是 arr【2】的-2大， 还是之前的 dp【1】+arr【2】大；   依旧是0
    //dp  -1  0  0
    //index3        比较dp dp[2]的0大，还是arr[3]的3大，还是之前大dp[1]+arr[3]的3大； 位置4 的 dp[3] = 3
    //index4  比较dp[3] 3 bigger, or arr[4] -2 bigger, or dp[3]+arr[3] -1 bigger; so dp[4] = 3
    //index5 dp[4] 3 bigger, or arr[5] 4 bigger, or dp[4]+arr[5] 7 bigger; so dp[5] = 7

    public int sum(int[] arr) {
        int[] dp = new int[ arr.length];
        dp[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
            dp[i] = Math.max (Math.max(dp[i-1], arr[i]), dp[i-1] + arr[i] );
        }
        return dp[arr.length-1];
    }

    public static void main(String[] args) {
        Sum s = new Sum();
        int[] arr = {1, -3, -5, 5, -2,4};
        System.out.println(s.sum(arr));
    }
}

public class ex {

    public static String solve(int n, int k, String str1, String str2) {
        //
        if( n == 0) {
            System.out.println("YES");
            return null;
        }
        if( k == 0) {
            System.out.println("NO");
            return null;
        }

        if (str1.charAt(n-1) == str2.charAt(k - 1) ) {
            return solve(n -1, k-1, str1, str2 );

        }
        return solve( n, k-1,str1, str2);
    }

    public static void main(String[] args) {
        String str1 = "gksrek";
        String str2 = "geeksforgeeks";
        int n = str1.length();
        int k = str2.length();

        solve(n,k,str1,str2);
    }
}

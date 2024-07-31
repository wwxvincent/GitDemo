package H200;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 7/28/24
 * @Description:
 */
public class _02FindCity {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<List<Integer>> cityGroup = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            cityGroup.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int cur = in.nextInt();
            int link = in.nextInt();
            cityGroup.get(cur).add(link);
            cityGroup.get(link).add(cur);
            // 无向图，互相加为毗邻城市
        }

        int[] dp = new int[n+5]; // 用于存储每个城市的聚焦度
        //compute every single city's p
        for (int i = 1; i <= n; i++) {
            for (int x : cityGroup.get(i) ) {
                dp[i] = Math.min(dp[i], dfs(x,i, cityGroup));
            }
        }

        StringBuffer sb = new StringBuffer();
        for( int i = 1; i <= n; i++) {

        }
    }

    //dfs，计算从某个城市开始，不经过其父城市能到达的数量
    private static int dfs(int origin, int father, List<List<Integer>> cityGroup) {
        int res =1; // include currently city
        for (int v : cityGroup.get(origin)) {
            if ( v == father) {  // if travel to it's father, skip
                continue;
            }
            res += dfs(v , origin, cityGroup);
        }
        return res;
    }
}

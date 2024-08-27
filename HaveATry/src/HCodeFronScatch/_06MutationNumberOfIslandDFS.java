package HCodeFronScatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/6/24
 * @Description:
 */
public class _06MutationNumberOfIslandDFS {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        List<String> lines = new ArrayList<>();
//        while ()


        int[][] matrix = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
            };

//        solution(matrix);
        solutionForMaxAreaIsland(matrix);
    }

    private static void solutionForMaxAreaIsland(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int maxArea = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
//                    getArea(matrix, i, j); // sink it
                    maxArea = Math.max(maxArea, getArea(matrix, i, j));
                }
            }
        }
        System.out.println(maxArea);
    }
    private static int getArea(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) {
            return 0;
        }
        matrix[i][j] = 0; // count it, then sink it
        int areaForThisIsland = 1 + getArea(matrix, i+1, j) + getArea(matrix, i-1, j) + getArea(matrix, i, j+1) + getArea(matrix, i, j-1);
        return areaForThisIsland;
    }

    private static void solution(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int num = 0;
        // find  any part of this isLand, then sink it to the sea bro
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    num++;
                    dfs(matrix, i, j); // sink it
                }
            }
        }

        System.out.println(num);


    }

    // i for the row
    // j for the col
    private static void dfs(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) {
            return;
        }
        matrix[i][j] = 0;
        dfs(matrix, i - 1, j);
        dfs(matrix, i + 1, j);
        dfs(matrix, i, j - 1);
        dfs(matrix, i, j + 1);
    }
}

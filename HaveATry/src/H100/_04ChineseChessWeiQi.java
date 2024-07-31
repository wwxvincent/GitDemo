//package HuaweiOD100;
//
//import java.util.Scanner;
//
///**
// * @Author: Vincent(Wenxuan) Wang
// * @Date: 7/22/24
// * @Description:
// */
//
//// 第一行 黑棋坐标 0 5 8 9 9 10 -》（0,5) (8,9) (9,10)
//// 第二行 白起坐标 5 0 9 9 9 8  -> (5,0) (9,9) (9,8)
//// 输出行，黑棋气口 ； 白棋气口
//public class _04ChineseChessWeiQi {
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        String[] blackArr = in.nextLine().split(" ");
//        String[] whiteArr = in.nextLine().split(" ");
//
//        in.close();
//
//        int[][] matrix = new int[18][18];
//
//        for(int i = 0; i < blackArr.length; i = i+2) {
//            int col = Integer.parseInt(blackArr[i]);
//            int row = Integer.parseInt(blackArr[i+1]);
//            if(matrix[col][row] != 0) {System.out.println("sth wrong"); return ;}
//            matrix[col][row] = 1;
//        }
//        for(int i = 0; i < whiteArr.length; i = i+2) {
//            int col = Integer.parseInt(whiteArr[i]);
//            int row = Integer.parseInt(whiteArr[i+1]);
//            if(matrix[col][row] != 0) {System.out.println("sth wrong"); return ;}
//            matrix[col][row] = 2;
//        }
//
//        // count for black
//        int QiForBlack = 0;
//
//        for(int i = 0; i < blackArr.length; i = i+2) {
//            int col = Integer.parseInt(blackArr[i]);
//            int row = Integer.parseInt(blackArr[i+1]);
//        }
//
//
//    }
//
//    private static int count(int[][] matrix, int color) {
//        int Qi = 0;
//        boolean[][] checked = new boolean[18][18];
//        for(int i = 0; i < matrix.length) {
//            for (int j = 0; j <matrix[0].length) {
//                if (matrix[i][j] == color) {
//                    Qi = count(matrix, )
//                }
//            }
//        }
//    }
//
//    private static int countForQi(int[][] matrix, int col, int row, boolean[][] checked) {
//        int Qi = 0;
//        checked[col][row] = true; // mark the point
//
//        int[] dx = {-1, 1, 0, 0};
//        int[] dy = {0, 0, -1, 1};
//
//        for (int i = 0; i < 4; i++) {
//            int nx = col + dx[i];
//            int ny = row + dy[i];
//
//            if (nx >= 0 && nx < 18 && ny >= 0 & ny < 18) {
//                if (matrix[nx][ny] != 1 && matrix[nx][ny] !=2)
//            }
//        }
//
//    }
//
//
//}
//
//

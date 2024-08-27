/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/1/24
 * @Description:
 */
public class eightQueen {

    private int size; // 棋盘大小
    private int[] positions; // 存储每一行皇后的列位置
    private int solutions; // 解的数量

    public eightQueen(int size) {
        this.size = size;
        this.positions = new int[size]; // 初始化所有位置为0
        this.solutions = 0;
    }

    // 检查当前位置是否可以放置皇后
    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 检查列和对角线是否有冲突
            if (positions[i] == col ||
                    Math.abs(positions[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    // 递归函数，放置皇后
    private void solveNQueens(int row) {
        if (row == size) {
            // 找到一个解
            solutions++;
            printSolution();
            return;
        }

        for (int i = 0; i < size; i++) {
            if (isSafe(row, i)) {
                positions[row] = i; // 放置皇后
                solveNQueens(row + 1); // 递归放置下一行的皇后
                // 回溯
            }
        }
    }

    // 打印解决方案
    private void printSolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (positions[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // 启动解决方案搜索
    public void startSolving() {
        solveNQueens(0); // 从第一行开始
        System.out.println("总共找到 " + solutions + " 个解。");
    }

    public static void main(String[] args) {
        int size = 8; // 八皇后问题
        eightQueen solver = new eightQueen(size);
        solver.startSolving();
    }
}

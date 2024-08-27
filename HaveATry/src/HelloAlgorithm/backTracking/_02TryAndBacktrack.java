package HelloAlgorithm.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/1/24
 * @Description:
 * 之所以称之为回溯算法，是因为该算法在搜索解空间时会采用“尝试”与“回退”的策略。
 * 当算法在搜索过程中遇到某个状态无法继续前进或无法得到满足条件的解时，它会撤销上一步的选择，退回到之前的状态，并尝试其他可能的选择。
 * 对于例题一，访问每个节点都代表一次“尝试”，而越过叶节点或返回父节点的 return 则表示“回退”。
 * 值得说明的是，回退并不仅仅包括函数返回。为解释这一点，我们对例题一稍作拓展。
 *
 * 在例题一代码的基础上，我们需要借助一个列表 path 记录访问过的节点路径。当访问到值为
 *  的节点时，则复制 path 并添加进结果列表 res 。遍历完成后，res 中保存的就是所有的解。
 */
public class _02TryAndBacktrack {



    public static void main (String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left17 = new TreeNode(7);
        TreeNode left13 = new TreeNode(3);
        root.setLeft(left17);
        root.setRight(left13);
        TreeNode left74 = new TreeNode(4);
        TreeNode left75 = new TreeNode(5);
        left17.setLeft(left74);
        left17.setRight(left75);
        //
        TreeNode right36 = new TreeNode(6);
        TreeNode right37 = new TreeNode(7);
        left13.setLeft(right36);
        left13.setRight(right37);


        List<List<TreeNode>> list = new ArrayList<>();
        List<TreeNode> singleLine = new ArrayList<>();
        solution(root, list, singleLine);

        System.out.println(list.size());
        for (int i = 0; i< list.size(); i++) {

            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j).value+"   ");
            }
            System.out.println();
        }
    }

    private static void solution(TreeNode root, List<List<TreeNode>> list, List<TreeNode> singleLine) {
        // dfs 到底后，从后往根加
        if (root == null) return;
        // add to this singleLine
        singleLine.add(root);
//        System.out.println(root.value);
        //check
        if(root.value == 7) {

            list.add(new ArrayList<>(singleLine));
        }
        //go left then right
        solution(root.left, list, singleLine);
        solution(root.right, list, singleLine);
        // pop out the last one
        singleLine.remove(singleLine.size()-1);

    }

}

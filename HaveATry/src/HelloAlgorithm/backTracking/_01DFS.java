package HelloAlgorithm.backTracking;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/1/24
 * @Description:
 *
 * 是一种通过穷举来解决问题的方法，它的核心思想是从一个初始状态出发，暴力搜索所有可能的解决方案，当遇到正确的解则将其记录，直到找到解或者尝试了所有可能的选择都无法找到解为止。
 *
 * 回溯算法通常采用“深度优先搜索”来遍历解空间。在“二叉树”章节中，我们提到前序、中序和后序遍历都属于深度优先搜索。接下来，我们利用前序遍历构造一个回溯问题，逐步了解回溯算法的工作原理。
 *
 * 给定一颗二叉树，搜索并记录所有值为7的节点，请返回节点列表
 */
public class _01DFS {
    public static class TreeNode {
        private int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
        public void setLeft(TreeNode left) {
            this.left = left;
        }
        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

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

        // adjust the relation order setting
//        left17.setLeft(left74);
//        left17.setRight(left75);
//        left13.setLeft(right36);
//        left13.setRight(right37);
//        root.setLeft(left17);
//        root.setRight(left13);
        List<TreeNode> list = new ArrayList<>();
        solution(root, list);

        System.out.println(list.size());
    }

    private static void solution(TreeNode root, List<TreeNode> list) {

        // if touch no leaf return
        if (root == null) return;
        System.out.println(root.value);
        //check current value
        if (root.value == 7) {
            list.add(root);
        }
        solution(root.left, list);
        solution(root.right, list);
    }

}

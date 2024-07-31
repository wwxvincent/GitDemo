import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreePathFinder {
    // 构建树的方法
    public static TreeNode buildTree(List<TreeNode> nodes) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        for (TreeNode node : nodes) {
            nodeMap.put(node.id, node);
        }

        for (TreeNode node : nodes) {
            if (nodeMap.containsKey(node.pid)) {
                nodeMap.get(node.pid).addChild(node);
            }
        }

        // 找到根节点并返回
        for (TreeNode node : nodes) {
            if (!nodeMap.containsKey(node.pid)) {
                return node;
            }
        }
        return null; // 如果没有根节点，返回null
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        // 创建节点列表（按照任意顺序）
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(new TreeNode(1, 0, "A")); // 假设根节点的pid为0或null，根据你的数据结构来调整
        nodes.add(new TreeNode(2, 1, "B"));
        nodes.add(new TreeNode(3, 1, "C"));
        nodes.add(new TreeNode(4, 2, "D"));
        nodes.add(new TreeNode(5, 3, "E"));

        // 构建树
        TreeNode root = buildTree(nodes);

        for (TreeNode node : nodes) {
            System.out.println(node.getPath());
        }


        // 遍历树并打印每个节点的路径
        //printPaths(root, "");
    }

    // 递归打印每个节点的路径
    private static void printPaths(TreeNode node, String pathSoFar) {
        if (node == null) {
            return;
        }

        // 更新当前节点的路径
        String currentPath = pathSoFar + "/" + node.name;
        System.out.println(currentPath);

        // 递归遍历子节点
        for (TreeNode child : node.children) {
            printPaths(child, currentPath);
        }
    }
}

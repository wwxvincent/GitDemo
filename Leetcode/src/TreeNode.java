import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int id;
    int pid; // parent id
    String name; // 可以是节点的名称或其他标识信息
    TreeNode parent; // 可选，如果你想在遍历过程中直接访问父节点
    List<TreeNode> children; // 子节点列表

    TreeNode(int id, int pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.children = new ArrayList<>();
    }

    // 添加子节点的方法
    public void addChild(TreeNode child) {
        child.parent = this; // 可选：设置子节点的父节点引用
        children.add(child);
    }

    // 获取节点的路径字符串，格式为 "/A/B/C"
    public String getPath() {
        if (parent == null) {
            return "/" + name; // 根节点的路径
        } else {
            return parent.getPath() + "/" + name; // 拼接父节点的路径和自己的名称
        }
    }
}


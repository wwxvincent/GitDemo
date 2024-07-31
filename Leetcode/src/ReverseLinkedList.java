class ListNode{
    int val;
    ListNode next;
    ListNode(int val) {
        this.val =val;
    }
}
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        if (head == null || head.next == null) return head;

        while(curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public static void main(String[] args) {
        // 创建一个链表：1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 打印原始链表
        System.out.println("原始链表：");
        printList(head);

        // 使用迭代法翻转链表
        ReverseLinkedList solution = new ReverseLinkedList();
        ListNode reversedHead = solution.reverseList(head);

        // 打印翻转后的链表
        System.out.println("翻转后的链表：");
        printList(reversedHead);
    }

    // 辅助函数：打印链表
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }
}

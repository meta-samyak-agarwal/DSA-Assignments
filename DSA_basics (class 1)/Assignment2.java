
// Method to detect if the linked list has a cycle
public class Assignment2 {

    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Node head = new Node(2);
        Node temp = head;
        for (int i = 0; i < 5; i++) {
            temp.next = new Node(i + 2);
            temp = temp.next;
        }

        // temp.next = head;
        boolean check = hasCycle(head);

        System.out.println("Cycle detected: " + check);

    }
}

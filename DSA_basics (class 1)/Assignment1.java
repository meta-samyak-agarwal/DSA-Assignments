
import java.util.Scanner;

class Node {

    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    Node head;

    void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    void solve(int L, int R, int N) {
        if (head == null || L > R) {
            return;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node preSublist = dummy;

        //  start of the sublist
        for (int i = 1; i < L; i++) {
            preSublist = preSublist.next;
        }

        Node sublistStart = preSublist.next;
        Node sublistEnd = sublistStart;

        //   end of the sublist
        for (int i = L; i < R; i++) {
            sublistEnd = sublistEnd.next;
        }

        Node postSublist = sublistEnd.next;     // if at the end then it will be null

        int length = R - L + 1;
        N = N % length;

        if (N == 0) {
            return;   // simple
        }
        Node KK = sublistStart;
        Node preKK = preSublist;

        for (int i = 0; i < (length - N); i++) {
            preKK = KK;
            KK = KK.next;
        }

        preSublist.next = KK;
        sublistEnd.next = sublistStart;
        preKK.next = postSublist;

        if (L == 1) {
            head = KK;
        }

    }

}

public class Assignment1 {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the number of elements in the linked list:");
            int n = sc.nextInt();

            System.out.println("Enter the elements:");
            for (int i = 0; i < n; i++) {
                int data = sc.nextInt();
                list.insert(data);
            }

            System.out.println("Enter L (start position), R (end position), and N (number of rotations):");
            int L = sc.nextInt();
            int R = sc.nextInt();
            int N = sc.nextInt();

            list.solve(L, R, N);

            System.out.println("Modified Linked List:");
            list.display();
        }

    }
}

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

   void solve(int L , int R , int N){
        Node dummy = new Node(0);
        Node start = dummy;
        Node end = dummy;

        Node left = dummy.next ;
        Node right = dummy.next;

        for(int i=1; i<L ;i++){
            left = left.next;
        }

        start = left.next;

        for(int i=1;i<R ;i++){
            right = right.next;
        }

        end = right.next;

        

        while(N > 0){

            right.next = end.next;

            end.next = start;
            left.next = end;
            solve(L, R, R);

            N--;
        }
        

   }
}

public class Assignment1 {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the linked list:");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int data = scanner.nextInt();
            list.insert(data);
        }

        System.out.println("Enter L (start position), R (end position), and N (number of rotations):");
        int L = scanner.nextInt();
        int R = scanner.nextInt();
        int N = scanner.nextInt();

        list.solve(L, R, N);

        System.out.println("Modified Linked List:");
        list.display();

        scanner.close();
    }
}
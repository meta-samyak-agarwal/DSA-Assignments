class Node {
    int data;
    Node next;
    Node(int x) {
        data = x;
        next = null;
    }
}

class Question2 {
    
    static void printList(Node curr) {
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    static Node getTail(Node cur) {
        while (cur != null && cur.next != null)
            cur = cur.next;
        return cur;
    }

    // Partitions the list taking the first element as the pivot
    static Node partition(Node head, Node tail) {
        
        // Select the first node as the pivot node
        Node pivot = head;
         
        Node pre = head;
        Node curr = head;

        // Traverse the list until you reach the node after the tail
        while (curr != tail.next) {
            
            // If current node's data is less than the pivot's data
            if (curr.data < pivot.data) {
                
                // swapping the curr and prev data
                int temp = curr.data;
                curr.data = pre.next.data;
                pre.next.data = temp;
                
                // Move 'pre' to the next node
                pre = pre.next;
            }
            
            // Move 'curr' to the next node
            curr = curr.next;
        }
        
        // Swap the pivot's data with 'pre' data(so that it come to the correct position)
        int currData = pivot.data;
        pivot.data = pre.data;
        pre.data = currData;
        
        // Return 'pre' as the new pivot
        return pre;
    }

    // Helper function for quick sort
    static void quickSortHelper(Node head, Node tail) {
      
        // Base case: if the list is empty or consists of a single node
        if (head == null || head == tail) {
            return;
        }
        
        // Call partition to find the pivot node
        Node pivot = partition(head, tail);
        
        // Recursive call for the left part of 
      	// the list (before the pivot)
        quickSortHelper(head, pivot);
        
        // Recursive call for the right part of 
      	// the list (after the pivot)
        quickSortHelper(pivot.next, tail);
    }
 
    public static void main(String[] args) {
      
        // Creating a linked list: 30 -> 3 -> 4 -> 20 -> 5
        Node head = new Node(30);
        head.next = new Node(3);
        head.next.next = new Node(4);
        head.next.next.next = new Node(20);
        head.next.next.next.next = new Node(5);


        Node tail = getTail(head);
        quickSortHelper(head, tail);

        printList(head);
    }
}

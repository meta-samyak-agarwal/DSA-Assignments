
import java.util.HashMap;
import java.util.Scanner;

class Assignment2 {

    static class PriorityQueue {
        private int currentSize;
        private int[] arr;
        private HashMap<Integer, Integer> map;

        public PriorityQueue(int size) {
            currentSize = 0;
            arr = new int[size];
            map = new HashMap<>();
        }

        // add elemet
        public void addElement(int element) {
            if (isFull()) {
                System.out.println("the pq is full");
            }
            arr[currentSize++] = element;
            map.put(element, element);
        }

        // remove element
        public int removeElement() {

            if (isEmpty()) {
                System.out.println("the pq is emoty");
                return -1;
            }

            int highestPriority = Integer.MIN_VALUE;
            int index = -1;

            for (int i = 0; i < currentSize; i++) {

                if (map.get(arr[i]) > highestPriority) {
                    highestPriority = map.get(arr[i]);
                    index = i;
                }
            }

            int removedElement = arr[index];
            map.remove(arr[index]);
            currentSize--;

            for (int i = index; i < currentSize; i++) { // fill the gap which is to be removed
                arr[i] = arr[i + 1];
            }

            return removedElement;
        }

        // isFULL
        public boolean isFull() {
            return currentSize == arr.length;
        }

        // isEmpty
        public boolean isEmpty() {
            return currentSize == 0;
        }

        public void print() {
            for (int i = 0; i < currentSize; i++) { // but inside the elemts is not sorted
                System.out.println(arr[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue pq = new PriorityQueue(1000);

        while (true) {
            System.out.println("Menu driven");
            System.out.println("1) Add the element into the pq");
            System.out.println("2) remove the element from the pq");
            System.out.println("3) print the pq");
            System.out.println("4) exit the operations");

            int x = sc.nextInt();

            switch (x) {
                case 1:
                    System.out.println("Enter the number to add");
                    int elem = sc.nextInt();

                    pq.addElement(elem);
                    break;

                case 2:
                     int remo = pq.removeElement();

                     System.out.println("the element removed is: " + remo);

                    break;


                case 3:
                    pq.print();
                     
                    break;
            
                default:
                    
                     break;
            }

        }

    }
}

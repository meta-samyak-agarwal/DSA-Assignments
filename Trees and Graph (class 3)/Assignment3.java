
import java.util.HashMap;
import java.util.Scanner;

class Assignment3 {

    static class PriorityQueue {
        private int currentSize; // options for the caption
        private String[] arr; // to store the names of the baller
        private HashMap<String, Integer> map; // used to store the baller and its balls left

        public PriorityQueue(int size) {
            currentSize = 0; // shuru me he don't have any baller until the user put in
            arr = new String[size];
            map = new HashMap<>();
        }

        // add ballers in the captain's list
        public void addElement(String playerName, int overs) {
            if (isFull()) {
                System.out.println("the pq is full");
            }

            // this is for the duplicate entry of the player , like if i want to add the
            // more overs for the same bowler
            if (!map.containsKey(playerName)) {
                arr[currentSize++] = playerName;
                map.put(playerName, overs);
            } else {
                System.out.println("Bowler already exists! Updating quota...");
                map.put(playerName, map.get(playerName) + overs);
            }
        }

        // remove element
        public String removeElement() {

            if (isEmpty()) {
                System.out.println("no ballers are left");
                return "";
            }

            int highestPriority = Integer.MIN_VALUE;
            int index = -1;

            for (int i = 0; i < currentSize; i++) {

                if (map.get(arr[i]) > highestPriority) {
                    highestPriority = map.get(arr[i]);
                    index = i;
                }
            }

            String removedElement = arr[index];
            map.put(removedElement, map.get(removedElement) - 1);

            if (map.get(removedElement) == 0) {
                map.remove(removedElement);
                for (int i = index; i < currentSize - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                currentSize--;
            }

            return removedElement;
        }

        public int getBalls(String ballerName) {
            return map.getOrDefault(ballerName, 0);
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
            for (int i = 0; i < currentSize; i++) {
                System.out.println(arr[i] + " has " + map.get(arr[i]) + " balls left.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ball = 0;

        PriorityQueue pq = new PriorityQueue(1000);

        while (true) {
            System.out.println("Menu driven");
            System.out.println("0) Add the balls virat will play");
            System.out.println("1) Add the ballers into the match");
            System.out.println("2) print the sequence the captain choose to ball the virat");
            System.out.println("3) print the ballers left with balls");
            System.out.println("4) exit the operations");

            System.out.print("Enter your choice here: ");
            int x = sc.nextInt();

            switch (x) {
                case 0:
                    System.out.println("Enter here: ");
                    ball = sc.nextInt();
                    break;
                case 1:
                    System.out.println("Enter the name of the baller");
                    String playerName = sc.next();
                    System.out.println("Enter the number of balls left");
                    int balls = sc.nextInt();

                    pq.addElement(playerName, balls);
                    break;

                case 2:
                    while (ball > 0) {

                        if (!pq.isEmpty()) {
                            String ballerName = pq.removeElement();

                            if (ballerName != null) {
                                System.out.println("Virat faces: " + ballerName);
                                ball--;
                            } else {
                                System.out.println("no ballers left to bowl");
                            }
                        }
                    }

                    break;

                case 3:
                    pq.print();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

        }

    }
}

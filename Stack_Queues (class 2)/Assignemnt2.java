 // Define Queue interface
interface Queue {
    void enqueue(int item); // Add an item to the queue
    int dequeue();          // Remove and return the front item
    boolean isEmpty();      // Check if the queue is empty
    boolean isFull();       // Check if the queue is full
}

// Implement the Queue interface using a circular array
class CircularQueue implements Queue {
    private int[] queueArray; // Array to store queue elements
    private int front;        // Points to the front of the queue
    private int rear;         // Points to the rear of the queue
    private int size;         // Maximum size of the queue
    private int count;        // Number of elements currently in the queue

    // Constructor to initialize the queue
    public CircularQueue(int size) {
        this.size = size;
        this.queueArray = new int[size];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    // Add an item to the queue
    @Override
    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot add " + item);
            return;
        }
        rear = (rear + 1) % size; // Move rear forward (wrap-around if needed)
        queueArray[rear] = item;
        count++;
        System.out.println("Enqueued: " + item);
    }

    // Remove and return the front item
    @Override
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue");
            return -1;
        }
        int dequeuedItem = queueArray[front];
        front = (front + 1) % size; // Move front forward (wrap-around if needed)
        count--;
        System.out.println("Dequeued: " + dequeuedItem);
        return dequeuedItem;
    }

    // Check if the queue is empty
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    // Check if the queue is full
    @Override
    public boolean isFull() {
        return count == size;
    }
}

// Test the CircularQueue implementation
public class Assignemnt2 {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the queue: ");
        int size = scanner.nextInt();

        CircularQueue queue = new CircularQueue(size);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Check if queue is empty");
            System.out.println("4. Check if queue is full");
            System.out.println("5. Print queue");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element to enqueue: ");
                    int item = scanner.nextInt();
                    queue.enqueue(item);
                    break;

                case 2:
                    queue.dequeue();
                    break;

                case 3:
                    System.out.println("Queue is " + (queue.isEmpty() ? "empty" : "not empty"));
                    break;

                case 4:
                    System.out.println("Queue is " + (queue.isFull() ? "full" : "not full"));
                    break;

                case 5:
                    queue.print();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

    }
}

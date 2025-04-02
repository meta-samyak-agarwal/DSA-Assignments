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
        CircularQueue queue = new CircularQueue(5); // Create a queue with size 5

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        // Try adding an element to a full queue
        queue.enqueue(60);

        queue.dequeue();
        queue.dequeue();
        queue.enqueue(60); // Add after dequeueing
        queue.dequeue();
    }
}
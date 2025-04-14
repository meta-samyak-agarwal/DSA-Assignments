import java.util.*;

// constructor for Pair
class Pair {
    int key;
    String value;

    public Pair(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

// Node class for the BST 
class Node {
    Pair data;
    Node left, right;

    Node(Pair data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// Binary Search Tree Dictionary
class BSTDictionary {
    private Node root;

    // Constructor for initializing with initial data
    public BSTDictionary(List<Pair> initialEntries) {
        for (Pair pair : initialEntries) {
            add(pair.key, pair.value);
        }
    }

    // Method to add a key-value pair
    public void add(int key, String value) {
        root = insert(root, new Pair(key, value));
    }

    private Node insert(Node root, Pair pair) {
        if (root == null) {
            return new Node(pair);
        }

        if (pair.key < root.data.key) {
            root.left = insert(root.left, pair);
        } else if (pair.key > root.data.key) {
            root.right = insert(root.right, pair);
        } else {
            root.data.value = pair.value; // Update value if key already exists
        }

        return root;
    }

    // Method to delete a node by its key
    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.data.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.data.key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            Node temp = minValueNode(root.right);
            root.data = temp.data;
            root.right = deleteNode(root.right, temp.data.key);
        }

        return root;
    }

    // inorder-successor 
    private Node minValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Method to retrieve a value by its key
    public String get(int key) {
        Node node = search(root, key);
        return node != null ? node.data.value : null;
    }

    // helper function for searching in a BST
    private Node search(Node root, int key) {
        if (root == null || root.data.key == key) {
            return root;
        }
        if (key < root.data.key) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    // Method to get all sorted items
    public List<Pair> getSortedItems() {
        List<Pair> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    // recursive function to find the inorder traversal ( left  -- data -- right ) 
    private void inorder(Node node, List<Pair> result) {
        if (node != null) {
            inorder(node.left, result);
            result.add(new Pair(node.data.key, node.data.value));
            inorder(node.right, result);
        }
    }

    // Method to get items in a range
    public List<Pair> getRange(int k1, int k2) {
        List<Pair> result = new ArrayList<>();
        rangeSearch(root, k1, k2, result);
        return result;
    }

    private void rangeSearch(Node node, int k1, int k2, List<Pair> result) {
        if (node == null) {
            return;
        }
        if (k1 < node.data.key) {
            rangeSearch(node.left, k1, k2, result);
        }
        if (k1 <= node.data.key && node.data.key <= k2) {
            result.add(new Pair(node.data.key, node.data.value));
        }
        if (node.data.key < k2) {
            rangeSearch(node.right, k1, k2, result);
        }
    }
}

public class Question1 {
    public static void main(String[] args) {
        List<Pair> initialData = new ArrayList<>();
        initialData.add(new Pair(10, "apple"));
        initialData.add(new Pair(20, "banana"));
        initialData.add(new Pair(5, "orange"));
        initialData.add(new Pair(15, "grape"));

        BSTDictionary dict = new BSTDictionary(initialData);

        dict.add(12, "kiwi");
        System.out.println("Value for key 15: " + dict.get(15));
        System.out.println("All items (sorted):");

        for (var entry : dict.getSortedItems()) {
            System.out.println(entry.key + " -> " + entry.value);
        }

        System.out.println("Range 10 to 15:");
        for (var entry : dict.getRange(10, 15)) {
            System.out.println(entry.key + " -> " + entry.value);
        }

        dict.delete(10);
        System.out.println("After deleting key 10:");
        for (var entry : dict.getSortedItems()) {
            System.out.println(entry.key + " -> " + entry.value);
        }
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Assignment2 {

    // Cache to store previously computed results
    private static Map<String, Integer> cacheMap = new HashMap<>();

    // Method to calculate the number of unique characters
    public static int calculateUniqueCharacters(String input) {
        if (cacheMap.containsKey(input)) {
            System.out.println("Result retrieved from cache.");
            return cacheMap.get(input);
        }

        // Calculate unique characters  -> (we avoid the hashset here)
        int uniqueCount = (int) input.chars().distinct().count();

        // Store the result in cache
        cacheMap.put(input, uniqueCount);
        return uniqueCount;
    }

    // Normalize the input string
    private static String normalizeString(String input) {
        return input.trim().replaceAll("\\s+", " ").toLowerCase();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Calculate unique characters in a string");
            System.out.println("2. View cached results");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Take string input and calculate unique characters
                    System.out.print("Enter a string: ");
                    String input = scanner.nextLine().toLowerCase();
                    String normalizedInput = normalizeString(input);
                    int uniqueCount = calculateUniqueCharacters(normalizedInput);

                    System.out.println("Number of unique characters: " + uniqueCount);
                    break;

                case 2:
                    // Display cachedMap content
                    System.out.println("Cached results:");
                    for (Map.Entry<String, Integer> entry : cacheMap.entrySet()) {
                        System.out.println("String: \"" + entry.getKey() + "\", Unique Characters: " + entry.getValue());
                    }
                    break;

                case 3:
                    // Exit the program
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
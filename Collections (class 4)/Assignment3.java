import java.util.Scanner;
import java.util.Stack;

public class Assignment3 {

    public static int calculateMass(String formula) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int totalMass = 0;

        while (i < formula.length()) {
            char ch = formula.charAt(i);

            if (ch == 'C') {
                stack.push(12);
                i++;
            } else if (ch == 'H') {
                stack.push(1);
                i++;
            } else if (ch == 'O') {
                stack.push(16);
                i++;
            } else if (ch == '(') {
                stack.push(-1); // Marker for opening parenthesis
                i++;
            } else if (ch == ')') {
                int groupMass = 0;
                while (stack.peek() != -1) { // Sum up everything inside the parentheses
                    groupMass += stack.pop();
                }
                stack.pop(); // Remove the marker for opening parenthesis
                i++;
                int multiplier = 0;

                // Look for the multiplier after ')'
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                stack.push(groupMass * (multiplier == 0 ? 1 : multiplier)); // Push multiplied group mass
            } else if (Character.isDigit(ch)) {
                int multiplier = 0;

                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                int lastElementMass = stack.pop();
                stack.push(lastElementMass * multiplier); // Multiply the last pushed mass
            } else {
                i++; // Skip any other characters
            }
        }

        while (!stack.isEmpty()) {
            totalMass += stack.pop(); // Sum up everything in the stack
        }

        return totalMass;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the molecular formula: ");
        String formula = scanner.nextLine();
        int mass = calculateMass(formula);
        System.out.println("The molecular mass of " + formula + " is: " + mass);

        scanner.close();

    }

}

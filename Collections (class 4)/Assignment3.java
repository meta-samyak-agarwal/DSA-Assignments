import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Assignment3 {

    public static Map<Character,Integer> map = new HashMap<>();

    public static int calculateMass(String formula) {
        map.put('C', 12);
        map.put('O', 16);
        map.put('H', 1);
        map.put('(', -1);

     

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int totalMass = 0;

        while (i < formula.length()) {
            char ch = formula.charAt(i);
            if(ch == ' ') continue;

            if (map.containsKey(ch)){
                stack.push(map.get(ch));
                i++;
            }else if (ch == ')') {

                int groupMass = 0;
                while (stack.peek() != -1) { // Sum up everything inside the parentheses
                    groupMass += stack.pop();
                }
                stack.pop(); // Remove the marker for opening parenthesis
                i++;

                stack.push(groupMass);
                 
            } else if (Character.isDigit(ch)) {
                int multiplier = 0;

                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    multiplier = multiplier * 10 + (formula.charAt(i) - '0');
                    i++;
                }
                int lastElementMass = stack.pop();
                stack.push(lastElementMass * multiplier); // Multiply the last pushed mass
            } else {
                System.out.println("You have entered the wrong formula");
                return 0;
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
        String result =  formula.replaceAll("\\s+", "");
        int mass = calculateMass(result);
        System.out.println("The molecular mass of " + result + " is: " + mass);

        scanner.close();

    }

}

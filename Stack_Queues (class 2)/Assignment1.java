
class Stack<T> { // Using generics (T represents the type)
    private int maxSize; // Maximum size of the stack
    private T[] stackArray; // Array to store elements of the stack
    private int top; // Index of the top element
    
    @SuppressWarnings("unchecked")
    public Stack(int size) {
        maxSize = size;
        stackArray = (T[]) new Object[size];
        top = -1; // Stack is initially empty
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void push(T element) {
        if (!isFull()) {
            stackArray[++top] = element;
        } else {
            throw new RuntimeException("Stack overflow");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        } else {
            throw new RuntimeException("Stack underflow");
        }
    }

    public T peek() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            throw new RuntimeException("Stack is empty");
        }
    }
}

class Assignment1 {

    
// Simplified method to determine precedence of operators
    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1; // Lowest precedence among these operators
            case "*", "/" -> 2; // Higher precedence than + and -
            case "(" -> 0;      // Parentheses do not have precedence in evaluation
            default -> -1;      // Invalid or unrecognized operator
        };
    }

    // Method to convert infix expression to postfix
    private static String infixToPostfix(String infixExpression) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>(infixExpression.length());
        String[] tokens = infixExpression.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) { // Token is an integer
                postfix.append(token).append(" ");
            } else if (token.equals("(")) { // Left parenthesis
                stack.push(token);
            } else if (token.equals(")")) { // Right parenthesis
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Pop '('
            } else { // Operator
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        // Pop remaining operators in the stack
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    // Method to evaluate postfix expression
    private static int evaluatePostfix(String postfixExpression) {
        Stack<Integer> stack = new Stack<>(postfixExpression.length());
        String[] tokens = postfixExpression.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) { // Operand
                stack.push(Integer.parseInt(token));
            } else { // Operator
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                switch (token) {
                    case "+" -> stack.push(operand1 + operand2);
                    case "-" -> stack.push(operand1 - operand2);
                    case "*" -> stack.push(operand1 * operand2);
                    case "/" -> stack.push(operand1 / operand2);
                    case "%" -> stack.push(operand1 % operand2);
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String infixExpression = "3 + 5 * ( 2 - 8 )";
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Postfix: " + postfixExpression);

        int result = evaluatePostfix(postfixExpression);
        System.out.println("Result: " + result);
    }
}
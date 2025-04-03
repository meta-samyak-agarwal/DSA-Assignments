import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

class Assignment1 {

    // Define Stack interface
    interface Stack<T> {
        void push(T x);

        T pop();

        T top();

        int size();

        boolean isEmpty();
    }

    public static class ArrayStack<T> implements Stack<T> {

        private int top;
        private T[] arr;
        private int maxSize;

        // Constructor
        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            this.arr = (T[]) new Object[maxSize];
            this.top = -1;
        }

        @Override
        public void push(T x) {
            if (top + 1 == maxSize)
                throw new StackOverflowError("Stack is full");
            arr[++top] = x;
        }

        @Override
        public T pop() {
            if (isEmpty())
                throw new EmptyStackException();
            return arr[top--];
        }

        @Override
        public T top() {
            if (isEmpty())
                throw new EmptyStackException();
            return arr[top];
        }

        @Override
        public int size() {
            return top + 1;
        }

        @Override
        public boolean isEmpty() {
            return top == -1;
        }
    }

    static int precedence(String s) {
        switch (s) {
            case "/", "*":
                return 7;
            case "+", "-":
                return 6;
            case ">", "<", ">=", "<=":
                return 5;
            case "==", "!=":
                return 4;
            case "!":
                return 3;
            case "&&":
                return 2;
            case "||":
                return 1;
            default:
                return -1;
        }
    }

    static String infixToPostfix(String infix, ArrayStack<String> st) {
        List<String> tokens = Arrays.asList(infix.split(" "));
        StringBuilder postfix = new StringBuilder();

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                postfix.append(token).append(" ");
            } else if ("(".equals(token)) {
                st.push(token);
            } else if (")".equals(token)) {
                while (!st.isEmpty() && !"(".equals(st.top())) {
                    postfix.append(st.pop()).append(" ");
                }
                st.pop(); // Remove '('
            } else {
                while (!st.isEmpty() && precedence(st.top()) >= precedence(token)) {
                    postfix.append(st.pop()).append(" ");
                }
                st.push(token);
            }
        }

        while (!st.isEmpty()) {
            postfix.append(st.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    static int evaluatePostfix(String postfix) {
        ArrayStack<Integer> stack = new ArrayStack<>(1000);
        List<String> tokens = Arrays.asList(postfix.split(" "));

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop(), a = stack.pop();
                
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                    case ">":
                        stack.push(a > b ? 1 : 0);
                        break;
                    case "<":
                        stack.push(a < b ? 1 : 0);
                        break;
                    case ">=":
                        stack.push(a >= b ? 1 : 0);
                        break;
                    case "<=":
                        stack.push(a <= b ? 1 : 0);
                        break;
                    case "==":
                        stack.push(a == b ? 1 : 0);
                        break;
                    case "!=":
                        stack.push(a != b ? 1 : 0);
                        break;
                    case "&&":
                        stack.push((a != 0 && b != 0) ? 1 : 0);
                        break;
                    case "||":
                        stack.push((a != 0 || b != 0) ? 1 : 0);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        ArrayStack<String> st = new ArrayStack<>(1000);
        String infix = "2 + 3 * ( 5 - 2 ) >= 4";

        String postfixExpression = infixToPostfix(infix, st);
        System.out.println("Postfix Expression: " + postfixExpression);

        int result = evaluatePostfix(postfixExpression);
        System.out.println("Result: " + result);
    }
}

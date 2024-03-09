import java.util.Stack;

public class EquationModel {
    private static boolean isOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("^") || operator.equals("//");
    }


    private static int getPriority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "//":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }



    public static String shiftEquation(String equation) {
        StringBuilder answer = new StringBuilder();
        Stack<String> stack = new Stack<>();
        boolean isNumber = false;
        for (char c : equation.toCharArray()) {
            String currentChar = String.valueOf(c);
            if (Character.isDigit(c) || (c == '-' && !isNumber)) {
                answer.append(currentChar);
                isNumber = true;
            } else if (isOperator(currentChar)) {
                answer.append(' ');
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(currentChar)) {
                    answer.append(stack.pop()).append(' ');
                }
                stack.push(currentChar);
                isNumber = false;
            } else if (c == '(') {
                stack.push(currentChar);
                isNumber = false;
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    answer.append(' ').append(stack.pop()).append(' ');
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                isNumber = false;
            } else if (c == ' ') {

            }
        }
        while (!stack.isEmpty()) {
            answer.append(' ').append(stack.pop()).append(' ');
        }

        return answer.toString().trim();
    }

    public static Double evaluateEquation(String expression) {
        String[] tokens = expression.split("\\s+");
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            if (isOperator(token)) {
                double b, a;
                switch (token) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a / b);
                        break;
                    case "//":
                        b = stack.pop();
                        a = stack.pop();
                        if (b == 0) {
                            throw new ArithmeticException("Error");
                        }
                        stack.push((double) ((int) a / (int) b));
                        break;
                    case "^":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(Math.pow(a, b));
                        break;
                    default:
                        throw new IllegalArgumentException("Error");
                }
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }
}

import java.util.Scanner;

public class EquationController {
    public static void main(String[] args) {
//        String example = "- 123 - 65 * (12 / 2) + 2 ^ 2";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your equation: ");
        String equation = scanner.nextLine();
        String RPNEquation = EquationModel.shiftEquation(equation);
        Double result = EquationModel.evaluateEquation(RPNEquation);
        EquationView.displayResult(equation, result);
    }
}
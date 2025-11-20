import java.util.Scanner;
import modelo.Calculator;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    try {
      Calculator calculator = new Calculator();
      System.out.print("Enter an infix expression: ");
      var infix = scanner.nextLine();
      var result = calculator.calculate(infix);
      System.out.println(infix + " = " + result);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    } finally {
      scanner.close();
    }
  }
}

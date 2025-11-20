package modelo;

import java.util.*;

public class PostfixCalculator {
  private final Stack<Integer> evaluationStack;

  public PostfixCalculator() {
    this.evaluationStack = new Stack<>();
  }

  /**
   * Evaluates an expression in postfix notation (RPN) and returns the integer result.
   *
   * <p>The expression must be tokenized by spaces, for example:
   *
   * <pre>{@code
   * "3 5 2 8 - 3 + * +"  // equivalent to 3 + 5 * (2 - 8 + 3)
   * }</pre>
   *
   * <h3>Parameters:</h3>
   *
   * <ul>
   *   <li><b>postfixExpression</b> – a postfix expression with integer operands (0, 7, 12, ...) and
   *       binary operators {@code + - * /}, all separated by one or more spaces.
   * </ul>
   *
   * <h3>Returns:</h3>
   *
   * <ul>
   *   <li>The result of evaluating the expression as an {@code int}.
   * </ul>
   *
   * <h3>Examples:</h3>
   *
   * <pre>{@code
   * evaluatePostfix("3 4 +")              // -> 7
   * evaluatePostfix("5 2 8 - 3 + *")      // -> -15
   * evaluatePostfix("12 3 / 2 * 1 +")     // -> 9
   * }</pre>
   *
   * <h3>Throws:</h3>
   *
   * <ul>
   *   <li>{@link IllegalArgumentException} if the expression is null, empty, contains unsupported
   *       tokens, or is malformed (e.g., operators with fewer than two operands, leftover
   *       operands).
   *   <li>{@link ArithmeticException} if a division by zero occurs.
   * </ul>
   */
  public int evaluatePostfix(String postfixExpression) {
    if (postfixExpression == null || postfixExpression.trim().isEmpty()) {
      throw new IllegalArgumentException("Postfix expression cannot be null or empty.");
    }

    evaluationStack.clear();
    String[] tokens = postfixExpression.trim().split("\\s+");

    for (String token : tokens) {
      if (token.isEmpty()) continue;

      if (isNonNegativeInteger(token)) {
        evaluationStack.push(Integer.valueOf(token));
        continue;
      }

      if (isOperator(token)) {
        if (evaluationStack.size() < 2) {
          throw new IllegalArgumentException("Invalid postfix: missing operands.");
        }
        int rightOperand = evaluationStack.pop();
        int leftOperand = evaluationStack.pop();
        evaluationStack.push(applyOperator(leftOperand, rightOperand, token));
        continue;
      }

      throw new IllegalArgumentException("Unsupported token in postfix: '" + token + "'.");
    }

    if (evaluationStack.size() != 1) {
      throw new IllegalArgumentException("Invalid postfix: leftover operands/operators.");
    }
    return evaluationStack.pop();
  }

  // --- helpers ---
  private boolean isNonNegativeInteger(String token) {
    return token.matches("\\d+"); // 0, 7, 12, 345, ...
  }

  private boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  }

  private int applyOperator(int leftOperand, int rightOperand, String operator) {
    return switch (operator) {
      case "+" -> leftOperand + rightOperand;
      case "-" -> leftOperand - rightOperand;
      case "*" -> leftOperand * rightOperand;
      case "/" -> {
        if (rightOperand == 0) throw new ArithmeticException("Division by zero.");
        yield leftOperand / rightOperand;
      }
      default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
    };
  }
}

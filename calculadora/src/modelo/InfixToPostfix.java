package modelo;

import java.util.*;

public class InfixToPostfix {
  private final Stack<String> outputStack;
  private final Stack<String> operatorStack;
  protected String regex = "[^\\d+\\-*/()\\s]";

  // key-values
  private static final Map<String, Integer> precedenceMap =
      Map.of(
          "+", 1,
          "-", 1,
          "*", 2,
          "/", 2,
          "(", -1,
          ")", 0
          );

  public InfixToPostfix() {
    this.outputStack = new Stack<>();
    this.operatorStack = new Stack<>();
  }

  /**
   * Converts an infix expression into postfix (Reverse Polish Notation) form.
   *
   * <p>If a token is not present in {@code precedenceMap}, it is considered an operand and pushed
   * directly into the output stack ({@code outputStack}). Operators are handled using a stack
   * ({@code operatorStack}) based on their tokenPrecedence. A tokenPrecedence value of {@code -1}
   * is used for an opening parenthesis (pushed to the stack), and {@code 0} for a closing
   * parenthesis (handled by {@link #handleClosingParenthesis()}). Finally, {@link
   * #drainRemainingOperators()} moves any remaining operators to the output.
   *
   * <p>This method follows a variation of Dijkstra’s <i>Shunting-yard</i> algorithm. In case of
   * tokenPrecedence ties, operators are assumed to be left-associative, resolved inside {@link
   * #handleOperator(int, String)}.
   *
   * <h3>Parameters:</h3>
   *
   * <ul>
   *   <li><b>infix</b> – the infix expression (may contain spaces); must not be {@code null}.
   * </ul>
   *
   * <h3>Returns:</h3>
   *
   * <ul>
   *   <li><b>Current output:</b> return {@code String.join("", outputStack)} for without spaced
   *       postfix.
   *   <li><b>Alternative:</b> raw stack format using {@code outputStack.toString()}.
   * </ul>
   *
   * <h3>Throws:</h3>
   *
   * <ul>
   *   <li>{@link IllegalArgumentException} – if the input is invalid (e.g., unbalanced parentheses
   *       or unsupported tokens), as determined by {@link #validateInput(String)}.
   * </ul>
   *
   * <h3>Example:</h3>
   *
   * <pre>{@code
   * // Input: 3 + 5 * ( 2 - 8 + 3 )
   * String rpn = convertToPostfix("3 + 5 * ( 2 - 8 + 3)");
   * // Expected output: "3 5 2 8 - 3 + * +"
   * }</pre>
   *
   * <h3>Complexity:</h3>
   *
   * <ul>
   *   <li>Time: O(n), where n is the number of tokens.
   *   <li>Space: O(n), due to the use of stacks.
   * </ul>
   *
   * @implNote Requires {@code precedenceMap} to include all supported operators and markers for
   *     parentheses (opening = -1, closing = 0).
   */
  public String convertToPostfix(String infix) {
    validateInput(infix);
    var tokens = preprocessInfixExpression(infix);

    for (String token : tokens) {
      if (token.isEmpty()) {
        continue;
      }

      var tokenPrecedence = precedenceMap.get(token);

      if (tokenPrecedence == null) {
        outputStack.push(token);
      } else {
        switch (tokenPrecedence) {
          case -1 -> operatorStack.push(token);
          case 0 -> handleClosingParenthesis();
          default -> handleOperator(tokenPrecedence, token);
        }
      }
    }

    drainRemainingOperators();

    return String.join(" ", outputStack);
  }

  private void validateInput(String input) {
    if (input == null || input.trim().isEmpty()) {
      throw new IllegalArgumentException("Error: input is blank.");
    }
    if (input.matches(".*\\d{2,}.*")) {
      throw new IllegalArgumentException("Error: only single digits (0-9) are allowed.");
    }

    var parenthesisDepth = 0;
    var lastTokenType = 'S';
    var openedParenthesisLast = false;

    for (int i = 0; i < input.length(); i++) {
      var currentChar = input.charAt(i);
      if (Character.isWhitespace(currentChar)) {
        continue;
      }

      if (Character.isDigit(currentChar)) {
        if (lastTokenType == ')') {
          throw new IllegalArgumentException("Error: missing operator before digit.");
        }
        lastTokenType = 'D';
        openedParenthesisLast = false;
        continue;
      }

      switch (currentChar) {
        case '+', '-', '*', '/' -> {
          if (lastTokenType == 'S' || lastTokenType == 'O' || lastTokenType == '(') {
            throw new IllegalArgumentException("Error: invalid operator placement.");
          }
          lastTokenType = 'O';
          openedParenthesisLast = false;
        }
        case '(' -> {
          if (lastTokenType == 'D' || lastTokenType == ')') {
            throw new IllegalArgumentException("Error: missing operator before '('.");
          }
          parenthesisDepth++;
          lastTokenType = '(';
          openedParenthesisLast = true;
        }
        case ')' -> {
          if (parenthesisDepth == 0
              || lastTokenType == 'S'
              || lastTokenType == 'O'
              || lastTokenType == '('
              || openedParenthesisLast) {
            throw new IllegalArgumentException(
                "Error: invalid ')' placement or empty parentheses.");
          }
          parenthesisDepth--;
          lastTokenType = ')';
          openedParenthesisLast = false;
        }
        default ->
            throw new IllegalArgumentException("Error: unsupported character: " + currentChar);
      }
    }

    if (parenthesisDepth != 0) {
      throw new IllegalArgumentException("Error: unbalanced parentheses.");
    }
    if (lastTokenType == 'S' || lastTokenType == 'O' || lastTokenType == '(') {
      throw new IllegalArgumentException("Error: expression cannot end with an operator or '('.");
    }
  }

  private String[] preprocessInfixExpression(String infixExpression) {
    infixExpression = removeInvalidCharacters(infixExpression);
    infixExpression = removeWhitespace(infixExpression);

    String[] tokens = infixExpression.split("");

    outputStack.clear();
    operatorStack.clear();

    return tokens;
  }

  private String removeInvalidCharacters(String rawInfix) {
    if (rawInfix == null) return "";
    return rawInfix.replaceAll(regex, "");
  }

  private String removeWhitespace(String rawInfix) {
    return rawInfix.replaceAll("\\s+", "");
  }

  private void handleClosingParenthesis() {
    while (!operatorStack.isEmpty()) {
      String currentOperator = operatorStack.peek();
      Integer currentPrecedence = precedenceMap.get(currentOperator);

      if (currentPrecedence == null) {
        throw new IllegalArgumentException("Error: unknown token on operator stack");
      }
      if (currentPrecedence == -1) {
        operatorStack.pop();
        return;
      }

      outputStack.push(operatorStack.pop());
    }

    throw new IllegalArgumentException("Error: unbalanced parentheses.");
  }

  private void handleOperator(int tokenPrecedence, String operatorToken) {
    while (!operatorStack.isEmpty()) {
      var topOperator = operatorStack.peek();
      var topOperatorPrecedence = precedenceMap.get(topOperator);

      if (topOperatorPrecedence == null || topOperatorPrecedence == -1) {
        break;
      }
      if (topOperatorPrecedence >= tokenPrecedence) {
        outputStack.push(operatorStack.pop());
      } else {
        break;
      }
    }
    operatorStack.push(operatorToken);
  }

  private void drainRemainingOperators() {
    while (!operatorStack.isEmpty()) {
      var poppedOperator = operatorStack.pop();
      var operatorPrecedence = precedenceMap.get(poppedOperator);
      if (operatorPrecedence == null || operatorPrecedence <= 0) {
        throw new IllegalArgumentException("Error: Unbalanced parentheses at the end.");
      }
      outputStack.push(poppedOperator);
    }
  }
}
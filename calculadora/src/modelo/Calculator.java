package modelo;

public class Calculator {
  private final InfixToPostfix converter;
  private final PostfixCalculator evaluator;

  public Calculator() {
    this.converter = new InfixToPostfix();
    this.evaluator = new PostfixCalculator();
  }

  public int calculate(String infixExpression) {
    String postfix = converter.convertToPostfix(infixExpression);
    return evaluator.evaluatePostfix(postfix);
  }
}

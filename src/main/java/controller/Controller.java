/*
 * Copyright (c) 2019 by 또링
 * All rights reserved.
 *
 * Controller.java
 * Expression 객체를 이용하여 계산을 하고 View를 통해 입출력을 제어하는 클래스
 *
 * @author      또링
 * @version     1.0
 * @date        09 Feb 2020
 *
 */

package controller;

import calculator.Calculator;
import model.Expression;
import model.Operator;
import validator.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Controller {

    public void run() {
        String input;
        InputValidator inputValidator = new InputValidator();

        do {
            input = InputView.inputStringFromUser();
        } while (!inputValidator.validateExpression(input));

        Expression expression = new Expression(input);
        expression.setValueList();

        double sum = calculate(expression);

        OutputView.print(sum);
    }

    private double calculate(Expression expression) {
        List<Double> numbers = expression.getNumbers();
        List<String> operators = expression.getOperators();
        double sum = numbers.get(0);

        Calculator calculator = new Calculator();

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals(Operator.PLUS.toString())) {
                sum = calculator.plus(sum, numbers.get(i + 1));
            }
            if (operators.get(i).equals(Operator.MINUS.toString())) {
                sum = calculator.minus(sum, numbers.get(i + 1));
            }
            if (operators.get(i).equals(Operator.MUL.toString())) {
                sum = calculator.mul(sum, numbers.get(i + 1));
            }
            if (operators.get(i).equals(Operator.DIV.toString())) {
                sum = calculator.div(sum, numbers.get(i + 1));
            }
        }

        return sum;
    }
}

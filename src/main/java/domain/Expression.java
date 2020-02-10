package domain;

import java.util.Objects;

public class Expression {
	private final Operands operands;
	private final Operators operators;

	Expression(Operands operands, Operators operators) {
		this.operands = operands;
		this.operators = operators;
	}

	int getResult() {
		while (!operators.isEmpty()) {
			Operator operator = Operator.getOf(operators.poll());
			int calculateResult = operator.calculate(operands.poll(), operands.poll());
			operands.offerFirst(calculateResult);
		}
		return operands.poll();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Expression that = (Expression)o;
		return Objects.equals(operands, that.operands) &&
			Objects.equals(operators, that.operators);
	}

	@Override
	public int hashCode() {
		return Objects.hash(operands, operators);
	}
}

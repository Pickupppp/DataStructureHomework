package com.pickupppp.task2;

public class BooleanExpressionCalculate {

	private static int leftCount = 0; // the number of left brackets
	private static int rightCount = 0; // the number of right bracket

	public static boolean calculate(String expression) throws Exception {
		leftCount = 0;
		rightCount = 0;
		AStack<Character> operand = new AStack<>();
		AStack<Character> operator = new AStack<>();
		for (int i = 0; i < expression.length(); i++) {
			char symbol = expression.charAt(i);
			if (symbol == 'T' || symbol == 'F') {
				operand.push(symbol);
			} else {
				switch (symbol) {
				case '(':
					operator.push(symbol);
					leftCount++;
					break;
				case ')':
					if (leftCount <= rightCount)
						throw new Exception("bracket mismatch");
					operator.push(symbol);
					rightCount++;
					calculateBracket(operand, operator);
					break;
				case '!':
					operator.push(symbol);
					break;
				case '&':
					if (operator.contains('!')) {
						not(operand, operator);
					}
					operator.push(symbol);
					break;
				case '^':
					if (operator.contains('!')) {
						not(operand, operator);
					}
					if (operator.contains('&')) {
						and(operand, operator);
					}
					operator.push(symbol);
					break;
				case '|':
					if (operator.contains('!')) {
						not(operand, operator);
					}
					if (operator.contains('&')) {
						and(operand, operator);
					}
					if (operator.contains('^')) {
						xor(operand, operator);
					}
					operator.push(symbol);
					break;
				}
			}
		}
		if (leftCount != rightCount)
			throw new Exception("bracket mismatch");

		if (operator.isEmpty() && operand.size() > 1)
			throw new Exception("missing operator");

		while (!operator.isEmpty()) {
			char symbol = operator.topValue();
			choose2Execute(symbol, operand, operator);
		}
		if (operand.size() > 1) {
			throw new Exception("missing operator");
		}
		if (operand.pop() == 'T') {
			return true;
		} else {
			return false;
		}
	}

	private static boolean char2Boolean(char symbol) {
		if (symbol == 'T') {
			return true;
		} else {
			return false;
		}
	}

	private static char boolean2Char(boolean bool) {
		if (bool) {
			return 'T';
		} else {
			return 'F';
		}
	}

	private static void or(AStack<Character> operand, AStack<Character> operator) throws Exception {
		if (operand.size() < 2) {
			throw new Exception("missing operand");
		}
		boolean temp1 = char2Boolean(operand.pop());
		boolean temp2 = char2Boolean(operand.pop());
		char symbol = operator.pop();
		if (symbol == '|') {
			operand.push(boolean2Char(temp1 | temp2));
		} else {
			throw new Exception("operator mismatch");
		}
	}

	private static void xor(AStack<Character> operand, AStack<Character> operator) throws Exception {
		if (operand.size() < 2) {
			throw new Exception("missing operand");
		}
		boolean temp1 = char2Boolean(operand.pop());
		boolean temp2 = char2Boolean(operand.pop());
		char symbol = operator.pop();
		if (symbol == '^') {
			operand.push(boolean2Char(temp1 ^ temp2));
		} else {
			throw new Exception("operator mismatch");
		}
	}

	private static void and(AStack<Character> operand, AStack<Character> operator) throws Exception {
		if (operand.size() < 2) {
			throw new Exception("missing operand");
		}
		boolean temp1 = char2Boolean(operand.pop());
		boolean temp2 = char2Boolean(operand.pop());
		char symbol = operator.pop();
		if (symbol == '&') {
			operand.push(boolean2Char(temp1 & temp2));
		} else {
			throw new Exception("operator mismatch");
		}
	}

	private static void not(AStack<Character> operand, AStack<Character> operator) throws Exception {
		if (operand.size() < 1) {
			throw new Exception("missing operand");
		}
		boolean temp1 = char2Boolean(operand.pop());
		char symbol = operator.pop();
		if (symbol == '!') {
			operand.push(boolean2Char(!temp1));
		} else {
			throw new Exception("operator mismatch");
		}
	}

	private static void calculateBracket(AStack<Character> operand, AStack<Character> operator) throws Exception {
		operator.pop(); // pop the right bracket
		rightCount--;
		while (operator.topValue() != '(') {
			choose2Execute(operator.topValue(), operand, operator);
		}
		operator.pop(); // pop the left bracket
		leftCount--;
	}

	private static void choose2Execute(char symbol, AStack<Character> operand, AStack<Character> operator)
			throws Exception {
		switch (symbol) {
		case '!':
			not(operand, operator);
			break;
		case '&':
			and(operand, operator);
			break;
		case '^':
			xor(operand, operator);
			break;
		case '|':
			or(operand, operator);
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(calculate("T|(T&T)&"));
	}

}

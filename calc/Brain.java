package calc;

import java.text.DecimalFormat;

/**
 * Brain.java
 * 
 * This class is how the calculator operates.
 * 
 * @author Timothy Lin
 * Wheaton College, CSCI 245, Fall 2014
 * Project 8
 * Dec 11, 2014
 */

public class Brain {

	/**
	 * Stores the current CalculatorFace
	 */

	private CalculatorFace calcFace;

	/**
	 * Stores the new calculator for this instance of CalculatorFace
	 */

	private Calculator calc;

	/**
	 * Used to keep track of the previous state that the calculator was in
	 */

	private int previousState;

	/**
	 * Keeps track of the current state of the calculator
	 */

	private int currentState;

	/**
	 * Multiplier used in storing decimals properly in the double
	 */

	private double multiplier;

	/**
	 * Used for formatting decimals so they fit the screen
	 */

	private DecimalFormat df;

	/**
	 * The number that was just typed in
	 */

	private int tempNumber;

	/**
	 * An array storing all the states that the caclulator can jump to
	 */

	private State[] states;
	
	/**
	 * Ints used for changing between states
	 */
	
	public static final int INITIAL_STATE = 0;
	public static final int BEFORE_DECIMAL_STATE = 1;
	public static final int AFTER_DECIMAL_STATE = 2;
	public static final int AFTER_PLUS_STATE = 3;
	public static final int AFTER_MINUS_STATE = 4;
	public static final int AFTER_MULTIPLY_STATE = 5;
	public static final int AFTER_DIVIDE_STATE = 6;
	public static final int BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE = 7;
	public static final int AFTER_DECIMAL_AFTER_PLUSMINUS_STATE = 8;
	public static final int AFTER_EQUALS_STATE = 9;

	/**
	 * Constructor for the Brain Also instantiates a new Calculator
	 * 
	 * @param face Takes in a Calculator Face
	 */
	
	public Brain(CalculatorFace face) {
		this.calcFace = face;
		calc = new Calculator();
		multiplier = 1;
		tempNumber = 0;
		states = new State[10];
		states[0] = new InitialState();
		states[1] = new BeforeDecimalState();
		states[2] = new AfterDecimalState();
		states[3] = new AfterPlusState();
		states[4] = new AfterMinusState();
		states[5] = new AfterMultiplyState();
		states[6] = new AfterDivideState();
		states[7] = new BeforeDecimalAfterPlusMinusState();
		states[8] = new AfterDecimalAfterPlusMinusState();
		states[9] = new AfterEqualsState();
		df = new DecimalFormat("#.#############");
		setPreviousState(INITIAL_STATE);
		setCurrentState(INITIAL_STATE);
	}

	/**
	 * Takes in the number being typed on the screen and set it as the current
	 * number in Calculator
	 * 
	 * @param number Number being pressed
	 */
	
	public void number(int number) {
		states[currentState].onNumber(number);
	}

	/**
	 * When the plus sign is pressed, set the current state to After_Plus_State
	 * and call the onOperate method of that state
	 */
	
	public void plus() {
		setCurrentState(AFTER_PLUS_STATE);
		states[currentState].onOperate();
	}

	/**
	 * When the minus sign is pressed, set the current state to After_Minus_State
	 * and call the onOperate method of that state
	 */

	public void minus() {
		setCurrentState(AFTER_MINUS_STATE);
		states[currentState].onOperate();
	}

	/**
	 * When the multiplication sign is pressed, set the current state to After_Multiply_State
	 * and call the onOperate method of that state
	 */

	public void multiply() {
		setCurrentState(AFTER_MULTIPLY_STATE);
		states[currentState].onOperate();
	}

	/**
	 * When the division sign is pressed, set the current state to After_Divide_State
	 * and call the onOperate method of that state
	 */

	public void divide() {
		setCurrentState(AFTER_DIVIDE_STATE);
		states[currentState].onOperate();
	}

	/**
	 * When the plus/minus button is pressed, call the onPlusMinus method of the current state
	 */

	public void plusMinus() {
		states[currentState].onPlusMinus();
	}

	/**
	 * When the decimal button is pressed, call the onDecimal method of the current state
	 */

	public void decimal() {
		states[currentState].onDecimal();
	}

	/**
	 * When the equal sign is pressed, call the onEquals method of the current state
	 */

	public void equals() {
		states[currentState].onEquals();
	}

	/**
	 * Resets the calculator back to initial state,
	 * resets all numbers stored in Calculator class to 0,
	 * rest Multiplier to 1,
	 * reset tempNumber to 0,
	 * set all states to Initial_State,
	 * and write 0 to the calculator.
	 */

	public void clear() {
		calc.clear();
		multiplier = 1;
		tempNumber = 0;
		setPreviousState(INITIAL_STATE);
		setCurrentState(INITIAL_STATE);
		calcFace.writeToScreen("" + 0);
	}

	/**
	 * Gets the current state of the calculator
	 * 
	 * @return the currentState
	 */
	public int getCurrentState() {
		return currentState;
	}

	/**
	 * Sets the current state of the calculator
	 * 
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}
	
	/**
	 * Gets the previous state of the calculator
	 * 
	 * @return the previousState
	 */
	public int getPreviousState() {
		return previousState;
	}

	/**
	 * Sets the previous state of the calculator
	 * 
	 * @param previousState
	 *            the previousState to set
	 */
	public void setPreviousState(int previousState) {
		this.previousState = previousState;
	}

	/**
	 * Nested class modeling the initial state of the calculator
	 */
	
	class InitialState extends State {
		
		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State
		 */

		void onDecimal() {
			setCurrentState(AFTER_DECIMAL_STATE);
		}
		
		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */

		void onPlusMinus() {
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen("" + calc.getCurrent());
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When a number button is pressed, store it to tempNumber,
		 * grab the current number, times that by 10 so all the digits are in the right place,
		 * add the number that was just pressed to it.
		 * Write that number to the screen, and set the current state to Before_Decimal State.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(calc.getCurrent() * 10.0 + tempNumber);
			calcFace.writeToScreen("" + calc.getCurrent());
			setCurrentState(BEFORE_DECIMAL_STATE);
		}
		
		/**
		 * When the operands are pressed, do nothing because there is nothing to operate on yet.
		 */
		
		void onOperate() { }
		
		/**
		 * When equals button is pressed, do nothing because there is nothing to operate on yet.
		 */

		void onEquals() { }

	}

	/**
	 * Nested class modeling the state of the calculator before the decimal button is pressed
	 */
	
	class BeforeDecimalState extends State {

		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State
		 */
		
		void onDecimal() {
			setCurrentState(AFTER_DECIMAL_STATE);
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			setPreviousState(getCurrentState());
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen("" + calc.getCurrent());
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When a number button is pressed, store it to tempNumber,
		 * grab the current number, times that by 10 so all the digits are in the right place,
		 * add the number that was just pressed to it.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(calc.getCurrent() * 10.0 + tempNumber);
			calcFace.writeToScreen("" + calc.getCurrent());
		}

		/**
		 * When the operands are pressed, do nothing because there is nothing to operate on yet.
		 */
		
		void onOperate() { }

		/**
		 * When the equals button is pressed, set current state to previous state,
		 * and then call the onEquals method on the previous state.
		 */
		
		void onEquals() {
			setCurrentState(getPreviousState());
			states[currentState].onEquals();
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the decimal button is pressed
	 */
	
	class AfterDecimalState extends State {

		/**
		 * When the decimal button is pressed, do nothing because this is the state the calculator should be in
		 */
		
		void onDecimal() { }

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to After_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			setPreviousState(getCurrentState());
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			setCurrentState(AFTER_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When a number button is pressed, store it to tempNumber,
		 * times the multiplier by 0.1 so that the new int pressed 
		 * will be in the right place after the decimal,
		 * grab the current number,
		 * times the tempNumber by the multiplier so all the digits are in the right place,
		 * add everything together.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			multiplier *= 0.1;
			calc.setCurrent(calc.getCurrent() + (tempNumber * multiplier));
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the operands are pressed, do nothing because there is nothing to operate on yet.
		 */
		
		void onOperate() { }

		/**
		 * When the equals button is pressed, set current state to previous state,
		 * and then call the onEquals method on the previous state.
		 * Also, set multiplier back to 1.
		 */
		
		void onEquals() {
			setCurrentState(getPreviousState());
			states[currentState].onEquals();
			multiplier = 1;
		}

	}

	/**
	 * Nested class modeling the state of the calculator before the decimal button is pressed
	 * but after the PlusMinus button is pressed
	 */
	
	class BeforeDecimalAfterPlusMinusState extends State {
		
		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_After_PlusMinus_State
		 */

		void onDecimal() {
			setCurrentState(AFTER_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to the previous state that it came from
		 */
		
		void onPlusMinus() {
			setCurrentState(getPreviousState());
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen("" + calc.getCurrent());
		}

		/**
		 * When a number button is pressed, store it to tempNumber,
		 * grab the current number, times that by 10 so all the digits are in the right place,
		 * add the number that was just pressed to it.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(calc.getCurrent() * 10.0 - tempNumber);
			calcFace.writeToScreen("" + calc.getCurrent());
		}

		/**
		 * When the operands are pressed, do nothing because there is nothing to operate on yet.
		 */
		
		void onOperate() { }
		
		/**
		 * When the equals button is pressed, set current state to previous state,
		 * and then call the onEquals method on the previous state.
		 */

		void onEquals() {
			setCurrentState(getPreviousState());
			states[currentState].onEquals();
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the decimal button is pressed
	 * and after the PlusMinus button is pressed
	 */
	
	class AfterDecimalAfterPlusMinusState extends State {
		
		/**
		 * When the decimal button is pressed, do nothing
		 */

		void onDecimal() { }

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to the previous state that it came from
		 */
		
		void onPlusMinus() {
			setCurrentState(getPreviousState());
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When a number button is pressed, store it to tempNumber,
		 * times the multiplier by 0.1 so that the new int pressed 
		 * will be in the right place after the decimal,
		 * grab the current number,
		 * times the tempNumber by the multiplier so all the digits are in the right place,
		 * add everything together.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			multiplier *= 0.1;
			calc.setCurrent(calc.getCurrent() - (tempNumber * multiplier));
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the operands are pressed, do nothing because there is nothing to operate on yet.
		 */
		
		void onOperate() { }

		/**
		 * When the equals button is pressed, set current state to previous state,
		 * and then call the onEquals method on the previous state.
		 * Also, set multiplier back to 1.
		 */
		
		void onEquals() {
			setCurrentState(getPreviousState());
			states[currentState].onEquals();
			multiplier = 1;
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the Plus button is pressed
	 */
	
	class AfterPlusState extends State {
		
		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State,
		 * set the previous state to After_Plus_State so we can come back to it,
		 * and write the current number to the screen
		 */

		void onDecimal() {
			setPreviousState(AFTER_PLUS_STATE);
			setCurrentState(AFTER_DECIMAL_STATE);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When an int is pressed, set tempNumber to the int that is pressed,
		 * set current to tempNumber,
		 * write current to the screen,
		 * reset multiplier to one,
		 * set the previous state to this state, and set the current state to Before_Decimal_State
		 * so that part of the code can be reused.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(tempNumber);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			multiplier = 1;
			setPreviousState(AFTER_PLUS_STATE);
			setCurrentState(BEFORE_DECIMAL_STATE);
		}

		/**
		 * When the operand is pressed, set current to result,
		 * set current back to 0,
		 * write to screen the result
		 */
		
		void onOperate() {
			calc.setResult(calc.getCurrent());
			calc.setCurrent(0);
			calcFace.writeToScreen(df.format(calc.getResult()));
		}

		/**
		 * After equals is pressed, store the result by adding what was previously 
		 * stored as result to the current number that was typed in.  Write that number to the screen,
		 * set current to result, save current state as previous state, and set current state
		 * to After_Equals_State.
		 */
		
		void onEquals() {
			calc.setResult(calc.getResult() + calc.getCurrent());
			calcFace.writeToScreen(df.format(calc.getResult()));
			calc.setCurrent(calc.getResult());
			setPreviousState(AFTER_PLUS_STATE);
			setCurrentState(AFTER_EQUALS_STATE);
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the Minus button is pressed
	 */
	
	class AfterMinusState extends State {

		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State,
		 * set the previous state to After_Minus_State so we can come back to it,
		 * and write the current number to the screen
		 */
		
		void onDecimal() {
			setPreviousState(AFTER_MINUS_STATE);
			setCurrentState(AFTER_DECIMAL_STATE);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}
		
		/**
		 * When an int is pressed, set tempNumber to the int that is pressed,
		 * set current to tempNumber,
		 * write current to the screen,
		 * reset multiplier to one,
		 * set the previous state to this state, and set the current state to Before_Decimal_State
		 * so that part of the code can be reused.
		 */

		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(tempNumber);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			multiplier = 1;
			setPreviousState(AFTER_MINUS_STATE);
			setCurrentState(BEFORE_DECIMAL_STATE);
		}

		/**
		 * When the operand is pressed, set current to result,
		 * set current back to 0,
		 * write to screen the result
		 */
		
		void onOperate() {
			calc.setResult(calc.getCurrent());
			calc.setCurrent(0);
			calcFace.writeToScreen(df.format(calc.getResult()));
		}

		/**
		 * After equals is pressed, store the result by subtracting what was previously 
		 * stored as result to the current number that was typed in.  Write that number to the screen,
		 * set current to result, save current state as previous state, and set current state
		 * to After_Equals_State.
		 */
		
		void onEquals() {
			calc.setResult(calc.getResult() - calc.getCurrent());
			calcFace.writeToScreen(df.format(calc.getResult()));
			calc.setCurrent(calc.getResult());
			setPreviousState(AFTER_MINUS_STATE);
			setCurrentState(AFTER_EQUALS_STATE);
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the Multiply button is pressed
	 */
	
	class AfterMultiplyState extends State {

		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State,
		 * set the previous state to After_Multiply_State so we can come back to it,
		 * and write the current number to the screen
		 */
		
		void onDecimal() {
			setPreviousState(AFTER_MULTIPLY_STATE);
			setCurrentState(AFTER_DECIMAL_STATE);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When an int is pressed, set tempNumber to the int that is pressed,
		 * set current to tempNumber,
		 * write current to the screen,
		 * reset multiplier to one,
		 * set the previous state to this state, and set the current state to Before_Decimal_State
		 * so that part of the code can be reused.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(tempNumber);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			multiplier = 1;
			setPreviousState(AFTER_MULTIPLY_STATE);
			setCurrentState(BEFORE_DECIMAL_STATE);
		}

		/**
		 * When the operand is pressed, set current to result,
		 * set current back to 0,
		 * write to screen the result
		 */
		
		void onOperate() {
			calc.setResult(calc.getCurrent());
			calc.setCurrent(0);
			calcFace.writeToScreen(df.format(calc.getResult()));
		}
		
		/**
		 * After equals is pressed, store the result by multiplying what was previously 
		 * stored as result to the current number that was typed in.  Write that number to the screen,
		 * set current to result, save current state as previous state, and set current state
		 * to After_Equals_State.
		 */

		void onEquals() {
			calc.setResult(calc.getResult() * calc.getCurrent());
			calcFace.writeToScreen(df.format(calc.getResult()));
			calc.setCurrent(calc.getResult());
			setPreviousState(AFTER_MULTIPLY_STATE);
			setCurrentState(AFTER_EQUALS_STATE);
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the Divide button is pressed
	 */
	
	class AfterDivideState extends State {

		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State,
		 * set the previous state to After_Divide_State so we can come back to it,
		 * and write the current number to the screen
		 */
		
		void onDecimal() {
			setPreviousState(AFTER_DIVIDE_STATE);
			setCurrentState(AFTER_DECIMAL_STATE);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When an int is pressed, set tempNumber to the int that is pressed,
		 * set current to tempNumber,
		 * write current to the screen,
		 * reset multiplier to one,
		 * set the previous state to this state, and set the current state to Before_Decimal_State
		 * so that part of the code can be reused.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(tempNumber);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			multiplier = 1;
			setPreviousState(AFTER_DIVIDE_STATE);
			setCurrentState(BEFORE_DECIMAL_STATE);
		}

		/**
		 * When the operand is pressed, set current to result,
		 * set current back to 0,
		 * write to screen the result
		 */
		
		void onOperate() {
			calc.setResult(calc.getCurrent());
			calc.setCurrent(0);
			calcFace.writeToScreen(df.format(calc.getResult()));
		}

		/**
		 * After equals is pressed, store the result by dividing what was previously 
		 * stored as result to the current number that was typed in.  Write that number to the screen,
		 * set current to result, save current state as previous state, and set current state
		 * to After_Equals_State.  Exception handling is done for division by 0.
		 */
		
		void onEquals() {
			try {
				calc.setResult(calc.getResult() / calc.getCurrent());
			} catch (ArithmeticException ae) {
			}
			calcFace.writeToScreen(df.format(calc.getResult()));
			calc.setCurrent(calc.getResult());
			setPreviousState(AFTER_DIVIDE_STATE);
			setCurrentState(AFTER_EQUALS_STATE);
		}

	}

	/**
	 * Nested class modeling the state of the calculator after the Equals button is pressed
	 */
	
	class AfterEqualsState extends State {
		
		/**
		 * When the decimal button is pressed, set the current state to After_Decimal_State,
		 * set the previous state to After_Equals_State so we can come back to it,
		 * and write the current number to the screen
		 */

		void onDecimal() {
			setPreviousState(AFTER_EQUALS_STATE);
			setCurrentState(AFTER_DECIMAL_STATE);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
		}

		/**
		 * When the PlusMinus button is pressed, grab the current number and times it by -1
		 * to make it positive or negative, write that number to the screen,
		 * and then set current state to Before_Decimal_After_PlusMinus_State
		 */
		
		void onPlusMinus() {
			calc.setCurrent(calc.getCurrent() * -1.0);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			setCurrentState(BEFORE_DECIMAL_AFTER_PLUSMINUS_STATE);
		}

		/**
		 * When an int is pressed, set tempNumber to the int that is pressed,
		 * set current to tempNumber,
		 * write current to the screen,
		 * reset multiplier to one,
		 * set the previous state to this state, and set the current state to Before_Decimal_State
		 * so that part of the code can be reused.
		 */
		
		void onNumber(int number) {
			tempNumber = number;
			calc.setCurrent(tempNumber);
			calcFace.writeToScreen(df.format(calc.getCurrent()));
			multiplier = 1;
			setPreviousState(AFTER_EQUALS_STATE);
			setCurrentState(BEFORE_DECIMAL_STATE);
		}

		/**
		 * When the operands are pressed, do nothing because there is nothing to operate on yet.
		 */
		
		void onOperate() { }
		
		/**
		 * After equals is pressed, set current state to to previous state,
		 * and call the onEquals method of the previous state.
		 */

		void onEquals() {
			setCurrentState(previousState);
			states[currentState].onEquals();
		}

	}

}

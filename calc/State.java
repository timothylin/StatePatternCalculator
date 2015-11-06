package calc;

/**
 * State.java
 * 
 * This class is an abstract class to be inherited by the various states of the calculator.
 * 
 * @author Timothy Lin
 * Wheaton College, CSCI 245, Fall 2014
 * Project 8
 * Dec 8, 2014
 */

public abstract class State {
	
	/**
	 * What to do in the current state when the Decimal button is pressed.
	 */

	abstract void onDecimal();
	
	/**
	 * What to do in the current state when the PlusMinus button is pressed.
	 */

	abstract void onPlusMinus();
	
	/**
	 * What to do in the current state when the PlusMinus button is pressed.
	 * 
	 * @param number The number being pressed.
	 */

	abstract void onNumber(int number);
	
	/**
	 * What to do in the current state when an operand button is pressed.
	 */

	abstract void onOperate();
	
	/**
	 * What to do in the current state when the Equals button is pressed.
	 */

	abstract void onEquals();

}
package calc;

/**
 * Calculator.java
 * 
 * This class is used to store all the values that the calculator needs. For our
 * purposes, only the current value is stored.
 * 
 * @author Timothy Lin
 * Wheaton College, CSCI 245, Fall 2014
 * Project 8
 * Dec 6, 2014
 */

public class Calculator {

	private double current;
	
	private double result;

	public Calculator() {
		this.setCurrent(0);
		this.setResult(0);
	}

	/**
	 * Clears the current value stored in the calculator
	 */

	public void clear() {
		this.setCurrent(0);
		this.setResult(0);
	}

	/**
	 * @return the current
	 */
	public double getCurrent() {
		return current;
	}

	/**
	 * @param number the current to set
	 */
	public void setCurrent(double number) {
		this.current = number;
	}

	/**
	 * @return the result
	 */
	public double getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(double result) {
		this.result = result;
	}

}

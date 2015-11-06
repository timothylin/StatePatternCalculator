package calc;

/**
 * IntListener.java
 * 
 * This class is the listener for the number buttons.
 * 
 * @author Timothy Lin
 * Wheaton College, CSCI 245, Fall 2014
 * Project 6
 * Oct 8, 2014
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntListener implements ActionListener {

	private Brain calcBrain;
	private int number;

	/**
	 * Constructor.
	 * 
	 * @param brain
	 *            Instantiates brain which is the workings behind this
	 *            calculator
	 * @param num
	 *            sets number that is being pressed into brain
	 */

	protected IntListener(Brain brain, int number) {
		this.calcBrain = brain;
		this.number = number;
	}

	/**
	 * Button pressed invokes actionPerformed method and passes to setNum method
	 * in brain Brain then displays the number on the calculator screen
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		calcBrain.number(number);
	}
}

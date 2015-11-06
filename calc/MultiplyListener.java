package calc;

/**
 * MultiplyListener.java
 * 
 * This class is the listener for the multiplication button.
 * 
 * @author Timothy Lin
 * Wheaton College, CSCI 245, Fall 2014
 * Project 6
 * Oct 8, 2014
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplyListener implements ActionListener {

	private Brain calcBrain;

	/**
	 * Constructor.
	 * 
	 * @param brain
	 *            Instantiate brain which operates the calculator
	 */

	protected MultiplyListener(Brain brain) {
		this.calcBrain = brain;
	}

	/**
	 * Button pressed invokes actionPerformed method and passes to the operand
	 * method in brain
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		calcBrain.multiply();
	}
}

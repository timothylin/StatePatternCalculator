package calc;

/**
 * EqualsListener.java
 * 
 * This class is the listener for the equals button, which does not do anything, really.
 * 
 * @author Timothy Lin
 * Wheaton College, CSCI 245, Fall 2014
 * Project 6
 * Oct 8, 2014
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualsListener implements ActionListener {

	private Brain calcBrain;

	/**
	 * Constructor.
	 * 
	 * @param brain
	 *            Instantiate brain which operates the calculator
	 */

	protected EqualsListener(Brain brain) {
		this.calcBrain = brain;
	}

	/**
	 * Button pressed invokes actionPerformed method and passes to the operate
	 * method in brain
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		calcBrain.equals();
	}
}

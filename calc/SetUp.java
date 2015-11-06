package calc;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {
		
		Brain calcBrain = new Brain(face);
		
		for (int i = 0; i < 10; i++) 
			face.addNumberActionListener(i, new IntListener(calcBrain, i));
		/*
		face.addNumberActionListener(0, new IntListener(calcBrain, "" + 0));
		face.addNumberActionListener(1, new IntListener(calcBrain, "" + 1));
		face.addNumberActionListener(2, new IntListener(calcBrain, "" + 2));
		face.addNumberActionListener(3, new IntListener(calcBrain, "" + 3));
		face.addNumberActionListener(4, new IntListener(calcBrain, "" + 4));
		face.addNumberActionListener(5, new IntListener(calcBrain, "" + 5));
		face.addNumberActionListener(6, new IntListener(calcBrain, "" + 6));
		face.addNumberActionListener(7, new IntListener(calcBrain, "" + 7));
		face.addNumberActionListener(8, new IntListener(calcBrain, "" + 8));
		face.addNumberActionListener(9, new IntListener(calcBrain, "" + 9));
		*/
		face.addActionListener('+', new PlusListener(calcBrain));
		face.addActionListener('-', new MinusListener(calcBrain));
		face.addActionListener('*', new MultiplyListener(calcBrain));
		face.addActionListener('/', new DivideListener(calcBrain));
		face.addActionListener('=', new EqualsListener(calcBrain));
		face.addActionListener('C', new CListener(calcBrain));
		face.addActionListener('.', new DecimalListener(calcBrain));
		face.addPlusMinusActionListener(new PlusMinusListener(calcBrain));

		face.writeToScreen("" + 0);
		
	}
	
	
	/**
	 * This main method is for your testing of your calculator.
	 * It will *not* be used during grading. Any changes you make
	 * to this method will be ignored at grading.
	 */
	public static void main(String[] args) {
		setUpCalculator(new ConcreteCalculatorFace());
	}

}

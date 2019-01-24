package pd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PlagiarismDetector {
	
	private int requiredArgsLength = 3; //num of required args
	private int optionalArgsLength = 4; //num of args required if inputting tuple size
	private int defaultTupleCount = 3; //default tuple size
	
	/**
	 * Constructor for class 
	 */
	public PlagiarismDetector() {
	
	}
	
	/**
	 * Checks if String is an integer value
	 * @param input - String to be checked 
	 * @return true if input is an integer, false if not
	 */
	private static boolean isInt(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public static void main( String[] args ) {
		
		PlagiarismDetector mainDetector = new PlagiarismDetector();
		
		if(args.length == mainDetector.requiredArgsLength) {
			//finds similarity percentage with default tuple count
			Compare compareHelper = new Compare(args[0], args[1], args[2], mainDetector.defaultTupleCount);
			System.out.println(compareHelper.getPercentage());
		}
		else if(args.length == mainDetector.optionalArgsLength) {
			if(isInt(args[3])) {
				//finds similarity percentage with indicated tuple size if it is valid
				Compare compareHelper = new Compare(args[0], args[1], args[2], Integer.parseInt(args[3]));
				System.out.println(compareHelper.getPercentage());	
			}
			else {
				System.out.println("Invalid Tuple Size");
			}
		}
		else {
			System.out.println("Invalid Argument Size");
		}
	}
}
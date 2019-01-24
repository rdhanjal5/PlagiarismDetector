package pd;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class FileToNTuple {
	
	private ArrayList<ArrayList<String>> classTuples; //contains n-tuples of initialized file
	
	/**
	 * A constructor that initializes an n-tuples representation
	 * of the passed file
	 * @param fileName - name of the file to turn to n-tuples
	 * @param count - length of each tuple
	 */
	FileToNTuple(String fileName, int count) {
		this.classTuples = fileToTuples(fileName, count); 
	}
	
	/**
	 * A getter method which returns the class n-tuples representation
	 * @return class instance variable of n-tuples
	 */
	public ArrayList<ArrayList<String>> getClassTuples() {
		return classTuples;
	}
	
	/**
	 * Takes filename and returns String representation of that file
	 * without unwanted characters
	 * @param fileName - name of the file to be cleaned and returned
	 * @return String representation of fileName
	 */
	public static String inputToString(String fileName) {
		int synonymValue = 0;
		String line;
		
		StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null)
	        {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    String myString = contentBuilder.toString();
	    
	    //clean file of all wild characters
	    myString = myString.replaceAll("[.,!{}@#$%&\\]\\[^*();:/<>{}|+=\"']", "").trim();
	    
	    return myString;
	}
	
	/**
	 * Takes fileName and count to create a n-tuples representation of
	 * fileName with each tuple being of length count
	 * @param fileName - name of the file to turn to n-tuples
	 * @param count - length of each tuple
	 * @return ArrayList object on n-tuples
	 */
	private static ArrayList<ArrayList<String>> fileToTuples(String fileName, int count) {
		//cleans fileName of wild characters and turns it into String object
		String[] words = inputToString(fileName).split("\\s+");
		
		//initialize ArrayList to represent n-tuples
		ArrayList<ArrayList<String>> listOfNTuples = new ArrayList<ArrayList<String>>();
		
        for (int i = 0; i < words.length - 1; i++) {
        	String nWords;
        	ArrayList<String> nTuple = new ArrayList<String>();
        	if(i == words.length - (count)) {
        		while(i < words.length) {
        			nTuple.add(words[i].trim().toLowerCase());
        			i++;
        		}
        	}
        	else { 
        		for(int j = 0; j < count; j++) {
        			nTuple.add(words[i + j].trim().toLowerCase());
        		}
        	}
        	listOfNTuples.add(nTuple);
        }
        return listOfNTuples;
	}
}

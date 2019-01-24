package pd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Compare {
	
	private double comparePercentage; //similarity percentage between two files
	
	/**
	 * A constructor that initializes the comparePercentage class variable
	 * to indicate the similarity level between two files
	 * @param synonymFile - name of file containing synonyms to aid in checking similarity
	 * @param inputFile1 - name of file 1
	 * @param inputFile2 - name of file 2
	 * @param  count - length of each tuple
	 */
	public Compare(String synonymFile, String inputFile1, String inputFile2, int tupleCount) {
		this.comparePercentage = similarityPercentage(synonymFile, inputFile1, inputFile2, tupleCount);
	}
	
	/**
	 * A getter method which returns class variable representing similar percentage
	 * @return class instance variable containing similarity percentage 
	 */
	public double getPercentage() {
		return comparePercentage;
	}
	
	/**
	 * A helper method to the similarityPercentage method which iterates through each tuple
	 * and maps the strings in each tuple to its synonym group (if a synonym group exists)
	 * or keeps the string the same
	 * @param fileTuples - ArrayList containing n-tuples of file
	 * @param synonymMap - HashMap which maps synonyms to a number representing its group
	 */
	private static ArrayList<ArrayList<String>> checkSynonyms(ArrayList<ArrayList<String>> fileTuples, 
			HashMap<String, Integer> synonymMap) {
		
		for(int i = 0; i < fileTuples.size(); i++) {
			
			//iterate through each tuple
			for(int j = 0; j < fileTuples.get(i).size(); j++) {
				//if a string in the tuple has a synonym, change it to its group value found in synonymMap
				if(synonymMap.containsKey(fileTuples.get(i).get(j))) {
					String a = String.valueOf(synonymMap.get( String.valueOf(fileTuples.get(i).get(j) )) );
					fileTuples.get(i).set(j, a);
				}
				//else keep the string the same
			}	
		}
		return fileTuples;
	}
	
	/**
	 * Checks how many tuples are equal to each other between two files by taking into
	 * account synonyms for the strings in each file
	 * @param synonymFile - name of file containing synonyms to aid in checking similarity
	 * @param inputFile1 - name of file 1
	 * @param inputFile2 - name of file 2
	 * @param  tupleCount - length of each tuple
	 */
	private static double similarityPercentage(String synonymFile, String inputFile1, String inputFile2, 
			int tupleCount) {
		
		HashSynonyms synonymMap = new HashSynonyms(synonymFile); //creates synonym map
		FileToNTuple file1 = new FileToNTuple(inputFile1, tupleCount); //converts file1 to n-tuples
		FileToNTuple file2 = new FileToNTuple(inputFile2, tupleCount); //converts file2 to n-tuples
		
		//takes the n-tuples from file1 and file2 and maps each string to its synonym group number 
		ArrayList<ArrayList<String>> file1TuplesWithSynonyms = checkSynonyms(file1.getClassTuples(), synonymMap.getClassMap());
		ArrayList<ArrayList<String>> file2TuplesWithSynonyms = checkSynonyms(file2.getClassTuples(), synonymMap.getClassMap());
		
		//tracks num of similar tuples between files
		int count = 0;
		
		for(int i = 0; i < file1TuplesWithSynonyms.size(); i++) {
			for(int j = 0; j < file2TuplesWithSynonyms.size(); j++) {
				if(file1TuplesWithSynonyms.get(i).equals(file2TuplesWithSynonyms.get(j))) {
					count++;
				}
			}
		}
		
		double percentage = (double)count/(double)file1TuplesWithSynonyms.size();
		return percentage;
	}
}
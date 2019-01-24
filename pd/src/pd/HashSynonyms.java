package pd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;

public class HashSynonyms {
	
	private HashMap<String, Integer> classMap; //maps synonyms from synonym text file
	
	/**
	 * A constructor which initializes the classMap to map the 
	 * synonyms of the file
	 * @param fileName - name of the synonym file
	 */
	HashSynonyms(String fileName) {
		this.classMap = hashmapSynonyms(fileName); 
	}
	
	/**
	 * A getter method which returns class map synonym representation
	 * @return class instance variable containing hash map of synonyms
	 */
	public HashMap<String, Integer> getClassMap() {
		return classMap;
	}

	/**
	 * Creates hash map of the synonyms file mapping synonyms of each other to the same number
	 * @param fileName - name of the synonym file
	 */
	private static HashMap<String, Integer> hashmapSynonyms (String fileName) {
		HashMap<String, Integer> synonyms = new HashMap<String, Integer>();
		BufferedReader br = null;
		
		//to give a unique value to each group of synonyms
		int synonymValue = 0;
		String line;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			
			//read through synonyms file
			while((line = br.readLine()) != null) {
				String[] splitLines = line.split("\\s+");
				for(int i = 0; i < splitLines.length; i++) {
					//insert each synonym into map with its unique group value
					synonyms.put(splitLines[i].trim().toLowerCase(), synonymValue);
				}
				//to ensure no two groups of synonyms have the same value
				synonymValue++;
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return synonyms;
	}
}
# PlagiarismDetector
Command-line program for plagiarism detection using a N-tuple comparison algorithm allowing for synonyms in the text. 

## Main File
PlagiarismDetector.java

## Arguments    
1. Synonym File (required)  
2. File 1  (required)  
3. File 2  (required)   
4. Tuple Size (optional: default size is 3)  

## Algorithm
The program takes a file containing synonyms (each group of synonyms is on the same line), two files to check similarities between, and an optional argument indicating the tuple size, which defaults to 3. The synonym file is used to create a hash map where each group of synonyms (or each line) is given a unique value. Thus, if two words have the same value, we know they are synonyms. The two files to be checked are used to create ArrayList objects which contain ArrayLists, each representing a tuple. The tuples are iterated through and each word in the tuples is checked for existence in the synonym hash map. If the word exists in the synonym hash map, we change the word to its value, else we keep it the same. Then we iterate through the 2 ArrayLists representing the n-tuples from the the two files and check for equivalence.

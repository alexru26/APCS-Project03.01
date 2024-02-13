/**
 * SubWordInterface is a simple interface that forces students 
 * to implement and comply with public method specifications.
 * @author Julian Cochran
 * @version December 7, 2014
 */

import java.util.ArrayList;

public interface WordFinder {
	/**
	 * Populates the dictionary from the text file contents
	 * The dictionary object should contain 26 buckets, each
	 * bucket filled with an ArrayList<String>
	 * The String objects in the buckets are sorted A-Z because
	 * of the nature of the text file words.txt
	 */
	void populateDictionary();
	
	/**
	 * Retrieve all SubWord objects from the dictionary.
	 * A SubWord is defined as a word that can be split into two
	 * words that are also found in the dictionary.  The words
	 * MUST be split evenly, e.g. no unused characters.
	 * For example, "baseball" is a SubWord because it contains
	 * "base" and "ball" (no unused characters)
	 * To do this, you must look through every word in the dictionary
	 * to see if it is a SubWord object
	 * @return An ArrayList containing the SubWord objects
	 * pulled from the file words.txt
	 */
	ArrayList<SubWord> getSubWords();
	
	/**
	 * Look through the entire dictionary object to see if 
	 * word exists in dictionary
	 * @param word The item to be searched for in dictionary
	 * @return true if word is in dictionary, false otherwise
	 * NOTE: EFFICIENCY O(log N) vs O(N) IS A BIG DEAL HERE!!!
	 * You MAY NOT use Collections.binarySearch() here; you must use
	 * YOUR OWN DEFINITION of a binary search in order to receive
	 * the credit as specified on the grading rubric.
	 */
	boolean inDictionary(String word);
}

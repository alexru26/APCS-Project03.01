import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * SubWordFinder that imports words, creates dictionary, finds all subwords, and writes to new file
 * @author Alex Ru
 * @version 02.13.24
 * After the program is run the subwords are written into the file called "subwords.txt"
 */
public class SubWordFinder implements WordFinder {
    private ArrayList<ArrayList<String>> dictionary;
    private String alpha = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Default constructor, initializes dictionary
     */
    public SubWordFinder() {
        dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            dictionary.add(new ArrayList<>());
        }
    }

    /**
     * Import all words, sort into buckets, and sort each bucket
     */
    public void populateDictionary() {
        Scanner in;
        try {
            in = new Scanner(new File("new_scrabble.txt"));
            String line;
            while (in.hasNext()) {
                line = in.nextLine();
                dictionary.get(alpha.indexOf(line.charAt(0))).add(line);
            }
            in.close();
            for (int i = 0; i < 26; i++) {
                Collections.sort(dictionary.get(i));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private int indexOf(String word) {
        ArrayList<String> bucket = dictionary.get(alpha.indexOf(word.charAt(0)));
        int left = 0;
        int right = bucket.size() - 1;
        int index;
        String check = "";
        while(left <= right) {
            index = left + (right - left) / 2;
            check = bucket.get(index);
            if(check.equals(word)) return index;
            else if(check.compareTo(word) < 0) left = index + 1;
            else right = index - 1;
        }
        return -1;
    }

    /**
     * Find subwords in words
     * @return ArrayList of subwords
     */
    public ArrayList<SubWord> getSubWords() {
        ArrayList<SubWord> res = new ArrayList<>();
        String check1, check2;
        for (ArrayList<String> bucket : dictionary) {
            for (String word : bucket) {
                for (int i = 2; i < word.length() - 1; i++) {
                    check1 = word.substring(0, i);
                    check2 = word.substring(i);
                    if (inDictionary(check1) && inDictionary(check2)) {
                        res.add(new SubWord(word, check1, check2));
                    }
                }
            }
        }
        return res;
    }

    /**
     * Find word is in dictionary and return true if in dictionary and false if not
     * @param word The item to be searched for in dictionary
     * @return in or not in dictionary
     */
    public boolean inDictionary(String word) {
        return indexOf(word) >= 0;
    }

    /**
     * Main entry point of SubWordFinder class
     * @param args command line arguments if necessary
     */
    public static void main(String[] args) throws FileNotFoundException {
        SubWordFinder obj = new SubWordFinder();
        obj.populateDictionary();
        ArrayList<SubWord> subwords = obj.getSubWords();
        HashMap<String, Integer> occurences = new HashMap<>(); // oh my goodness gracious a hashmap??????
        try {
            PrintWriter file = new PrintWriter("subwords.txt");
            for(SubWord a : subwords) {
                file.println(a);
                // extra here!!!
                if(!occurences.containsKey(a.rootWord)) occurences.put(a.rootWord, 1);
                else occurences.replace(a.rootWord, occurences.get(a.rootWord)+1);
            }
            file.close();
        } catch(Exception e) { e.printStackTrace(); }

        // extra here!!!
        int max = Collections.max(occurences.values());
        System.out.println("the greatest number of SubWord combinations is " + max + "\n");
        occurences.forEach((key, value) -> {
            if(value == max) System.out.println(key + " has the most SubWord combinations");
        });
    }
}

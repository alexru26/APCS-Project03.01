/**
 * SubWord class
 * @author Alex Ru
 * @version 02.13.24
 */
public class SubWord implements Comparable<SubWord>{
    public String rootWord, sub1, sub2;

    /**
     * Default constructor for class SubWord
     * @param original original string
     * @param s1 subword 1
     * @param s2 subword 2
     */
    public SubWord(String original, String s1, String s2) {
        rootWord = original;
        sub1 = s1;
        sub2 = s2;
    }

    /**
     * Displays SubWord as string
     * @return string that shows rootWord, sub1, sub2
     */
    public String toString() {
        return rootWord + " = " + sub1 + " + " + sub2;
    }

    /**
     * Method to compare SubWord to another SubWord based on rootWord
     * @param o the object to be compared.
     * @return negative int for less, positive int for more, 0 for equal
     */
    public int compareTo(SubWord o) {
        return this.rootWord.compareTo(o.rootWord);
    }
}

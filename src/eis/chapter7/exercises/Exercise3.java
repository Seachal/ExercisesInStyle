package eis.chapter7.exercises;

public class Exercise3 {

    public static int f(String s, char c) {
        int i = 0, n = 0;
        boolean flag = true;
        while (flag) {
            if (s.charAt(i) == c)
                n++;
            if (i == s.length() -1)
                flag = false;
            else
                i++;
        }
        return n;
    }

    /** SPOILER ALERT: The solution follows! **/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /** Counts the number of occurrences of a character in a string.
     *
     * @param s  a string
     * @param c  a character
     * @return   The number of occurrences of {@code c} in {@code s}
     */
    public static int countOccurrences(String s, char c) {
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
    
    /** Counts the number of occurrences of a character in a string, using streams.
     *
     * @param s  a string
     * @param c  a character
     * @return   The number of occurrences of {@code c} in {@code s}
     */
    public static int countOccurrencesWithStreams(String s, char c) {
        return (int) s.chars().filter(character -> character == c).count();
    }

    public static void main(String ... args) {
        if (args.length < 2) {
            System.out.println("Please provide two arguments from the command line.");
            return;
        }
        System.out.println(f(args[0], args[1].charAt(0)));
        System.out.println(countOccurrences(args[0], args[1].charAt(0)));
        System.out.println(countOccurrencesWithStreams(args[0], args[1].charAt(0)));
    }
}

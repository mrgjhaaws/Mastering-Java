// Chapter 4: Creating and Using Java Strings
// Topic: String immutability, comparison, and common methods.

/*
 UNSOLVED (exercise) -- compiles, but prints the WRONG answer.
 Task: this method is supposed to reverse a string in place, but
 Strings are immutable in Java -- you can't mutate one. Find the bug
 (it's a logic bug, not a compile error) and fix it in the SOLVED version.

 static String reverseBroken(String input) {
     for (int i = 0; i < input.length() / 2; i++) {
         char temp = input.charAt(i);
         // this line does nothing useful: Strings can't be indexed-assigned
         // input.charAt(input.length() - 1 - i) = temp;  // <-- won't even compile
     }
     return input; // <-- always returns the ORIGINAL, unreversed string
 }
*/

// SOLVED
public class StringDemo {

    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        String c = new String("hello");

        // == compares references; .equals() compares content
        System.out.println("a == b        : " + (a == b));       // true (string pool)
        System.out.println("a == c        : " + (a == c));       // false (new object)
        System.out.println("a.equals(c)   : " + a.equals(c));    // true

        System.out.println();
        System.out.println("reversed      : " + reverse("hello"));
        System.out.println("upper         : " + "hello".toUpperCase());
        System.out.println("trimmed       : [" + "  padded  ".trim() + "]");
        System.out.println("replace       : " + "banana".replace('a', 'o'));
        System.out.println("substring(1,4): " + "hello".substring(1, 4));

        // Immutability demo: concatenation makes a NEW String, doesn't mutate the old one
        String original = "cat";
        String changed = original.concat("erpillar");
        System.out.println();
        System.out.println("original still: " + original);
        System.out.println("changed is    : " + changed);
    }

    /** Builds and returns a NEW reversed string; the input is untouched. */
    static String reverse(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }
}

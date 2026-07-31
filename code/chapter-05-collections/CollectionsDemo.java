// Chapter 5: Collections, Lists, and Java's Built-in APIs
// Topic: arrays vs. List vs. Set vs. Map.

/*
 UNSOLVED (exercise) -- will NOT compile.
 Task: two bugs. (1) arrays have fixed size -- .add() isn't a thing on
 arrays. (2) a Set should reject duplicates, but this uses a List type
 for something that needs uniqueness.

 public class CollectionsExercise {
     public static void main(String[] args) {
         int[] scores = new int[3];
         scores.add(90);              // <-- bug: arrays don't have .add()

         List<String> uniqueTags = new ArrayList<>();  // <-- bug: allows duplicates
         uniqueTags.add("java");
         uniqueTags.add("java");      // silently duplicates, defeats the purpose
     }
 }
*/

// SOLVED
import java.util.*;

public class CollectionsDemo {

    public static void main(String[] args) {
        // Arrays: fixed size, set at creation
        int[] scores = { 90, 85, 77 };
        System.out.println("Array length (fixed): " + scores.length);

        // List: ordered, resizable, allows duplicates
        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("Java"); // duplicates allowed
        System.out.println("List (allows dupes): " + languages);

        // Set: no duplicates, no guaranteed order (HashSet)
        Set<String> uniqueTags = new HashSet<>();
        uniqueTags.add("java");
        uniqueTags.add("beginner");
        uniqueTags.add("java"); // silently ignored, size stays 2
        System.out.println("Set (unique only)   : " + uniqueTags);
        System.out.println("Set size            : " + uniqueTags.size());

        // Map: key-value pairs, keys unique
        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("java", 3);
        wordCounts.put("python", 1);
        wordCounts.put("java", 5); // overwrites the previous value for "java"
        System.out.println("Map (key -> value)  : " + wordCounts);
        System.out.println("Value for 'java'    : " + wordCounts.get("java"));

        // Iterating a Map
        System.out.println();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println("  " + entry.getKey() + " => " + entry.getValue());
        }
    }
}

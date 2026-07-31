// Chapter 9: Java Streams
// Topic: the java.util.stream API for filtering/mapping/collecting data.

/*
 UNSOLVED (exercise) -- will NOT compile.
 Task: streams are single-use (once a terminal operation runs, the
 stream is "consumed"). This reuses one stream twice.

 List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
 Stream<Integer> evens = numbers.stream().filter(n -> n % 2 == 0);
 long count = evens.count();               // first terminal op: OK
 List<Integer> list = evens.collect(...);  // <-- bug: stream already consumed,
                                            //     throws IllegalStateException
*/

// SOLVED
import java.util.*;
import java.util.stream.*;

public class StreamsDemo {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // A fresh stream per terminal operation -- streams can't be reused.
        long evenCount = numbers.stream().filter(n -> n % 2 == 0).count();
        List<Integer> evenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);

        System.out.println("Even count      : " + evenCount);
        System.out.println("Even squares    : " + evenSquares);
        System.out.println("Sum of all      : " + sum);
        System.out.println("Max             : " + max.orElse(-1));

        // Grouping: a common real-world stream use case
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry");
        Map<Character, List<String>> byFirstLetter = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));
        System.out.println();
        System.out.println("Grouped by first letter: " + byFirstLetter);

        // Chained pipeline: filter -> map -> sorted -> collect, all lazy until collect() runs
        List<String> shortUppercaseWords = words.stream()
                .filter(w -> w.length() <= 6)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Short words, uppercase, sorted: " + shortUppercaseWords);
    }
}

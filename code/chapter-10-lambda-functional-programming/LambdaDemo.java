// Chapter 10: Functional Programming with Lambda Expressions
// Topic: functional interfaces, lambdas, and method references.

/*
 UNSOLVED (exercise) -- will NOT compile.
 Task: a lambda can only implement an interface with exactly ONE
 abstract method (a "functional interface"). This one has two.

 @FunctionalInterface
 interface Calculator {
     int apply(int a, int b);
     int reset();          // <-- bug: a second abstract method breaks
                            //     the "functional interface" contract
 }

 Calculator adder = (a, b) -> a + b;  // won't compile: reset() unimplemented
*/

// SOLVED
import java.util.function.*;
import java.util.*;

public class LambdaDemo {

    @FunctionalInterface
    interface Calculator {
        int apply(int a, int b);
    }

    public static void main(String[] args) {
        // A custom functional interface implemented with a lambda
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        System.out.println("add(3, 4)      = " + add.apply(3, 4));
        System.out.println("multiply(3, 4) = " + multiply.apply(3, 4));

        // Standard functional interfaces from java.util.function
        Function<Integer, Integer> square = n -> n * n;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Supplier<String> greeting = () -> "Hello from a Supplier!";
        Consumer<String> printer = s -> System.out.println("Consumed: " + s);
        BiFunction<Integer, Integer, Integer> max = Math::max; // method reference

        System.out.println();
        System.out.println("square.apply(5)   = " + square.apply(5));
        System.out.println("isEven.test(7)    = " + isEven.test(7));
        System.out.println("greeting.get()    = " + greeting.get());
        printer.accept("some text");
        System.out.println("max.apply(3, 9)   = " + max.apply(3, 9));

        // Pure function: same input always gives same output, no side effects
        Function<Integer, Integer> pureDouble = n -> n * 2;
        System.out.println();
        System.out.println("Pure function called twice with 5: "
                + pureDouble.apply(5) + ", " + pureDouble.apply(5));

        // Composing functions
        Function<Integer, Integer> squareThenDouble = square.andThen(pureDouble);
        System.out.println("square(3) then double = " + squareThenDouble.apply(3));
    }
}

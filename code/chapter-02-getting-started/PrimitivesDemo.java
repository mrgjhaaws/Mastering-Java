// Chapter 2: Getting Started with Java
// Topic: primitive data types, and reading the classpath/environment.

/*
 UNSOLVED (exercise) -- will NOT compile.
 Task: three of these primitive declarations are wrong (either the type
 can't hold the value, or the literal syntax is invalid). Find all three.

 public class PrimitivesExercise {
     public static void main(String[] args) {
         byte smallNumber = 200;        // <-- bug: out of range for byte
         float price = 19.99;           // <-- bug: needs an 'f' suffix
         char grade = "A";              // <-- bug: char needs single quotes
         int count = 42;
         boolean isReady = true;
         System.out.println(smallNumber + " " + price + " " + grade);
     }
 }
*/

// SOLVED
public class PrimitivesDemo {

    public static void main(String[] args) {
        byte smallNumber = 100;      // byte: -128 to 127
        short mediumNumber = 30000;  // short: -32,768 to 32,767
        int count = 42;              // int: ~-2.1B to 2.1B
        long bigNumber = 9_000_000_000L; // long: needs the L suffix
        float price = 19.99f;        // float: needs the f suffix
        double preciseValue = 19.999999;
        char grade = 'A';            // char: single quotes, one character
        boolean isReady = true;

        System.out.println("byte:    " + smallNumber);
        System.out.println("short:   " + mediumNumber);
        System.out.println("int:     " + count);
        System.out.println("long:    " + bigNumber);
        System.out.println("float:   " + price);
        System.out.println("double:  " + preciseValue);
        System.out.println("char:    " + grade);
        System.out.println("boolean: " + isReady);

        printClasspathInfo();
    }

    static void printClasspathInfo() {
        System.out.println();
        System.out.println("java.class.path = " + System.getProperty("java.class.path"));
        System.out.println("(This is what javac/java search to find your .class files.)");
    }
}

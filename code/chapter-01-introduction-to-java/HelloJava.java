// Chapter 1: Introduction to Java
// Topic: your first Java program, and why "write once, run anywhere" works.
//
// This file is split into two parts:
//   1) UNSOLVED  - a broken exercise. Find and fix the compile error.
//   2) SOLVED    - the corrected, working version.
//
// To try the exercise yourself: comment out the SOLVED class below,
// uncomment the UNSOLVED one, fix it, then compare with the solution.

/*
 UNSOLVED (exercise) -- this version will NOT compile as-is.
 Task: the entry point signature is wrong. Find the bug and fix it
 so the program prints a welcome message.

 public class HelloJava {
     public static void Main(String args[]) {   // <-- bug is here
         System.out.println("Hello, Java!");
         System.out.println("Running on: " + System.getProperty("os.name"));
     }
 }
*/

// SOLVED
public class HelloJava {

    public static void main(String[] args) {
        System.out.println("Hello, Java!");
        System.out.println("Running on: " + System.getProperty("os.name"));
        System.out.println("Java version: " + System.getProperty("java.version"));
        describeJava();
    }

    /**
     * A few of the language features covered in Chapter 1:
     * platform independence, being compiled AND interpreted,
     * and automatic memory management.
     */
    static void describeJava() {
        System.out.println();
        System.out.println("Why Java is considered platform-independent:");
        System.out.println("1. Source code (.java) compiles to bytecode (.class)");
        System.out.println("2. Bytecode runs on any machine with a matching JVM");
        System.out.println("3. The JVM, not your OS, is what the bytecode targets");
    }
}

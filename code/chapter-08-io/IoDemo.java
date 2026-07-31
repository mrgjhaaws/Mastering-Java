// Chapter 8: Java I/O
// Topic: reading/writing files, and closing streams safely.

/*
 UNSOLVED (exercise) -- compiles, but leaks a file handle and doesn't
 flush the writer, so data may not actually reach the file.

 static void writeBroken(String path, String content) throws IOException {
     FileWriter writer = new FileWriter(path);
     writer.write(content);
     // bug: never calls flush() or close() -- data may be lost, and
     // the file handle is never released
 }
*/

// SOLVED
import java.io.*;
import java.nio.file.*;

public class IoDemo {

    public static void main(String[] args) throws IOException {
        Path tempFile = Files.createTempFile("chapter8-demo", ".txt");

        writeText(tempFile.toString(), "Hello from Java I/O!\nSecond line.");
        String contents = readText(tempFile.toString());

        System.out.println("Wrote and read back:");
        System.out.println(contents);

        // Modern alternative: java.nio.file for simple whole-file operations
        System.out.println();
        System.out.println("Same thing via java.nio.file.Files:");
        System.out.println(Files.readString(tempFile));

        Files.deleteIfExists(tempFile);
    }

    /** Classic java.io approach, with guaranteed close via try-with-resources. */
    static void writeText(String path, String content) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        } // writer.close() (which flushes) is called automatically here
    }

    static String readText(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString().stripTrailing();
    }
}

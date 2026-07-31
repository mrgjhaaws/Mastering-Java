# Chapter 8 — Java I/O

## Notes

Java I/O revolves around **streams** — sequences of bytes (or, for text,
characters) flowing from a source (file, network socket, memory) to a
destination. `InputStream`/`Reader` are for reading; `OutputStream`/
`Writer` are for writing. The byte-oriented classes (`InputStream`,
`OutputStream`) handle raw binary data; the character-oriented classes
(`Reader`, `Writer`) handle text and deal with character encoding.

**Buffering matters for performance.** `FileReader` alone reads one
character at a time from disk, which is slow. Wrapping it in a
`BufferedReader` (as in this chapter's solved code) reads larger chunks
into memory and serves individual reads from that buffer — the same
pattern applies to `BufferedWriter`/`BufferedOutputStream`.

**Closing streams is not optional.** An unclosed `FileWriter` may hold
data in an internal buffer that never gets flushed to disk, meaning
your program can *appear* to succeed while the file ends up empty or
truncated. It also holds an OS-level file handle open, which is a
finite resource — enough leaked handles and a long-running program
starts failing to open new files. `try-with-resources` (Java 7+) is
the modern fix: any resource implementing `AutoCloseable` gets closed
automatically when the try block exits.

**Modern alternative:** for simple whole-file reads/writes,
`java.nio.file.Files` (`Files.readString()`, `Files.writeString()`,
`Files.createTempFile()`) is shorter and less error-prone than the
classic `java.io` stream classes — reach for `java.io` when you need
fine-grained control over reading/writing incrementally.

## Q&A

**Q1: What's the practical risk of never calling `.close()` (or
`.flush()`) on a `FileWriter`?**
A: Buffered data may never actually reach the file, and the underlying
OS file handle stays open — the program can look like it succeeded
while the file is empty or incomplete.

**Q2: What does `try-with-resources` do for a `FileWriter`?**
A: It automatically calls `.close()` (which also flushes any buffered
data) when the try block ends, whether that's from normal completion
or an exception — no need for a manual `finally` block.

**Q3: Why wrap a `FileReader` in a `BufferedReader` instead of reading
directly?**
A: `FileReader` alone reads from disk one character at a time, which is
slow. `BufferedReader` reads larger chunks into an internal buffer and
serves reads from memory, dramatically reducing the number of actual
disk operations.

**Q4: What's the difference between `InputStream`/`OutputStream` and
`Reader`/`Writer`?**
A: The `Stream` classes work with raw bytes (binary data, any
encoding). The `Reader`/`Writer` classes work with characters and
handle text encoding conversion for you.

**Q5: When would you reach for `java.nio.file.Files` instead of
`java.io.FileWriter`/`FileReader`?**
A: For simple, whole-file operations (read it all, write it all) where
you don't need incremental control — `Files.readString()` /
`Files.writeString()` are one-liners that handle opening, buffering,
and closing internally.

**Q6: In the solved `readText` method, why does the loop check
`(line = reader.readLine()) != null` instead of something like
`while (reader.hasNext())`?**
A: `BufferedReader` doesn't have a `hasNext()` method — `readLine()`
itself returns `null` when the stream is exhausted, so checking for
`null` inside the loop condition is the idiomatic way to detect
end-of-file.

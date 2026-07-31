# Chapter 1 — Introduction to Java

## Notes

Java is a general-purpose, class-based, object-oriented language designed
so compiled code can run on any device with a Java Virtual Machine (JVM),
regardless of the underlying hardware or operating system — the
"write once, run anywhere" idea.

**How it works:** source files (`.java`) are compiled by `javac` into
platform-neutral bytecode (`.class` files). At runtime, the JVM
interprets (or JIT-compiles) that bytecode into native instructions for
the host machine. This two-step process is why Java is described as
*both* compiled and interpreted.

**Core language traits:**
- **Simple** — syntax derived from C/C++ but with pointers, manual memory
  management, and operator overloading removed.
- **Object-oriented** — everything (aside from primitives) is modeled as
  an object with state and behavior.
- **Platform-independent / portable** — bytecode + JVM, not native
  machine code.
- **Robust & safe** — strong compile-time type checking, automatic
  garbage collection, and runtime exception handling reduce whole
  categories of bugs (no dangling pointers, no manual `free()`).
- **Multithreaded** — concurrency primitives are part of the core
  language and standard library, not a bolt-on.
- **High performance (for a managed language)** — JIT compilation gets
  bytecode close to native speed after warm-up.

**Why it matters in industry:** these traits are exactly why Java became
the default choice for large, long-lived enterprise systems — backend
services, Android apps, and big-data tooling all lean on portability and
memory safety more than raw peak speed.

## Q&A

**Q1: What's the difference between the JDK, JRE, and JVM?**
A: The JVM executes bytecode. The JRE bundles the JVM plus the standard
class libraries needed to *run* Java programs. The JDK adds the
development tools (`javac`, debugger, etc.) needed to *build* them.

**Q2: Why is Java called "compiled and interpreted"?**
A: `javac` compiles source into bytecode (compilation step); the JVM
then interprets/JIT-compiles that bytecode at runtime (interpretation
step). Both stages happen, just at different times.

**Q3: What makes Java "platform-independent" if the JVM itself is
platform-specific?**
A: The *bytecode* is platform-neutral — the same `.class` file runs
unmodified on any OS. Only the JVM implementation differs per platform;
your compiled code doesn't.

**Q4: Name two ways Java's design reduces memory-related bugs compared
to C/C++.**
A: No manual pointer arithmetic, and automatic garbage collection
instead of manual `malloc`/`free`.

**Q5: Is Java multithreading a library feature or a language feature?**
A: Both — threading primitives (`Thread`, `synchronized`, memory model
guarantees) are part of the language spec, with a much larger
concurrency toolkit (`java.util.concurrent`) in the standard library.

**Q6: What does "robust" mean in the context of Java's design goals?**
A: Strong static typing at compile time, plus structured exception
handling at runtime, so many classes of errors are caught before or
during execution instead of causing silent corruption.

**Q7: What's the entry point of every standalone Java program, and why
does its signature matter?**
A: `public static void main(String[] args)`. The JVM looks for this
exact signature to start execution — get the capitalization or
parameter type wrong (like the exercise in this chapter's code file)
and the class won't run as a program, even if it compiles.

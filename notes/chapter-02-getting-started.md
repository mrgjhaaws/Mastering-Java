# Chapter 2 — Getting Started with Java

## Notes

Before writing real programs, two things need to be in place: a working
JDK on your `PATH`, and a clear mental model of Java's primitive types.

**Environment basics:** `JAVA_HOME` points at your JDK install;
`PATH` needs `JAVA_HOME/bin` so `java`/`javac` are runnable from any
terminal; `CLASSPATH` tells the JVM where to look for compiled `.class`
files and `.jar` dependencies. Most modern IDEs manage all three for
you, but it's worth knowing what they mean when a build fails with
`ClassNotFoundException`.

**The 8 primitive types**, grouped by what they hold:
- Whole numbers: `byte` (1 byte), `short` (2 bytes), `int` (4 bytes),
  `long` (8 bytes, needs an `L` suffix for literals over `int` range)
- Decimals: `float` (4 bytes, needs an `f` suffix), `double` (8 bytes,
  the default for decimal literals)
- Text: `char` (a single UTF-16 code unit, single quotes)
- Logic: `boolean` (`true`/`false`, no numeric equivalent in Java)

Unlike some languages, Java won't silently truncate a value that
doesn't fit its declared type — assigning `200` to a `byte` is a
compile error, not a silent overflow, because the compiler checks
range at compile time for literals.

**Compilation vs. execution, concretely:** `javac Foo.java` produces
`Foo.class` (bytecode). `java Foo` starts the JVM, loads that class,
and calls its `main` method. Nothing runs until that second step.

## Q&A

**Q1: Why does `byte smallNumber = 200;` fail to compile?**
A: `byte` can only hold -128 to 127. The compiler catches this at
compile time for constant literals, not at runtime.

**Q2: Why do `float` literals need an `f` suffix but `double` literals
don't?**
A: Decimal literals default to `double` in Java. Without the `f`
suffix, `19.99` is treated as a `double`, which is wider than `float`
and can't be assigned to a `float` variable without an explicit cast.

**Q3: What's the difference between `JAVA_HOME`, `PATH`, and
`CLASSPATH`?**
A: `JAVA_HOME` locates the JDK install. `PATH` makes the `java`/`javac`
executables runnable from the terminal. `CLASSPATH` tells the JVM
where to search for compiled classes and libraries at runtime.

**Q4: What error would you expect from `char grade = "A";`?**
A: A compile error — double quotes create a `String`, not a `char`.
`char` literals require single quotes: `'A'`.

**Q5: Why does `long bigNumber = 9_000_000_000L;` need the `L` suffix?**
A: Integer literals default to `int`, which maxes out around 2.1
billion. Without `L`, the literal itself would overflow `int` before
it's ever assigned to the `long` variable.

**Q6: What do the underscores in `9_000_000_000L` do?**
A: Nothing functionally — they're a readability feature (since Java 7)
that lets you group digits like you would with commas, without
affecting the value.

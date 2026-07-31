# Mastering Java — Study Companion

An original, runnable companion to a 10-chapter beginner's Java
curriculum: independently written code examples, chapter notes, and
Q&A for each topic — organized so you can read, run, and test yourself
chapter by chapter.

> **Note on originality:** the code, notes, and Q&A in this repo are
> written from scratch to cover the same topics as a standard
> beginner Java curriculum. Nothing here is copied from any book —
> every example, explanation, and question was independently authored
> for this repo.

## Structure

```
/code/    — one folder per chapter, each with a runnable, compiled-and-
            tested .java file. Every file contains a commented-out
            UNSOLVED (buggy) exercise followed by the SOLVED, working
            version, so you can try fixing the bug yourself first.
/notes/   — one markdown file per chapter: a concept summary plus
            6-7 original Q&A pairs to test your understanding.
```

## Chapters

| # | Topic | Code | Notes |
|---|-------|------|-------|
| 1 | Introduction to Java | [`code/chapter-01-introduction-to-java/`](code/chapter-01-introduction-to-java/HelloJava.java) | [notes](notes/chapter-01-introduction-to-java.md) |
| 2 | Getting Started with Java | [`code/chapter-02-getting-started/`](code/chapter-02-getting-started/PrimitivesDemo.java) | [notes](notes/chapter-02-getting-started.md) |
| 3 | Object-Oriented Programming | [`code/chapter-03-oop/`](code/chapter-03-oop/OopDemo.java) | [notes](notes/chapter-03-oop.md) |
| 4 | Creating and Using Java Strings | [`code/chapter-04-strings/`](code/chapter-04-strings/StringDemo.java) | [notes](notes/chapter-04-strings.md) |
| 5 | Collections, Lists, and Built-in APIs | [`code/chapter-05-collections/`](code/chapter-05-collections/CollectionsDemo.java) | [notes](notes/chapter-05-collections.md) |
| 6 | Libraries, Packages, and Modules | [`code/chapter-06-libraries-packages-modules/`](code/chapter-06-libraries-packages-modules/MathHelper.java) | [notes](notes/chapter-06-libraries-packages-modules.md) |
| 7 | Java Database Connectivity (JDBC) | [`code/chapter-07-jdbc/`](code/chapter-07-jdbc/JdbcDemo.java) | [notes](notes/chapter-07-jdbc.md) |
| 8 | Java I/O | [`code/chapter-08-io/`](code/chapter-08-io/IoDemo.java) | [notes](notes/chapter-08-io.md) |
| 9 | Java Streams | [`code/chapter-09-streams/`](code/chapter-09-streams/StreamsDemo.java) | [notes](notes/chapter-09-streams.md) |
| 10 | Functional Programming with Lambda Expressions | [`code/chapter-10-lambda-functional-programming/`](code/chapter-10-lambda-functional-programming/LambdaDemo.java) | [notes](notes/chapter-10-lambda-functional-programming.md) |

## Running any chapter

```bash
cd code/chapter-XX-slug
javac *.java
java <ClassNameWithMain>
```

Every file compiles cleanly under Java 21 and has been run to confirm
its output.

## LinkedIn series

Each chapter also has a companion LinkedIn post summarizing its core
lesson — links are collected in [`LINKEDIN.md`](LINKEDIN.md) once
published.

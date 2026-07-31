# Chapter 9 — Java Streams

## Notes

`java.util.stream` (Java 8+) is a declarative API for processing
sequences of data: instead of writing manual loops with mutable
accumulator variables, you describe a pipeline of operations and let
the runtime execute it.

**Three kinds of operations:**
- **Source** — `list.stream()` turns a collection into a stream.
- **Intermediate** — `.filter()`, `.map()`, `.sorted()` — these are
  *lazy*: nothing actually runs until a terminal operation is called.
- **Terminal** — `.collect()`, `.count()`, `.sum()`, `.forEach()` —
  these trigger execution and produce a final result.

**Streams are single-use.** Once a terminal operation runs, that stream
object is "consumed" — calling another operation on it throws
`IllegalStateException`. This trips up beginners coming from
languages where you can iterate the same collection repeatedly: with
streams, you create a *new* stream (`list.stream()` again) for each
independent pipeline, as shown throughout this chapter's solved code.

**Laziness has a real consequence:** because intermediate operations
don't run until a terminal operation appears, building a pipeline and
never calling a terminal operation on it does *nothing* — no exception,
no output, just silently no-op code. This is a common "why isn't my
filter working" bug.

**Common terminal operations worth knowing:** `.collect(Collectors.toList())`
turns a stream back into a `List`; `.reduce()` combines elements into a
single value; `Collectors.groupingBy()` partitions elements by a key
function into a `Map` of lists.

## Q&A

**Q1: Why does calling `.collect()` on a stream that already had
`.count()` called on it throw an exception?**
A: Streams are single-use — a terminal operation (`.count()`) consumes
the stream. Any further operation on that same stream object throws
`IllegalStateException`; you'd need a fresh `list.stream()` call.

**Q2: What's the difference between an intermediate and a terminal
operation?**
A: Intermediate operations (`.filter()`, `.map()`) are lazy and return
a new stream — nothing executes yet. Terminal operations (`.collect()`,
`.count()`, `.forEach()`) actually trigger the pipeline to run and
produce a result.

**Q3: If you build a filter/map pipeline but never call a terminal
operation, what happens?**
A: Nothing — no error, no output. Because intermediate operations are
lazy, a pipeline with no terminal operation simply never executes.

**Q4: What does `Collectors.groupingBy(w -> w.charAt(0))` produce?**
A: A `Map` where each key is the first character returned by the
lambda, and each value is a `List` of all stream elements that shared
that key.

**Q5: In `numbers.stream().mapToInt(Integer::intValue).sum()`, why is
`mapToInt` needed instead of just calling `.sum()` on the original
stream?**
A: `Stream<Integer>` has no `.sum()` method — only the primitive
specializations (`IntStream`, `LongStream`, `DoubleStream`) do.
`mapToInt` converts the boxed `Integer` stream into an `IntStream` that
supports numeric terminal operations like `.sum()`.

**Q6: Why is stream order in the pipeline
(`.filter().map().sorted().collect()`) meaningful?**
A: Each stage processes only the elements passed on by the previous
stage — filtering before mapping means fewer elements get mapped;
sorting before collecting determines the final list's order. Reordering
these can change both performance and output.

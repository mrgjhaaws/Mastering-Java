# Chapter 10 — Functional Programming with Lambda Expressions

## Notes

Lambda expressions (Java 8+) let you write a function's implementation
inline, without a full named class — but only against a **functional
interface**: an interface with exactly one abstract method. That's the
whole contract: the lambda's parameters and body become that one
method's implementation.

**`@FunctionalInterface`** is an optional but useful annotation — it
doesn't change behavior, but makes the compiler enforce the
single-abstract-method rule, turning an accidental second abstract
method into a clear compile error instead of a confusing failure at
the lambda call site.

**Standard functional interfaces (`java.util.function`)** cover most
common shapes so you rarely need to declare your own:
- `Function<T,R>` — takes a `T`, returns an `R` (`.apply()`)
- `Predicate<T>` — takes a `T`, returns `boolean` (`.test()`)
- `Supplier<T>` — takes nothing, returns a `T` (`.get()`)
- `Consumer<T>` — takes a `T`, returns nothing (`.accept()`)
- `BiFunction<T,U,R>` — takes two args, returns an `R`

**Method references** (`Math::max`) are shorthand for a lambda that
just calls an existing method — `(a, b) -> Math.max(a, b)` and
`Math::max` are equivalent, but the reference form is more concise
when the lambda body is *only* a method call.

**"Pure function"** means: given the same input, always returns the
same output, with no observable side effects (no mutating external
state, no I/O). Pure functions are easy to test and reason about in
isolation — `pureDouble` in this chapter's code is pure; a lambda that
also logged to a file or mutated a shared list would not be.

## Q&A

**Q1: Why can't a lambda implement an interface with two abstract
methods?**
A: A lambda expression's body maps to exactly one method — there's no
syntax for a lambda to provide two separate method implementations at
once. Only true functional interfaces (one abstract method) work with
lambda syntax.

**Q2: What does `@FunctionalInterface` actually do?**
A: It's a compile-time check, not a behavior change — it makes the
compiler reject the interface if it doesn't have exactly one abstract
method, catching the mistake at the interface's declaration instead of
wherever a lambda later tries to implement it.

**Q3: What's the difference between `Function<T,R>` and
`BiFunction<T,U,R>`?**
A: `Function` takes one argument and returns a result; `BiFunction`
takes two arguments (of potentially different types) and returns a
result.

**Q4: Why is `Math::max` equivalent to `(a, b) -> Math.max(a, b)`?**
A: A method reference is shorthand for a lambda whose entire body is a
single existing method call with matching parameters — the compiler
expands `Math::max` into exactly that lambda form.

**Q5: What makes a function "pure," and why does it matter?**
A: Same input always produces same output, with no side effects (no
mutating shared state, no I/O). Pure functions are trivially testable
in isolation and safe to run in parallel, since they can't interfere
with anything outside themselves.

**Q6: What does `square.andThen(pureDouble)` do?**
A: It composes two `Function`s into one: the input first goes through
`square`, and that result is immediately passed into `pureDouble` —
`andThen` chains functions left-to-right in the order they're called.

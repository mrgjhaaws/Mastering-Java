# Chapter 3 — Object-Oriented Programming

## Notes

Java's four OOP pillars, in practical terms:

- **Encapsulation** — keep a class's internal state `private`, expose
  behavior through methods. The `legCount` field in this chapter's
  `Animal` example has no public setter; it's fixed at construction.
- **Inheritance** — `extends` lets a subclass reuse and specialize a
  parent's fields/methods. `Dog` and `Bird` both inherit `name` and
  `getLegCount()` from `Animal`, but override `makeSound()`.
- **Polymorphism** — code written against the parent type (`Animal`)
  automatically gets the right subclass behavior at runtime. The demo
  loop calls `a.makeSound()` without knowing or caring whether `a` is
  actually a `Dog` or a `Bird`.
- **Abstraction** — expose *what* an object does, hide *how*. Callers
  of `makeSound()` don't need to know each subclass's internal logic.

**Constructors matter here too:** `Dog`'s constructor calls
`super(name, 4)` to let `Animal`'s constructor do the actual field
assignment — a subclass constructor's first line is implicitly (or
explicitly) a call to the parent constructor.

**A common beginner mistake** is confusing `extends` (class
inheritance, single parent only) with `implements` (interface
contracts, multiple allowed) — using the wrong keyword, as in this
chapter's exercise, is a compile error, not a subtle bug.

## Q&A

**Q1: Why does `class Dog implements Animal` fail to compile?**
A: `Animal` is a class, not an interface. Classes are inherited with
`extends`; `implements` is only for interfaces.

**Q2: What's wrong with overriding `makeSound()` as
`makeSound(String extra)`?**
A: `@Override` requires an exact signature match with the parent
method. Adding a parameter creates a different (overloaded) method
instead of overriding — the compiler flags `@Override` as invalid here.

**Q3: Why is `legCount` declared `private` with no setter?**
A: That's encapsulation — a dog's leg count shouldn't change after
construction, so the class simply doesn't expose a way to mutate it.

**Q4: In the demo's `for (Animal a : animals)` loop, how does Java know
to call `Dog`'s `makeSound()` and not `Animal`'s?**
A: Dynamic dispatch — the JVM looks up the actual runtime type of the
object (`Dog` or `Bird`) to decide which overridden method to call,
regardless of the reference's declared type (`Animal`).

**Q5: What does `super(name, 4)` do in the `Dog` constructor?**
A: It calls `Animal`'s constructor with those two arguments, letting
the parent class handle its own field initialization instead of
duplicating that logic in `Dog`.

**Q6: Can a Java class extend more than one class?**
A: No — Java only allows single inheritance for classes. A class can,
however, implement multiple interfaces.

# Chapter 4 — Creating and Using Java Strings

## Notes

`String` is Java's most-used reference type, and its single most
important trait is **immutability**: once created, a `String`
object's character content can never change. Every "modifying" method
(`.concat()`, `.replace()`, `.toUpperCase()`, `.substring()`) returns a
brand-new `String` and leaves the original untouched.

**Why immutability exists:** it enables the *string pool* — literal
strings with the same content can safely share one object in memory
(`"hello" == "hello"` is `true`), which would be unsafe if strings were
mutable. It also makes strings safe to share across threads without
synchronization, and safe to use as `HashMap` keys (a mutable key
could corrupt the map's internal buckets if changed after insertion).

**`==` vs `.equals()`** is the single most common beginner bug source:
`==` compares object references (are these the same object in
memory?), while `.equals()` compares content (do these have the same
characters?). Two string literals with equal content usually share a
pooled reference (`==` true), but `new String("x")` always allocates a
fresh object, so `==` is `false` even though `.equals()` is `true`.

**If you need to build a string through many modifications** (like
reversing one character-by-character, or looping to build output),
use `StringBuilder` instead of repeated `String` concatenation —
`StringBuilder` is mutable, so it avoids allocating a new `String`
object on every intermediate step.

## Q&A

**Q1: Why can't you reverse a `String` "in place" the way you might
reverse an array?**
A: `String` objects are immutable — there's no method that mutates the
existing character sequence. Any reversal has to build and return a
new `String` (or use a mutable `StringBuilder`).

**Q2: Why does `a == b` return `true` for two `"hello"` literals but
`a == c` return `false` for `new String("hello")`?**
A: Literals are interned in the string pool, so identical literals
share one object. `new String(...)` always creates a distinct object
on the heap, bypassing the pool.

**Q3: When should you use `.equals()` instead of `==` for strings?**
A: Essentially always, unless you specifically need reference identity.
`.equals()` compares actual character content, which is what "are
these strings the same" almost always means in practice.

**Q4: What does `"cat".concat("erpillar")` do to the original `"cat"`
string?**
A: Nothing — `concat()` returns a new `String` ("caterpillar"). The
original `"cat"` reference still points to the unchanged three-letter
string.

**Q5: Why is `StringBuilder` preferred over `String` concatenation
inside a loop?**
A: Each `String` concatenation allocates a new object. In a loop, that
means many discarded intermediate strings. `StringBuilder` mutates one
internal buffer instead, which is far more efficient for repeated
modifications.

**Q6: Why does String immutability make strings safer as `HashMap`
keys?**
A: A key's hash code is computed from its content. If a key object
could be mutated after insertion, its hash code would change and the
map could no longer find it in the correct bucket — immutability
prevents that entire class of bug.

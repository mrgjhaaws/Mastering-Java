# Chapter 5 — Collections, Lists, and Java's Built-in APIs

## Notes

Four data structures, four different jobs:

- **Array** — fixed size, set once at creation. Fastest for indexed
  access, but you can't grow or shrink it; "resizing" means creating a
  new array and copying elements over.
- **`List`** (e.g. `ArrayList`) — ordered, resizable, allows
  duplicates. Use it when order matters and you don't need uniqueness.
- **`Set`** (e.g. `HashSet`) — no duplicates allowed; adding an element
  that already exists is silently a no-op. Use it when "is this thing
  in the collection" matters more than order.
- **`Map`** (e.g. `HashMap`) — key-value pairs, keys unique. Putting a
  value at an existing key overwrites it rather than adding a second
  entry.

**Picking the right one is a design decision, not just a syntax
choice.** Using a `List` where you actually need uniqueness (as in
this chapter's exercise) doesn't cause a compile error or even a crash
— it just silently allows duplicate data to accumulate, which is a
much harder class of bug to notice later.

**Generics (`<String>`, `<String, Integer>`)** give compile-time type
safety: `List<String>` won't let you `.add(42)`, so a whole category of
`ClassCastException` at runtime becomes a compile error instead.

## Q&A

**Q1: Why can't you call `.add()` on a plain array?**
A: Arrays in Java have a fixed length decided at creation time. There's
no method to grow them — `.add()` only exists on the `Collection`
types (`List`, `Set`, etc.), not on raw arrays.

**Q2: What happens when you add a duplicate value to a `HashSet`?**
A: Nothing observable — the add is silently ignored, and the set's
size doesn't change. This is different from a `List`, which would
happily store the duplicate.

**Q3: If a `List` should really contain only unique values, what bug
does using `List` instead of `Set` introduce?**
A: Nothing crashes — duplicates just quietly accumulate. It's a
correctness bug, not a compile or runtime error, which makes it easy
to miss until something downstream (like a count or a report) is
wrong.

**Q4: What happens when you `.put()` a value at a key that already
exists in a `HashMap`?**
A: The new value overwrites the old one for that key. A `Map`
guarantees unique keys, not unique key-value pairs across time.

**Q5: Why does `Map.Entry` show up when iterating a `HashMap` with a
for-each loop?**
A: `map.entrySet()` returns a `Set<Map.Entry<K,V>>` — each entry bundles
one key and its value together, which is what lets you access both
`entry.getKey()` and `entry.getValue()` per iteration.

**Q6: What does declaring `List<String>` (versus a raw `List`) buy
you?**
A: Compile-time type checking. The compiler rejects
`list.add(42)` on a `List<String>` before the program ever runs,
instead of letting it fail with a `ClassCastException` later.

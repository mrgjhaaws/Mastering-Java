# Chapter 6 — Libraries, Packages, and Modules

## Notes

**Packages** are Java's namespace mechanism — `com.example.util` groups
related classes together, avoids naming collisions between unrelated
libraries, and doubles as a physical folder structure
(`com/example/util/MathHelper.java`). A class's package declaration
must be the very first non-comment line in the file, and the file must
live at the matching directory path or the compiler won't find it.

**Libraries** are just collections of pre-compiled classes (often
packaged as `.jar` files) that you depend on instead of writing
them yourself — the standard library (`java.lang`, `java.util`,
`java.io`, etc.) ships with every JDK, while third-party libraries get
added to your project's classpath (or dependency manager config, for
Maven/Gradle projects).

**`import` vs. auto-imported:** everything in `java.lang` (like `Math`,
`String`, `System`) is available without an `import` statement — it's
the one package Java implicitly imports everywhere. Anything else,
including your own other packages, needs an explicit `import`.

**Modules** (introduced in Java 9, the "Jigsaw" project) are a step up
from packages: a module groups multiple packages and explicitly
declares which ones it exports and which other modules it requires,
giving you compile-time enforced boundaries between components of a
large application — packages alone don't enforce that isolation.

## Q&A

**Q1: Why does `PI * radius * radius` fail to compile without
qualification?**
A: `PI` isn't defined anywhere in this file or auto-imported — only
`java.lang` is implicit. You need either `Math.PI` (qualified) or an
explicit `import static java.lang.Math.PI;`.

**Q2: Why doesn't `Math.PI` need an import statement, but
`java.util.ArrayList` does?**
A: `Math` lives in `java.lang`, the one package every Java file
implicitly imports. `ArrayList` lives in `java.util`, which requires
an explicit `import java.util.ArrayList;` (or `import java.util.*;`).

**Q3: What has to be true about a file's location for
`package com.example.util;` to work?**
A: The file must sit at a matching directory path — e.g.
`src/com/example/util/MathHelper.java` — relative to the project's
source root. The package declaration and the folder structure must
agree.

**Q4: What's the practical difference between a package and a
module?**
A: A package is just a namespace for grouping classes — it doesn't
enforce what other code can access. A module (Java 9+) groups packages
and explicitly declares what it exports/requires, giving compiler-
enforced boundaries a package alone can't.

**Q5: If two different libraries both define a class named `Helper`,
how does Java tell them apart?**
A: Through their fully-qualified names — `com.libraryone.Helper` vs.
`com.librarytwo.Helper` are different classes even with the same
simple name, because the package prefix disambiguates them.

**Q6: What's a `.jar` file, in one sentence?**
A: A zip-format archive bundling compiled `.class` files (and
resources) from one or more packages into a single distributable unit.

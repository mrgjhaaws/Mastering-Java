# Chapter 7 — Java Database Connectivity (JDBC)

## Notes

JDBC is Java's standard API for talking to relational databases — it
defines interfaces (`Connection`, `Statement`, `ResultSet`) that any
database vendor implements via a driver, so your application code
stays mostly the same whether you're pointed at PostgreSQL, MySQL, or
an embedded database.

**The core flow:** get a `Connection` from `DriverManager` (or a
connection pool in real applications), create a `Statement` or
`PreparedStatement` to run SQL, get back a `ResultSet` to iterate over
rows, then close everything.

**`PreparedStatement` over `Statement` — this is not optional in real
code.** A `Statement` built by concatenating a username directly into
SQL text is a classic SQL-injection vulnerability: a malicious username
like `' OR '1'='1` can rewrite the query's logic entirely.
`PreparedStatement` binds values as parameters (`?` placeholders) that
are never interpreted as SQL syntax, closing that hole entirely — and
as a side benefit, it's usually faster for repeated queries because the
database can cache the query plan.

**Resource leaks are the other classic JDBC bug.** Every `Connection`,
`Statement`, and `ResultSet` holds a real resource (a socket, memory)
that must be closed. `try-with-resources` (used throughout this
chapter's solved code) guarantees that happens automatically, even if
an exception is thrown partway through — manually calling `.close()`
in a `finally` block is the old, more error-prone way to get the same
guarantee.

## Q&A

**Q1: What's the security problem with
`"SELECT * FROM users WHERE username = '" + username + "'"`?**
A: SQL injection — if `username` contains SQL syntax (like
`' OR '1'='1`), it gets executed as part of the query instead of being
treated as plain data, potentially bypassing intended logic entirely.

**Q2: How does `PreparedStatement` prevent SQL injection?**
A: Parameter values (set via `.setString()`, `.setInt()`, etc.) are
sent to the database separately from the SQL text and bound as literal
data — they can never be interpreted as SQL syntax, regardless of
their content.

**Q3: What does `try-with-resources` guarantee for a `Connection`,
`Statement`, or `ResultSet`?**
A: That `.close()` is called automatically when the try block exits —
whether normally or via an exception — preventing the resource leak
you'd get from forgetting a manual `.close()` call.

**Q4: Why might `PreparedStatement` also be faster than `Statement`
for a query run many times?**
A: The database can cache and reuse the query execution plan across
calls with different parameter values, instead of re-parsing and
re-planning the SQL text every single time.

**Q5: What's the role of a JDBC driver?**
A: It implements the standard JDBC interfaces
(`Connection`, `Statement`, etc.) for a specific database vendor,
translating the generic JDBC calls into that database's actual wire
protocol.

**Q6: In the solved example, why is `setupSchema` given its own
`try (Statement stmt = ...)` block instead of reusing one `Statement`
for the whole program?**
A: Scoping each `Statement` to the operation it's used for means it's
closed as soon as that operation finishes, rather than staying open
(and holding a resource) for the entire program's lifetime.

// Chapter 7: Java Database Connectivity (JDBC)
// Topic: connecting to a database and running queries safely.
//
// Note: this file demonstrates JDBC *structure* using an in-memory
// H2/SQLite-style connection string as an example. Swap the URL/driver
// for your actual database to run it for real.

/*
 UNSOLVED (exercise) -- compiles, but is a SQL-injection vulnerability
 and leaks resources (never closes the connection).
 Task: find both problems and fix them in the SOLVED version.

 static void findUserBroken(Connection conn, String username) throws SQLException {
     Statement stmt = conn.createStatement();
     // bug 1: string-concatenated SQL is vulnerable to SQL injection
     ResultSet rs = stmt.executeQuery(
         "SELECT * FROM users WHERE username = '" + username + "'");
     while (rs.next()) {
         System.out.println(rs.getString("username"));
     }
     // bug 2: conn, stmt, and rs are never closed
 }
*/

// SOLVED
import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) {
        String url = "jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1"; // in-memory demo DB

        // try-with-resources: connection/statement/result set all auto-close,
        // even if an exception is thrown.
        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            setupSchema(conn);
            insertUser(conn, "ada");
            insertUser(conn, "grace");
            findUser(conn, "ada"); // safe: uses a PreparedStatement
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            System.out.println("(Expected if the H2 driver isn't on your classpath --"
                    + " this file demonstrates JDBC structure, not a live DB.)");
        }
    }

    static void setupSchema(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (username VARCHAR(50) PRIMARY KEY)");
        }
    }

    static void insertUser(Connection conn, String username) throws SQLException {
        String sql = "INSERT INTO users (username) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        }
    }

    /** Safe from SQL injection: the username is bound as a parameter, never
     *  concatenated into the SQL string. */
    static void findUser(Connection conn, String username) throws SQLException {
        String sql = "SELECT username FROM users WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Found user: " + rs.getString("username"));
                }
            }
        }
    }
}

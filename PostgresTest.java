import java.sql.*;

public class PostgresTest {
    public static void main(String[] args) {
        addBread(true, "baguette");
    }

    private static void addBread(boolean toasted, String bread) {
        // Note that placeholder values are represented as ?
        String query = "INSERT INTO test (toasted, bread) VALUES (?, ?)";

        try {
            // Get a connection
            Connection con = DatabaseConnection.getCon();

            // Create a prepared statement from the query
            PreparedStatement sqlStatement = con.prepareStatement(query);

            // Fill in the prepared placeholders
            sqlStatement.setBoolean(1, toasted);
            sqlStatement.setString(2, bread);

            int result = sqlStatement.executeUpdate();
            System.out.println("Rows: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

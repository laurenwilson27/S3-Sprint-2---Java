import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

/**
 * A Data Access Object which interfaces with the Postgres database. It can record Users in the database, as well as retrieving, deleting, and updating them.
 * Additionally, this DAO is able to authenticate a plaintext password against the password hashes stored in the database.
 */

public class UserDao {

    /**
     * Records a new User in the database, and assign's the User's ID.
     * @param user User to add to the database
     * @return True if successful.
     */
    public boolean createUser(User user) {
        // insert user into database 
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        try {
            // Get a connection
            Connection psql = DatabaseConnection.getCon();

            // Prepare the SQL query
            String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement statement = psql.prepareStatement(query);

            // Set the parameters for the statement
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, user.isDoctor());

            // Execute the prepared statement
            ResultSet response = statement.executeQuery();

            // If data was returned...
            if (response.next()) {
                // The database determines what the id of the new user is, update the user object with this id
                int newId = response.getInt(1);

                user.setId(newId);
                // Also, the User object should contain the hashed password instead of the plaintext one
                user.setPassword(hashedPassword);
    
                return true;
            } else return false;
        } catch (Exception e) { System.err.println(e); return false; }
    }

    /**
     * Retrieves a User from the database based on its ID
     * @param id ID of the user
     * @return A User instance matching the database contents
     */
    public User getUserById(int id) { //get user by id from database 
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare the SQL query
            String query = "SELECT id, first_name, last_name, email, password, is_doctor FROM users WHERE id = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, id);

            // Database logic to get data by ID Using Prepared Statement
            ResultSet response = statement.executeQuery();

            // If the response was > 0 rows...
            if (response.next()) {
                // Create and return the new User based on the first (and only) row
                return new User(response.getInt(1), response.getString(2), response.getString(3),
                                response.getString(4), response.getString(5), response.getBoolean(6));
            } else return null;
        } catch (Exception e) { System.err.println(e); return null; }
    }

    /**
     * Retrieves a User from the database based on its email address
     * @param email Email address of the user
     * @return A User instance matching the database contents
     */
    public User getUserByEmail(String email) { // get user by email from database 
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare the SQL query
            String query = "SELECT id, first_name, last_name, email, password, is_doctor FROM users WHERE email = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setString(1, email);

            // Database logic to get data by ID Using Prepared Statement
            ResultSet response = statement.executeQuery();

            // If the response was > 0 rows...
            if (response.next()) {
                // Create and return the new User based on the first (and only) row
                return new User(response.getInt("id"), response.getString("first_name"), response.getString("last_name"),
                                response.getString("email"), response.getString("password"), response.getBoolean("is_doctor"));
            } else return null;
        } catch (Exception e) { System.err.println(e); return null; }
    }

    /**
     * Updates a User in the database.
     * @param user User to base the update on
     * @return True if successful
     */
    // This function will update the database with the values contained in the corresponding User instance
    public boolean updateUser(User user) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare the SQL query
            String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, is_doctor = ? WHERE id = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isDoctor());
            statement.setInt(6, user.getId());

            // Database logic to get update user Using Prepared Statement
            int result = statement.executeUpdate();

            // 'result' will be equal to the number of rows updated by the query.
            // A successful updateUser is one which updated one row
            if (result == 1) {
                return true;
            } else return false;
        } catch (Exception e) { System.err.println(e); return false; }
    }

    /**
     * Deletes a User from the database.
     * @param id ID of the User to delete
     * @return True if successful
     */
    public boolean deleteUser(int id) { // delete user from the database 
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare the SQL query
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, id);

            // Database logic to delete user
            int result = statement.executeUpdate();

            // 'result' will be equal to the number of rows deleted by the query.
            // A successful deleteUser is one which updated one row
            if (result == 1) {
                return true;
            } else return false;
        } catch (Exception e) { System.err.println(e); return false; }
    }

    /**
     * Validates a User password based on the record in the database
     * @param email Email address of the User to validate
     * @param password Plaintext password to compare against
     * @return True if the password is valid
     */
    public boolean verifyPassword (String email, String password)
    {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare the statement
            String query = "SELECT password FROM users WHERE email = ?";    // SQL Statement
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setString(1, email);

            // Implement logic to retrieve password using the Bcrypt
            ResultSet result = statement.executeQuery();

            // If there is data in the result set, the query found a match
            if (result.next()) {
                // Get the hashed password from the result
                String hash = result.getString(1);

                // Compare the given plaintext password to the hashed one in the database using bcrypt
                // The result of the comparison is the result of the function
                return (BCrypt.checkpw(password, hash));
            }
            return false; // Otherwise, the function has failed
        } catch (Exception e) { System.err.println(e); return false; }
    }

}

import java.util.List;
// import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.sql.*;

/**
 * Data Access Object class which facilitates the creation, modification, and retrieval of HealthData stored in the database.
 */

public class HealthDataDao {
  /**
   * Records the given HealthData instance in the database.
   *
   * @param healthData The HealthData to record
   * @return True if successful
   */
  public boolean createHealthData(HealthData healthData) { /* insert health data into database */
    try {
      Connection psql = DatabaseConnection.getCon();

      String query = "INSERT INTO health_data" +
        "(user_id, weight, height, steps, heart_rate, date, a1c) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";

      // Prepare the statement
      PreparedStatement statement = psql.prepareStatement(query);

      statement.setInt(1, healthData.getUserId());
      statement.setDouble(2, healthData.getWeight());
      statement.setDouble(3, healthData.getWeight());
      statement.setInt(4, healthData.getSteps());
      statement.setInt(5, healthData.getHeartRate());
      statement.setDate(6, Date.valueOf(healthData.getDate()));
      statement.setDouble(7, healthData.getA1C());

      // Execute the statement and act on the result
      ResultSet response = statement.executeQuery();

      if (response.next()) {
        // The response should contain the id of the new row in the database
        // Update the provided HealthData instance to use this ID.
        healthData.setId(response.getInt(1));

        return true;
      } else return false;

    } catch (Exception e) {System.err.println(e); return false;}
  }

  /**
   * Retrieves HealthData which is recorded in the database, based on its ID.
   * @param id ID of the HealthData.
   * @return A new HealthData instance with parameters based on the database records.
   */
  public HealthData getHealthDataById(int id) { /* get health data by id from database */ 
    try {
      Connection psql = DatabaseConnection.getCon();

      String query = "SELECT id, user_id, weight, height, steps, heart_rate, date, a1c FROM health_data WHERE id = ?";

      // Prepare the statement
      PreparedStatement statement = psql.prepareStatement(query);
      
      statement.setInt(1, id);

      // Execute the statement and act on the result
      ResultSet response = statement.executeQuery();

      if (response.next()) {
        // The response should contain all columns needed to create and return a new HealthData
        return new HealthData(response.getInt("id"), response.getInt("user_id"), response.getDouble("weight"), 
        response.getDouble("height"), response.getInt("steps"), response.getInt("heart_rate"), response.getString("date"),
        response.getDouble("a1c"));
      } else return null;

    } catch (Exception e) {System.err.println(e); return null;}
  }

  /**
   * Retrieves all HealthData belonging to a given user from the database.
   *
   * @param userId ID of the User.
   * @return A list of HealthData instances with parameters based on the database records.
   */
  public List<HealthData> getHealthDataByUserId(int userId) { /* get health data by user id from database */ 
    try {
      Connection psql = DatabaseConnection.getCon();

      String query = "SELECT id, user_id, weight, height, steps, heart_rate, date, a1c FROM health_data WHERE user_id = ?";

      // Prepare statement
      PreparedStatement statement = psql.prepareStatement(query);

      statement.setInt(1, userId);

      // Execute the statement and act on the result
      ResultSet response = statement.executeQuery();

      // Use the ArrayList implementation of the List interface
      List<HealthData> resultList = new ArrayList<HealthData>(); 

      // Loop through the Postgres response to create a List of new HealthData instances
      while (response.next()) {
        resultList.add(new HealthData(response.getInt("id"), response.getInt("user_id"), response.getDouble("weight"), 
        response.getDouble("height"), response.getInt("steps"), response.getInt("heart_rate"), response.getString("date"),
        response.getDouble("a1c")));
      }

      // Return the completed list
      return resultList;
    } catch (Exception e) {System.err.println(e); return null;}
  }

  /**
   * Updates a HealthData record in the database based on the information in the supplied HealthData instance.
   * @param healthData HealthData to use for the update.
   * @return True if successful.
   */
  public boolean updateHealthData(HealthData healthData) { /* update health data in the database */
    try {
      Connection psql = DatabaseConnection.getCon();

      String query = "UPDATE health_data SET user_id = ?, weight = ?, height = ?, steps = ?, heart_rate = ?, date = ?, a1c = ? WHERE id = ?";

      // Prepare statement
      PreparedStatement statement = psql.prepareStatement(query);

      statement.setInt(1, healthData.getUserId());
      statement.setDouble(2, healthData.getWeight());
      statement.setDouble(3, healthData.getHeight());
      statement.setInt(4, healthData.getSteps());
      statement.setInt(5, healthData.getHeartRate());
      statement.setDate(6, Date.valueOf(healthData.getDate()));
      statement.setDouble(7, healthData.getA1C());
      statement.setInt(8, healthData.getId());

      // Execute the statement
      int result = statement.executeUpdate();

      // 'result' will be equal to the number of rows updated by the query.
      // A successful update is one which updated a single row
      if (result == 1) {
        return true;
      } else return false;
    } catch (Exception e) {/*System.err.println(e);*/ return false;}
  }

  /**
   * Deletes HealthData with a matching ID from the database.
   * @param id ID of the HealthData to delete.
   * @return True if successful.
   */
  public boolean deleteHealthData(int id) { /* delete health data from the database */ 
    try {
      Connection psql = DatabaseConnection.getCon();

      String query = "DELETE FROM health_data WHERE id = ?";

      // Prepare statement
      PreparedStatement statement = psql.prepareStatement(query);

      statement.setInt(1, id);

      // Execute the statement
      int result = statement.executeUpdate();

      // 'result' will be equal to the number of rows deleted by the query.
      // A successful deletion is one which deleted a single row
      return (result == 1);
    } catch (Exception e) {System.err.println(e); return false;}
  }
}

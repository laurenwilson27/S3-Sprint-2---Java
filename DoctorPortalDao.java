import java.util.List;

// import javax.xml.crypto.Data;

// List is an interface, so we'll also use ArrayList, which implements List
import java.util.ArrayList;
import java.sql.*;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

   // Complete all these methods and add more as needed
 
    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    // Searches for and returns a new Doctor matching a specific ID in the database
    public Doctor getDoctorById(int doctorId) {
        try {
            Connection psql = DatabaseConnection.getCon();

            String query = "SELECT id, first_name, last_name, email, password, is_doctor FROM users WHERE id = ? AND is_doctor = True";

            // Prepare statement
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, doctorId);

            // Execute statement, act on the result
            ResultSet res = statement.executeQuery();

            // If the result of the query contained data, use the data to create and return a new Doctor
            if (res.next()) {
                return new Doctor(res.getInt("id"), res.getString("first_name"), res.getString("last_name"), 
                res.getString("email"), res.getString("password"), res.getBoolean("is_doctor"), res.getString("medical_license_number"), res.getString("specialization"));
            } else return null;
        } catch (Exception e) {System.err.println(e); return null;}
    }

    // Returns a List containing every User which is a patient of the Doctor with the given ID
    public List<User> getPatientsByDoctorId(int doctorId) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Begin by using a prepared statement to select all doctor-patient relationships involving this doctor
            String query = "SELECT doctor_id, patient_id FROM doctor_patient WHERE doctor_id = ?";

            PreparedStatement statement = psql.prepareStatement(query);
            statement.setInt(1, doctorId);

            ResultSet result = statement.executeQuery();
            List<User> patients = new ArrayList<User>();
            
            // Loop through the result set, creating new users through the User DAO and adding them to the patient list
            while (result.next()) {
                patients.add(userDao.getUserById(result.getInt("patient_id")));
            }

            // Return the completed list
            return patients;
        } catch (Exception e) {System.err.println(e); return null;}
    }

    // Returns a List containing every HealthData entry for a given patient
    // This essentially just wraps a similar HealthData DAO method
    public List<HealthData> getHealthDataByPatientId(int patientId) {
        return healthDataDao.getHealthDataByUserId(patientId);
    }

    // Add more methods for other doctor-specific tasks

    // Adds a doctor-patient relationship to the doctor_patient DB table
    // Returns true if the relationship was added successfully
    // Note that because doctor_id and patient_id are a compound primary key, the query will fail if the relationship already exists, returning false
    public boolean createDoctorPatientRelationship(int doctorId, int patientId) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Use a prepared statement
            String query = "INSERT INTO doctor_patient (doctor_id, patient_id) VALUES (?, ?)";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);

            // Execute the query - result will be an integer equal to the number of created rows
            int result = statement.executeUpdate();

            // If result is 1, return true. Otherwise, return false.
            return (result == 1);
        } catch (Exception e) {System.err.println(e); return false;}
    }

    // Delete a doctor-patient relationship from the doctor_patient DB table
    public boolean deleteDoctorPatientRelationship(int doctorId, int patientId) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Use a prepared statement
            String query = "DELETE FROM doctor_patient WHERE doctor_id = ? AND patient_id = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);

            // Execute the query - result will be an integer equal to the number of deleted rows
            int result = statement.executeUpdate();

            // If result is 1, return true. Otherwise, return false.
            return (result == 1);
        } catch (Exception e) {System.err.println(e); return false;}
    }
}


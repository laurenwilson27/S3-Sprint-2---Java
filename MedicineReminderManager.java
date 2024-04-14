import javax.xml.transform.Result;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * The MedicineReminderManager class should have methods to add reminders, get reminders
 *  1. for a specific user, and
 *  2. get reminders that are DUE for a specific user.
 *  You'll need to integrate this class with your application and database logic to
 *  1. store,
 *  2. update, and
 *  3. delete reminders as needed.
 */

public class MedicineReminderManager {
    private List<MedicineReminder> reminders;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    public void addReminder(MedicineReminder reminder) {
        reminders.add(reminder);
    }

    public void setReminders(List<MedicineReminder> reminders) {
        this.reminders = reminders;
    }

    public boolean createReminder(MedicineReminder reminder) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare a statement
            String query = "insert into medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date) " +
                    "values (?, ?, ?, ?, ?, ?) returning id";
            PreparedStatement statement = psql.prepareStatement(query);

            // Execute statement and act on result
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                reminder.setId(result.getInt("id"));
                return true;
            }
            return false;
        } catch (Exception e) {System.out.println(e); return false;}
    }

    public boolean deleteReminder(int id) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare a statement
            String query = "DELETE FROM medicine_reminders WHERE id = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            // Execute statement and act on result
            int result = statement.executeUpdate();

            // If successful, result is 1
            return (result == 1);
        } catch (Exception e) {System.out.println(e); return false;}
    }

    public boolean updateReminder(MedicineReminder reminder) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare a statement
            String query = "UPDATE medicine_reminders SET user_id = ?, medicine_name = ?, dosage = ?, schedule = ?, " +
                    "start_date = ?, end_date = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, reminder.getUserId());
            statement.setString(2, reminder.getMedicineName());
            statement.setString(3, reminder.getDosage());
            statement.setString(4, reminder.getSchedule());
            statement.setString(5, reminder.getStartDate());
            statement.setString(6, reminder.getEndDate());

            // Execute statement. Successful if result = 1
            int result = statement.executeUpdate();

            return (result == 1);
        } catch (Exception e) {System.out.println(e); return false;}
    }

    public List<MedicineReminder> getRemindersForUser(int userId) {
        List<MedicineReminder> userReminders = new ArrayList<>();

        // Write your logic here
        try {
            Connection psql = DatabaseConnection.getCon();

            // Create a prepared statement which uses userId
            String query = "SELECT id, user_id, medicine_name, dosage, schedule, start_date, end_date FROM " +
                    "medicine_reminders WHERE user_id = ?";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, userId);

            // Execute the statement, and act on the result
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                userReminders.add(new MedicineReminder(result.getInt("id"), result.getInt("user_id"),
                        result.getString("medicine_name"), result.getString("dosage"), result.getString("schedule"),
                        result.getString("start_date"), result.getString("end_date")));
            }
        } catch (Exception e) {System.err.println(e);}

        return userReminders;
    }

    // Retrieves *all* reminders from the database
    public List<MedicineReminder> getReminders(int userId) {
        List<MedicineReminder> allReminders = new ArrayList<>();

        // Write your logic here
        try {
            Connection psql = DatabaseConnection.getCon();

            // Create a prepared statement - this does not have any parameters
            String query = "SELECT id, user_id, medicine_name, dosage, schedule, start_date, end_date FROM " +
                    "medicine_reminders";
            PreparedStatement statement = psql.prepareStatement(query);

            // Execute the statement, and act on the result
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                allReminders.add(new MedicineReminder(result.getInt("id"), result.getInt("user_id"),
                        result.getString("medicine_name"), result.getString("dosage"), result.getString("schedule"),
                        result.getString("start_date"), result.getString("end_date")));
            }
        } catch (Exception e) {System.err.println(e);}

        return allReminders;
    }

    // It was not explicitly clarified what a 'due' reminder is considered to be.
    // My code will assume that a 'due' reminder is one which is past its start date, but preceding its end date.
    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // Write your logic here
        // Start by getting the list of all reminders for a user using the getRemindersForUser function
        List<MedicineReminder> allReminders = getRemindersForUser(userId);

        // Loop through the list of all reminders
        for (MedicineReminder reminder : allReminders) {
            // The start and end dates for a reminder are stored as strings in the MedicineReminder class
            // Parse them to LocalDateTimes using the provided formatter
            LocalDateTime startDate = LocalDateTime.parse(reminder.getStartDate(), formatter);
            LocalDateTime endDate = LocalDateTime.parse(reminder.getEndDate(), formatter);

            // Compare the start and end date of this minder to the 'now' LocalDateTime
            if (!startDate.isBefore(now) && !endDate.isAfter(now)) {
                // If the current datetime falls between the start and end dates, add this remimnder to the due reminders
                dueReminders.add(reminder);
            }
        }

        return dueReminders;
    }
}

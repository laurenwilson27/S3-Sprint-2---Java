// import java.time.LocalDateTime;
// Note that the Postgres 'date' type does not include 'time'
// Because of this, I am using the LocalDate class to more easily convert dates into java.sql.Date
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * A class which can store multiple MedicineReminders, as well as performing CRUD operations on the database.
 * The MedicineReminderManager can record new MedicineReminders, retrieve them, update them, and delete them.
 */

public class MedicineReminderManager {
    private List<MedicineReminder> reminders;

    /**
     * Constructor for a MedicineReminderManager. Each manager holds its own collection of MedicineReminders.
     */
    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    /**
     * Appends a MedicineReminder to the manager's collection of reminders.
     * @param reminder The reminder to add
     */
    public void addReminder(MedicineReminder reminder) {
        reminders.add(reminder);
    }

    /**
     * Replaces the manager's list of reminders with a list which is supplied to this method.
     * @param reminders The new list of reminders
     */
    public void setReminders(List<MedicineReminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * Records a MedicineReminder in the database.
     * @param reminder The MedicineReminder to record
     * @return True if successful
     */
    public boolean createReminder(MedicineReminder reminder) {
        try {
            Connection psql = DatabaseConnection.getCon();

            // Prepare a statement
            String query = "insert into medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date) " +
                    "values (?, ?, ?, ?, ?, ?) returning id";
            PreparedStatement statement = psql.prepareStatement(query);

            statement.setInt(1, reminder.getUserId());
            statement.setString(2, reminder.getMedicineName());
            statement.setString(3, reminder.getDosage());
            statement.setString(4, reminder.getSchedule());
            statement.setDate(5, Date.valueOf(reminder.getStartDate()));
            statement.setDate(6, Date.valueOf(reminder.getEndDate()));

            // Execute statement and act on result
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                reminder.setId(result.getInt("id"));
                return true;
            }
            return false;
        } catch (Exception e) {System.out.println(e); return false;}
    }

    /**
     * Removes a MedicineReminder from the database, based on the reminder's ID
     * @param id ID of the reminder to remove
     * @return True if successful.
     */
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

    /**
     * Updates a MedicineReminder in the database.
     * @param reminder The MedicineReminder to base the update on
     * @return True if successful
     */
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
            statement.setDate(5, Date.valueOf(reminder.getStartDate()));
            statement.setDate(6, Date.valueOf(reminder.getEndDate()));

            // Execute statement. Successful if result = 1
            int result = statement.executeUpdate();

            return (result == 1);
        } catch (Exception e) {System.out.println(e); return false;}
    }

    /**
     * Retrieves all MedicineReminders for a given user.
     *
     * @param userId ID of the user to retrieve all reminders of
     * @return A list of MedicineReminders belonging to the user
     */
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

    /**
     * Retrieves all MedicineReminders in the database.
     * @return A list of all MedicineReminders.
     */
    public List<MedicineReminder> getReminders() {
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

    /**
     * Retrieves a list of all MedicineReminders for a User which are 'due', ie, the current date is between the start and end dates of the reminder
     * @param userId The ID of the user
     * @return A list of all 'due' reminders for the user
     */
    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Write your logic here
        // Start by getting the list of all reminders for a user using the getRemindersForUser function
        List<MedicineReminder> allReminders = getRemindersForUser(userId);

        // Loop through the list of all reminders
        for (MedicineReminder reminder : allReminders) {
            // The start and end dates for a reminder are stored as strings in the MedicineReminder class
            // Parse them to LocalDates using the provided formatter
            LocalDate startDate = LocalDate.parse(reminder.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(reminder.getEndDate(), formatter);

            // Compare the start and end date of this minder to the 'now' LocalDate
            if ((startDate.isBefore(today) || startDate.isEqual(today)) && (endDate.isAfter(today) || endDate.isEqual(today))) {
                // If the current datetime falls between the start and end dates, add this reminder to the due reminders
                dueReminders.add(reminder);
            }
        }

        return dueReminders;
    }
}

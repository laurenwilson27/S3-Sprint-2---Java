// import com.DataBaseConnection;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.time.Duration;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;


public class HealthMonitoringApp {
    private static final UserDao userDao = new UserDao();
    /**
     * Test the following functionalities within the Main Application
     *  1. Register a new user
     *  2. Log in the user
     *  3. Add health data8
     *  4. Generate recommendations
     *  5. Add a medicine reminder
     *  6. Get reminders for a specific user
     *  7. Get due reminders for a specific user
     *  8. test doctor portal
     */

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        HealthDataDao healthDataDao = new HealthDataDao();
        MedicineReminderManager reminders = new MedicineReminderManager();

        Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Test register a new user
        // Create a test user
        User testUser = new User("Lauren", "Wilson","lauren.wilson@example.com", "password123", false);

        // Register this user in the database
        if (userDao.createUser(testUser))
            System.out.println("Creation was successful!");

        // This should have assigned a new id to the test user, print it.
        System.out.println("Test User " + testUser.getEmail() + " has ID: " + testUser.getId());

        // test Login user (call testLoginUser() here)
        System.out.println("Press ENTER to proceed to next test: login user");
        input.nextLine();

        testLoginUser();

        // Add health data
        System.out.println("Press ENTER to proceed to next test: health data");
        input.nextLine();

        HealthData newData = new HealthData(testUser.getId(), 49.8, 172.72, 12000, 60, LocalDateTime.now().format(formatter), 8.1);
        if (healthDataDao.createHealthData(newData))
            System.out.println("Creation was successful!");

        System.out.println("Test Health Data was given id " + newData.getId());

        // Generate recommendations
        System.out.println("Press ENTER to proceed to next test: recommendations");
        input.nextLine();

        System.out.println("Recommendations for " + testUser.getFirstName() + " " + testUser.getLastName());
        List<String> recommendations = RecommendationSystem.generateRecommendations(newData);
        for (String recommendation : recommendations)
            System.out.println(recommendation);

        // Add a medicine reminder
        System.out.println("Press ENTER to proceed to next test: medicine reminder");
        input.nextLine();

        MedicineReminder newReminder = new MedicineReminder(testUser.getId(), "Sunshine, happiness, and rainbows", "Lots", "Twice daily",
               LocalDateTime.now().format(formatter), LocalDateTime.now().plus(Duration.ofDays(100)).format(formatter));
        // Add the reminder to the reminders instance
        reminders.addReminder(newReminder);
        // Add the reminder in the database
        reminders.createReminder(newReminder);

        System.out.println("User " + newReminder.getUserId() + " has been prescribed " + newReminder.getMedicineName());

        // Get reminders for a specific user
        System.out.println("Press ENTER to proceed to next test: reminders for user");
        input.nextLine();

        // Use the method to get the test user's reminders from the DB
        List<MedicineReminder> myReminders = reminders.getRemindersForUser(testUser.getId());
        for (MedicineReminder reminder : myReminders)
            System.out.println(testUser.getFirstName() + " " + testUser.getLastName() + " should take " + reminder.getDosage() +
                    " of " + reminder.getMedicineName() + " from " + reminder.getStartDate() + " until " + reminder.getEndDate());

        // Get due reminders for a specific user
        System.out.println("Press ENTER to proceed to next test: due reminders");
        input.nextLine();

        // Use the method to get the test user's DUE reminders from the DB
        // (This will have the same result as the previous test)
        List<MedicineReminder> dueReminders = reminders.getDueReminders(testUser.getId());
        for (MedicineReminder reminder : myReminders)
            System.out.println(testUser.getFirstName() + " " + testUser.getLastName() + " should be taking " + reminder.getDosage() +
                            " of " + reminder.getMedicineName());

        //test doctor portal (call testDoctorPortal() here)

        input.close();
    }

    public static boolean loginUser(String email, String password) {
        //implement method to login user.
        User user = userDao.getUserByEmail(email);

        if (user != null) {
            // Compare the stored hashed password with the given password and return result
            return BCrypt.checkpw(password, user.getPassword());
        }

        return false;
    }

    /**
     * To test the Doctor Portal in your Health Monitoring System, provide a simple test code method that you can add
     * to your main application class.
     * In this method, we'll test the following functionalities:
     * 1. Fetching a doctor by ID
     * 2. Fetching patients associated with a doctor
     * 3. Fetching health data for a specific patient
      */
    public static void testDoctorPortal() {
        DoctorPortalDao doctorPortal = new DoctorPortalDao();

        // Replace the doctorId with a valid ID from your database
        int doctorId = 3;

        // Add code to Fetch the doctor by ID
        Doctor useDoctor = doctorPortal.getDoctorById(doctorId);

        // Add code to Fetch patients associated with the doctor


        // Add code to Fetch health data for the patient

    }

    /**
     * To test the login user functionality in your Health Monitoring System, you can
     * add a test method to your main application class
     */
    public static void testLoginUser() {
        // I'll use the credentials of the user created at the start of this test program
        String userEmail = "lauren.wilson@example.com";
        String userPassword = "password123";

        boolean loginSuccess = userDao.verifyPassword(userEmail, userPassword);

        System.out.println("Logging in with email " + userEmail + "and password " + userPassword);

        if (loginSuccess) {
            // Print to console, "Login Successful"
            System.out.print("Login Successful");
        } else {
            // Print to console, "Incorrect email or password. Please try again.");
            // Show an error message and prompt the user to re-enter their credentials
            System.err.println("Incorrect email or password. Please try again.");
        }
    }

}

/**
 * A universal user class. All users have a name, email, phone number, and password, as well as a flag for if they are or aren't a doctor.
 */

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDoctor;

    /**
     * Basic constructor for a User.
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param isDoctor
     */
    public User(int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    /**
     * Alternate constructor which does not take an id. Useful if the User does not yet exist in the database.
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param isDoctor
     */
    public User(String firstName, String lastName, String email, String password, boolean isDoctor) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

   
}

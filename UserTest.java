public class UserTest {
    public static void main(String[] args) {
        UserDao dao = new UserDao();

        // User user = new User(0, "Lauren", "Wilson", "lauren3@example.com", "password123", true);

        // dao.createUser(user);

        // User user = dao.getUserByEmail("lauren3@example.com");
        // System.out.println(user.getFirstName() + " " + user.getLastName());

        System.out.println(dao.verifyPassword("lauren3@example.com", "password123"));
    }   
}

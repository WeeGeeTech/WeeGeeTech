public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("JAAS Config File: " + System.getProperty("java.security.auth.login.config"));
        SimpleLoginModule loginModule = new SimpleLoginModule();

        // Initialize the user interface
//        UserInterface userInterface = new UserInterface();

        // Start the program
//        userInterface.run();

        UserStorage userStorage = new UserStorage(); // Initialize UserStorage
        UserManager userManager = new UserManager(userStorage); // Initialize UserManager with UserStorage
        UserInterface userInterface = new UserInterface(userManager); // Initialize UserInterface with UserManager
        userInterface.run(); // Start the program

        // Add user credentials
//        String username = "admin";
//        String password = "password"; // Example user credentials
//        loginModule.addUser(username, password);

//        try {
//            // Generate a random salt
//            byte[] salt = PasswordUtils.generateSalt();
//
//            // Hash the password with the salt
//            byte[] hashedPassword = PasswordUtils.hashPasswordWithSalt(password, salt);
//
//            // Verify the password
//            boolean passwordVerified = PasswordUtils.verifyPassword(password, salt, hashedPassword);
//
//            if (passwordVerified) {
//                System.out.println("Password verification successful.");
//            } else {
//                System.out.println("Password verification failed.");
//            }
//        } catch (NoSuchAlgorithmException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
    }
}

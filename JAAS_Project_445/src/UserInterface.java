import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private boolean isLoggedIn = false;
    private String username = null;
    private UserManager userManager;

    private UserSession userSession;

    public UserInterface(UserManager userManager) {
        this.userManager = userManager;
    }

    public UserInterface() {
    }


    public void run() {
        while (true) {
            Thread.yield();
            if (!isLoggedIn) {
                displayMainMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        System.out.println("Exiting program.");
                        System.exit(0);
                        break;
//                    case 4:
//                        LoginTest.performLoginTest();
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            } else {
                displayLoggedInMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        viewAccountDetails();
                        break;
                    case 2:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again.");
                        break;
                }
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("1. Log in");
        System.out.println("2. Create account");
        System.out.println("3. Exit");
//        System.out.println("4. Test Bob's password");
        System.out.print("Choose an option: ");
    }

    private void displayLoggedInMenu() {
        System.out.println("1. View account details");
        System.out.println("2. Log out");
        System.out.print("Choose an option: ");
    }



    public void login() {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager(new UserStorage()); // Provide a UserStorage object
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        String sessionId = null;

        if (userManager.login(username, password)) {
            System.out.println("Login successful! Welcome " + username + "!");
            sessionId = SessionManager.createSession(username);
            // Proceed with the rest of your application logic
        } else {
            System.out.println("UserInterface login() failed. Please try again.");
        }
        isLoggedIn = true;
    }

    private void createUser() {
        UserStorage userStorage = new UserStorage(); // Create a UserStorage object
        UserManager userManager = new UserManager(userStorage); // Pass the UserStorage object to the UserManager constructor
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        userManager.addUser(username, password);
        System.out.println("Account created successfully.");
    }


    private void viewAccountDetails() {
         System.out.println("Active session.");
    }



//        private void viewAccountDetails(String sessionId) {
//        String username = userManager.getUsername(sessionId);
//        if (username != null) {
//            System.out.println("Username: " + username);
//        } else {
//            System.out.println("User not logged in or session expired.");
//        }
//    }
    private void logout() {
        isLoggedIn = false;
        System.out.println("You have logged out successfully.");
        username = null;
    }
}
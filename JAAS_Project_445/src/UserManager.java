import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class UserManager {
    private final Object lock = new Object();
    private UserStorage userStorage;

    public UserManager(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean login(String username, String password) {
        // Call verifyCredentials method
        System.out.println("UserManager login() initialized.");
        if (verifyCredentials(username, password)) {
            // If credentials are verified, perform login
            try {
                LoginContext lc = new LoginContext("Sample", new SimpleCallbackHandler(username, password));
                lc.login();
                String sessionId = SessionManager.createSession(username);
                System.out.println("created new SessionManager");
                return true; // Login successful
            } catch (LoginException e) {
                System.out.println("UserManager login() catch executed. Derp.");
                System.out.println("Login failed: " + e.getMessage());
                return false; // Login failed
            }
        } else {
            // If credentials are not verified, return false
            System.out.println("UserManager login() failed: Invalid username or password.");
            return false;
        }
    }

    // Method to securely store user credentials
    public void addUser(String username, String password) {
        try {
            // Generate a random salt
            byte[] salt = generateSalt();

            // Hash the password with the salt
            byte[] hashedPassword = hashPassword(password.toCharArray(), salt);

            // Store the username, hashed password, and salt securely in your system
            userStorage.addUser(username, hashedPassword, salt);

            System.out.println("User added successfully.");
        } catch (NoSuchAlgorithmException e) {
            // Handle exceptions
            e.printStackTrace();
        } catch (IOException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to verify a user's password
    private boolean verifyCredentials(String givenUsername, String givenPassword) {
        synchronized (lock) {
            try (BufferedReader reader = new BufferedReader(new FileReader("user_credentials.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 3 && parts[0].equals(givenUsername)) {
                        String storedHashedPassword = parts[1];
                        String storedSalt = parts[2];

                        // Decode the stored hashed password and salt from Base64
                        byte[] decodedHashedPassword = Base64.getDecoder().decode(storedHashedPassword);
                        byte[] decodedSalt = Base64.getDecoder().decode(storedSalt);

                        try {
                            // Hash the provided password using the decoded salt
                            byte[] hashedPassword = hashPassword(givenPassword.toCharArray(), decodedSalt);
//                            System.out.println("user: " + givenUsername);
//                            System.out.println("decodedHashedPassword: " + bytesToHex(decodedHashedPassword));
//                            System.out.println("hashedPassword: " + bytesToHex(hashedPassword));
                            // Compare the hashed password with the stored hashed password
                            String dhp = bytesToHex(decodedHashedPassword);
                            String hp = bytesToHex(hashedPassword);
                            if (Arrays.equals(decodedHashedPassword, hashedPassword)) {
                                // Authentication successful
//                            System.out.println("UserManager > verifyCredentials() returned TRUE!");
                                return true;
                            }
                        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                            // Handle hashing algorithm or key specification error
                            System.out.println("This is inside a catch statement: UserManager > verifyCredentials");
                            e.printStackTrace(); // or handle it in a way appropriate for your application
                            return false; // or handle it in a way appropriate for your application
                        }
                    }
                }
            } catch (IOException e) {
                // Handle file IO exception
                System.out.println("This is inside an OUTER catch statement: UserManager > verifyCredentials");
                e.printStackTrace();
            }
            // No matching credentials found
            return false;
        }
    }


    // Helper method to convert byte array to hexadecimal string for testing
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Method to generate a random salt
    private byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Method to hash a password with salt using PBKDF2
//    private byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        int iterations = 1000; // Number of iterations
//        int keyLength = 256; // Key length
//        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
//        return skf.generateSecret(spec).getEncoded();
//    }

    private static byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Hash the password using PBKDF2 with HMAC SHA-512
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        return factory.generateSecret(spec).getEncoded();
    }

    public String getUsername(String sessionId) {
        // Retrieve the username associated with the sessionId from the SessionManager
        return SessionManager.getUsername(sessionId);
    }

    // Other user management methods? deleteUser, changePassword, ?
}
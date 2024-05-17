import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorage {

    private Map<String, UserData> userCredentials;
    private static final String STORAGE_FILE = "user_credentials.txt";

    // Map to store user data (username -> {hashedPassword, salt})
    private Map<String, UserData> userDataMap;

    public UserStorage() {
        userDataMap = new HashMap<>(); // Initialize the userDataMap
        userCredentials = new HashMap<>(); // Initialize the userCredentials map
        loadCredentialsFromFile(); // Load credentials from file, if any
    }



    // Method to add a new user to the storage
    public void addUser(String username, byte[] hashedPassword, byte[] salt) throws IOException {
        // Store the username along with the hashed password and salt
        userDataMap.put(username, new UserData(hashedPassword, salt));
        saveCredentialsToFile();
    }


//    public void addUser(String username, String password) {
//        try {
//            byte[] salt = generateSalt();
//            byte[] hashedPassword = hashPassword(password.toCharArray(), salt);
//            userCredentials.put(username, Base64.getEncoder().encodeToString(hashedPassword) + ":" + Base64.getEncoder().encodeToString(salt));
//            saveCredentialsToFile();
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
//            // Handle exceptions
//            e.printStackTrace();
//        }
//    }

//    public boolean verifyUser(String username, String password) {
//        String storedCredentials = userCredentials.get(username);
//        if (storedCredentials != null) {
//            String[] parts = storedCredentials.split(":");
//            try {
//                byte[] hashedPassword = Base64.getDecoder().decode(parts[0]);
//                byte[] salt = Base64.getDecoder().decode(parts[1]);
//                byte[] hashedAttempt = hashPassword(password.toCharArray(), salt);
//                return MessageDigest.isEqual(hashedPassword, hashedAttempt);
//            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//                // Handle exceptions
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }

    private void loadCredentialsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STORAGE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length != 3) {
//                    System.out.println("Invalid line format: " + line); // Debugging output
                    continue; // Skip processing this line
                }
                byte[] hashedPassword = Base64.getDecoder().decode(parts[1]);
                byte[] salt = Base64.getDecoder().decode(parts[2]);

                // Ensure that the hashed password matches the expected format (PBKDF2)
//                if (!isPBKDF2(hashedPassword)) {
//                    System.out.println("Invalid hash format: " + parts[1]);
//                    continue; // Skip processing this line
//                }

                // Add the username and decoded password/salt to the map
                userCredentials.put(parts[0], new UserData(hashedPassword, salt));

                // Print the parsed information
//                System.out.println("User: " + parts[0]);
//                System.out.println("Hashed Password: " + parts[1]);
//                System.out.println("Salt: " + parts[2]);
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
    }

    // Check if the hashed password is generated using PBKDF2
    private boolean isPBKDF2(byte[] hashedPassword) {
        // Convert the byte array to a string
        String hashString = new String(hashedPassword);
        // Check if the string contains the specific PBKDF2 algorithm used for hashing
        return hashString.contains("PBKDF2WithHmacSHA512");
    }



    private void saveCredentialsToFile() throws IOException {
        // Read existing credentials from file
        List<String> lines = Files.readAllLines(Paths.get("user_credentials.txt"));

        // Add the new credentials to the list
        StringBuilder newCredentials = new StringBuilder();
        for (Map.Entry<String, UserData> entry : userDataMap.entrySet()) {
            String username = entry.getKey();
            UserData userData = entry.getValue();
            String hashedPassword = Base64.getEncoder().encodeToString(userData.getHashedPassword());
            String salt = Base64.getEncoder().encodeToString(userData.getSalt());
            newCredentials.append(username).append(":").append(hashedPassword).append(":").append(salt).append("\n");
        }

        // Append new credentials to existing file content
        lines.add(newCredentials.toString());

        // Write the updated contents back to the file
        Files.write(Paths.get("user_credentials.txt"), lines);
    }

//    private byte[] generateSalt() throws NoSuchAlgorithmException {
//        SecureRandom random = SecureRandom.getInstanceStrong();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        return salt;
//    }
//
    private byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        return factory.generateSecret(spec).getEncoded();
    }
}

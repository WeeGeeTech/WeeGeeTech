import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {

    // Method to generate a random salt
    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    // Method to hash a password with salt using SHA-256
    public static byte[] hashPasswordWithSalt(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        return digest.digest(password.getBytes());
    }

    // Method to verify a password with its hash and salt
    public static boolean verifyPassword(String password, byte[] salt, byte[] hashedPassword) throws NoSuchAlgorithmException {
        byte[] generatedHash = hashPasswordWithSalt(password, salt);
        return MessageDigest.isEqual(hashedPassword, generatedHash);
    }
}

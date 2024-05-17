public class UserData {
    private byte[] hashedPassword;
    private byte[] salt;

    public UserData(byte[] hashedPassword, byte[] salt) {
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

//    public UserData(String hashedPassword, String salt) {
//        this.hashedPassword = hashedPassword;
//        this.salt = salt;
//    }

    // Getters and setters for hashedPassword and salt

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}

public class UserSession {
    private String username;
    private String sessionId;
    private long expirationTime; // Timestamp when the session expires

    public UserSession(String username, String sessionId, long expirationTime) {
        this.username = username;
        this.sessionId = sessionId;
        this.expirationTime = expirationTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    // Constructor, getters, and setters
}

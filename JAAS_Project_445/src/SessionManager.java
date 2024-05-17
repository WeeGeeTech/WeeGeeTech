import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static Map<String, UserSession> activeSessions = new HashMap<>();
    private static final long SESSION_TIMEOUT_MS = 30 * 60 * 1000; // 30 minutes

    public static String createSession(String username) {
        String sessionId = generateSessionId();
        long expirationTime = System.currentTimeMillis() + SESSION_TIMEOUT_MS;
        UserSession session = new UserSession(username, sessionId, expirationTime);
        activeSessions.put(sessionId, session);
        return sessionId;
    }

    public static boolean isValidSession(String sessionId) {
        if (activeSessions.containsKey(sessionId)) {
            UserSession session = activeSessions.get(sessionId);
            return session.getExpirationTime() > System.currentTimeMillis();
        }
        return false;
    }

    public static String getUsername(String sessionId) {
        if (isValidSession(sessionId)) {
            return activeSessions.get(sessionId).getUsername();
        }
        return null;
    }

    public static String generateSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    // Other session management methods
}

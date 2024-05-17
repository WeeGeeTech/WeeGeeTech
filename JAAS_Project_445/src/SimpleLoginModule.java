import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

public class SimpleLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private boolean loginSucceeded = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username: ");
        callbacks[1] = new PasswordCallback("Password: ", false);

        try {
            callbackHandler.handle(callbacks);
            String username = ((NameCallback) callbacks[0]).getName();
            String password = new String(((PasswordCallback) callbacks[1]).getPassword());

            // Verify the entered username and password against stored credentials
            // Replace this logic with your actual authentication mechanism
            if ("admin".equals(username) && "password".equals(password)) {
                loginSucceeded = true;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException("Error during login: " + e.getMessage());
        }

        return loginSucceeded;
    }

    @Override
    public boolean commit() throws LoginException {
        if (loginSucceeded) {
            subject.getPrincipals().add(new Principal() {
                @Override
                public String getName() {
                    return "admin"; // Return the username or identifier of the authenticated user
                }
            });
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        // No specific actions needed if login is aborted
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().clear(); // Clear all principals from the subject
        return true;
    }
}

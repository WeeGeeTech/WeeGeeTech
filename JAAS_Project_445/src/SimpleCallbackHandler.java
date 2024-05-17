import javax.security.auth.callback.*;
import java.io.IOException;

public class SimpleCallbackHandler implements CallbackHandler {

    public SimpleCallbackHandler(String username, String password) {

    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                // Prompt the user for their username
                NameCallback nameCallback = (NameCallback) callback;
                nameCallback.setName("admin"); // Hardcoded username for simplicity
            } else if (callback instanceof PasswordCallback) {
                // Prompt the user for their password
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                passwordCallback.setPassword("password".toCharArray()); // Hardcoded password for simplicity
            } else {
                throw new UnsupportedCallbackException(callback, "Unsupported callback");
            }
        }
    }
}

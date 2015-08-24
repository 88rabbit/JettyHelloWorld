package io.github.mezk.main;

/**
 * Represents user's profile.
 * @author <a href="mailto:andreyselkin@gmail.com">Andrei Selkin</a>
 */
public class UserProfile {
    /**User's login.*/
    private String login;
    /**User's password.*/
    private String password;
    /**User's email.*/
    private String email;

    /**
     * Class constructor.
     * @param login login.
     * @param password password.
     * @param email email.
     */
    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

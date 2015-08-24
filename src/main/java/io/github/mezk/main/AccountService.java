package io.github.mezk.main;

import java.util.HashMap;
import java.util.Map;

/**
 * Temp class to handle account operations.
 * @author <a href="mailto:andreyselkin@gmail.com">Andrei Selkin</a>
 */
public class AccountService {

    /**In memory DB which contains user info.*/
    private Map<String, UserProfile> users = new HashMap<>();
    /**User's sessions in memory.*/
    private Map<String, UserProfile> sessions = new HashMap<>();

    /**
     * Adds user profile into DB.
     * @param userName user name.
     * @param userProfile user profile.
     * @return true if user was added successfully.
     */
    public boolean addUser(String userName, UserProfile userProfile) {
        if (users.containsKey(userName)) {
            return false;
        }
        users.put(userName, userProfile);
        return true;
    }

    /**
     * Adds session.
     * @param sessionId session's id.
     * @param userProfile user's profile.
     */
    public void addSession(String sessionId, UserProfile userProfile) {
        sessions.put(sessionId, userProfile);
    }

    /**
     * Gets user's profile from DB.
     * @param userName user name.
     * @return user's profile.
     */
    public UserProfile getUser(String userName) {
        return users.get(userName);
    }

    /**
     * Gets user's profile by session id.
     * @param sessionId session's id.
     * @return user's profile.
     */
    public UserProfile getSession(String sessionId) {
        return sessions.get(sessionId);
    }
}

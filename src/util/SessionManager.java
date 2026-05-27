package util;

import model.User;

import java.util.HashMap;
import java.util.UUID;

public class SessionManager {

    private static HashMap<String, User>
            sessions =
            new HashMap<>();

    public static String createSession(
            User user
    ) {

        String token =
                UUID.randomUUID()
                        .toString();

        sessions.put(token, user);

        return token;
    }

    public static User getUser(
            String token
    ) {

        return sessions.get(token);
    }

    public static void removeSession(
            String token
    ) {

        sessions.remove(token);
    }
}
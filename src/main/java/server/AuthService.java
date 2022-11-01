package server;

import utils.Files;

import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

class AuthService {
    private static AuthService single_instance = null;
    static HashMap<String, String> mapUserTokens = new HashMap<>();

    private AuthService() {
    }

    public static AuthService getInstance() {

        if (single_instance == null)
            single_instance = new AuthService();

        return single_instance;
    }

    public void createNewUser(String email, String name, String password) throws IllegalArgumentException {
        // validation:  email unique ...
        UserRepository.User userExist = UserRepository.getUserByEmail(email);

        if (userExist != null) {
            throw new IllegalArgumentException("The user already exists");
        }

        UserRepository.User newUser = new UserRepository.User(email, name, password);
        UserRepository.writeUserToDb(newUser);
    }

    public static String loginUser(String email, String password) {

        UserRepository.User user = UserRepository.getUserByEmail(email);

        if (!user.getPassword().equals(password)) {
            throw new NullPointerException("user's email or password don't match");
        }
        String token = generateUniqueToken();
        mapUserTokens.put(token, String.valueOf(user.getId()));
        return token;
    }

    private static String generateUniqueToken() {

        StringBuilder token = new StringBuilder();
        long currentTimeInMilisecond = Instant.now().toEpochMilli();

        return token.append(currentTimeInMilisecond).append("-")
                .append(UUID.randomUUID().toString()).toString();
    }

    public static Integer getUserId(String token) throws NullPointerException {

        String id = mapUserTokens.get(token);

        if (id == null) {
            throw new NullPointerException();
        }
        return Integer.valueOf(id);
    }
}

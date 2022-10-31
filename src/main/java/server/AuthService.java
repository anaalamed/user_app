package server;

import utils.Files;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

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

    public void createNewUser(String email, String name, String password) {
        // validation:  email unique ...

        UserRepository.User newUser = new UserRepository.User(email, name, password);
        UserRepository.writeUserToDb(newUser);
    }

    public static String loginUser(String email, String password) {

        UserRepository.User user = UserRepository.getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            // generate token
            String token = String.valueOf(generateUniqueToken());
            mapUserTokens.put(token, String.valueOf(user.getId()));
            System.out.println("hashmap login tokens: " + mapUserTokens);
            return token;
        }
        return null;
    }

    private static int generateUniqueToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.hashCode();
    }

    public static Integer getUserId(String token) {
        return Integer.valueOf(mapUserTokens.get(token));
    }
}

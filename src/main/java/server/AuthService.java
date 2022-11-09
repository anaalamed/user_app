package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;


class AuthService {
    private static Logger logger = LogManager.getLogger(AuthService.class.getName());
    private static AuthService single_instance = null;
    static HashMap<String, String> mapUserTokens = new HashMap<>();


    private AuthService() {
        logger.trace("enter to AuthService() constructor");
    }

    public static AuthService getInstance() {
        logger.trace("enter to getInstance() function");
        if (single_instance == null)
            single_instance = new AuthService();
        logger.trace("singleton has been created: " + single_instance);
        return single_instance;
    }

    protected void createNewUser(String email, String name, String password) throws IllegalArgumentException {
        logger.trace("enter to createNewUser() function");
        UserRepository.User userExist = UserRepository.getUserByEmail(email);
        logger.debug("user Exist is : " + userExist);
        if (userExist != null) {
            logger.warn("Input is not valid, createNewUser failed");
            throw new IllegalArgumentException("The user already exists");
        }

        UserRepository.User newUser = new UserRepository.User(email, name, password);
        UserRepository.writeUserToDb(newUser);
    }

    protected static String loginUser(String email, String password) {
        logger.trace("enter to loginUser() function");
        UserRepository.User user = UserRepository.getUserByEmail(email);
        logger.debug("equals passwords is : " + user.getPassword().equals(password));
        if (!user.getPassword().equals(password)) {
            logger.warn("Input is not valid, loginUser failed");
            throw new NullPointerException("user's email or password don't match");
        }
        String token = generateUniqueToken();
        mapUserTokens.put(token, String.valueOf(user.getId()));
        return token;
    }

    private static String generateUniqueToken() {
        logger.trace("enter to generateUniqueToken() function");

        StringBuilder token = new StringBuilder();
        long currentTimeInMilisecond = Instant.now().toEpochMilli();

        return token.append(currentTimeInMilisecond).append("-")
                .append(UUID.randomUUID().toString()).toString();
    }

    protected static Integer getUserId(String token) throws NullPointerException {
        String id = mapUserTokens.get(token);
        logger.debug("Input id is : " + id);
        if (id == null) {
            logger.warn("Input is not valid, getUserId failed");
            throw new NullPointerException();
        }
        return Integer.valueOf(id);
    }

}
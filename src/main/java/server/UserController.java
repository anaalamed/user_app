package server;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Validate;


public class UserController {
    private static UserController single_instance = null;
    private static Logger logger = LogManager.getLogger(UserController.class.getName());

    private UserController() {
    }

    public static UserController getInstance() {
        if (single_instance == null)
            single_instance = new UserController();
        logger.trace("singleton has been created: " + single_instance);

        return single_instance;
    }

    public static void updateName(String token, String name) {
        logger.info("start updateName method");
        try {
            Integer userId = AuthService.getUserId(token);
            logger.debug("userId: " + userId);
            if (Validate.validateName(name)) {
                UserService.updateName(userId, name);
            } else {
                logger.warn("validation - Invalid name");
            }
        } catch (NullPointerException error) {
            logger.warn("Invalid Token");
        }
    }

    public static void updatePassword(String token, String password) {
        logger.info("start updatePassword method");
        try {
            Integer userId = AuthService.getUserId(token);
            logger.debug("userId: " + userId);
            if (Validate.validatePassword(password)) {
                UserService.updatePassword(userId, password);
            } else {
                logger.warn("validation - Invalid password");
            }
        } catch (NullPointerException error) {
            logger.warn("Invalid Token");
        }
    }

    public static void updateEmail(String token, String email) {
        logger.info("start updateEmail method");
        try {
            Integer userId = AuthService.getUserId(token);
            logger.debug("userId: " + userId);
            if (Validate.validateEmail(email)) {
                UserService.updateEmail(userId, email);
            } else {
                logger.warn("validation - Invalid email");
            }
        } catch (NullPointerException error) {
            logger.warn("Invalid Token");
        }
    }

    public static void removeUser(String token) {
        logger.info("start removeUser method");
        try {
            Integer userId = AuthService.getUserId(token);
            logger.debug("userId: " + userId);
            UserService.removeUser(userId);
        } catch (NullPointerException error) {
            logger.warn("Invalid Token");
        }
    }
}

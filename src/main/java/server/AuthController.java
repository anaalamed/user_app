package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Validate;

public class AuthController {
    private static AuthController single_instance = null;
    private static AuthService authService;
    private static Logger logger = LogManager.getLogger(AuthController.class.getName());

    private AuthController() {
        authService = AuthService.getInstance();
    }

    public static AuthController getInstance() {
        logger.trace("enter to getInstance function");
        if (single_instance == null)
            single_instance = new AuthController();

        logger.trace("singleton has been created: " + single_instance);
        return single_instance;
    }

    public static void registration(String email, String name, String password) throws IllegalArgumentException {
        logger.trace("enter to registration function");
        boolean isValidateUser = validateUser(email, name, password);

        logger.debug("user validation is: " + isValidateUser);
        if (!isValidateUser) {
            logger.warn("input is not valid, registration failed");
            throw new IllegalArgumentException("input is not valid, registration failed");
        }

        try {
            authService.createNewUser(email, name, password);
        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException);
        }
    }

    public static String login(String email, String password) throws IllegalArgumentException {
        logger.trace("enter to login function");
        boolean isValidateLoginFields = validateLoginFields(email, password);

        logger.debug("login fields validations are: " + isValidateLoginFields);
        if (!isValidateLoginFields) {
            logger.warn("input is not valid, login failed");
            throw new IllegalArgumentException("input is not valid, login failed");
        }
        return authService.loginUser(email, password);
    }

    private static boolean validateUser(String email, String name, String password) {
        logger.trace("enter to validateUser function");
        boolean isEmailValid = Validate.validateEmail(email);
        boolean isNameValid = Validate.validateName(name);
        boolean isPasswordValid = Validate.validatePassword(password);

        return isEmailValid && isNameValid && isPasswordValid;
    }


    private static boolean validateLoginFields(String email, String password) {
        logger.trace("enter to validateLoginFields function");
        boolean isEmailValid = Validate.validateEmail(email);
        boolean isPasswordValid = Validate.validatePassword(password);

        return isEmailValid && isPasswordValid;
    }
}

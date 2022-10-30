package server;

import utils.Validate;

public class AuthController {
    private static AuthController single_instance = null;
    private static AuthService authService;

    public AuthController() {
        authService = AuthService.getInstance();
    }

    public static AuthController getInstance() {
        if (single_instance == null)
            single_instance = new AuthController();

        return single_instance;
    }

    public static String  login( String email, String password) {
        boolean isValidateLoginFields = validateLoginFields( email, password);

        if (isValidateLoginFields) {
            return authService.loginUser(email, password);
        } else {
            System.out.println("user data is not valid. login failed");
        }
        return null;
    }

    public static void registration( String email, String name, String password) {
        boolean isValidateUser = validateUser(email, name, password);

        if (isValidateUser) {
            authService.createNewUser(email, name, password);
        } else {
            System.out.println("data for new user is not valid");
        }
    }

    public static boolean validateUser(String email, String name, String password) {

        boolean isEmailValid = Validate.validateEmail(email);
        boolean isNameValid = Validate.validateName(name);
        boolean isPasswordValid = Validate.validatePassword(password);

        // password - regex
        if (isEmailValid && isNameValid && isPasswordValid) {
            return true;
        }
        return false;
    }


    public static boolean validateLoginFields( String email, String password) {
        // email - email
        // password - regex

        return true;
    }
    public static void validateId() {

    }
}

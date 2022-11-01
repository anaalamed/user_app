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

    public static void registration( String email, String name, String password) throws IllegalArgumentException {
        boolean isValidateUser = validateUser(email, name, password);

        if (!isValidateUser) {
            throw new IllegalArgumentException("input is not valid, registration failed");
        }

        try {
            authService.createNewUser(email, name, password);
        }
        catch (NullPointerException nullPointerException){
            System.out.println(nullPointerException);
        }
    }

    public static String login( String email, String password) throws  IllegalArgumentException{
        boolean isValidateLoginFields = validateLoginFields( email, password);

        if (!isValidateLoginFields) {
            throw new IllegalArgumentException("input is not valid, login failed");
        }
        return authService.loginUser(email, password);
    }

    public static boolean validateUser(String email, String name, String password) {

        boolean isEmailValid = Validate.validateEmail(email);
        boolean isNameValid = Validate.validateName(name);
        boolean isPasswordValid = Validate.validatePassword(password);

        return isEmailValid && isNameValid && isPasswordValid;
    }

    public static boolean validateLoginFields( String email, String password) {
        boolean isEmailValid = Validate.validateEmail(email);
        boolean isPasswordValid = Validate.validatePassword(password);

        if (isEmailValid && isPasswordValid) {
            return true;
        }

        System.out.println("login has failed for email: " + email);
        return false;
    }
}

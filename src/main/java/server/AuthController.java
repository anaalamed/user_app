package server;

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

    public static void login( String email, String password) {
        boolean isValidateLoginFields = validateLoginFields( email, password);

        if (isValidateLoginFields) {
            authService.loginUser(email, password);
        } else {
            System.out.println("user data is not valid. login failed");
        }
    }

    public static void registration(String id, String email, String name, String password) {
        boolean isValidateUser = validateUser(id, email, name, password);

        if (isValidateUser) {
            authService.createNewUser(id, email, name, password);
        } else {
            System.out.println("data for new user is not valid");
        }
    }

    public static boolean validateUser(String id, String email, String name, String password) {
        validateId();
        // validation id - only numbers - 4 digits - String
        // email - email
        // name - just letters
        // password - regex

        return true;
    }


    public static boolean validateLoginFields( String email, String password) {
        // email - email
        // password - regex

        return true;
    }
    public static void validateId() {

    }



}

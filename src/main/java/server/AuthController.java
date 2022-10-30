package server;

public class AuthController {
    private static AuthController single_instance = null;
    private AuthService authService;

    public AuthController() {
        authService = AuthService.getInstance();
    }

    public static AuthController getInstance() {
        if (single_instance == null)
            single_instance = new AuthController();

        return single_instance;
    }

    public void registration(String id, String email, String  name, String password) {
        boolean isValidateUser = validateUser(id, email, name, password);

        if (isValidateUser) {
            authService.createNewUser(id, email, name, password);
        } else {
            System.out.println("user data is not valid");
        }
    }

    public boolean validateUser(String id, String email, String  name, String password) {
        validateId();
        // validation id - only numbers - 4 digits - String
        // email - email
        // name - just letters
        // password - regex

        return true;
    }

    public static void validateId() {

    }



}

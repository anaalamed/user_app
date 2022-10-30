package server;

public class AuthController {
    private static AuthController single_instance = null;



    public static AuthController getInstance() {
        if (single_instance == null)
            single_instance = new AuthController();

        return single_instance;
    }

    public void validateUser(String id, String email, String  name, String password) {
        validateId();
        // validation id - only numbers - 4 digits - String
        // email - email
        // name - just letters
        // password - regex

        AuthService authService = AuthService.getInstance();
        authService.createNewUser(id, email, name, password);
    }

    public static void validateId() {

    }



}

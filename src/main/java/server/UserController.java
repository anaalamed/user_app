package server;

import utils.Validate;

public class UserController {
    private static UserController single_instance = null;
    private static UserService userService;
    private static AuthService authService;

    private UserController() {
        userService = UserService.getInstance();
    }

    public static UserController getInstance() {
        if (single_instance == null)
            single_instance = new UserController();

        return single_instance;
    }

    public static void updateName(String token, String name) {
        try {
            Integer userId = AuthService.getUserId(token);
            if (Validate.validateName(name)) {
                UserService.updateName(userId, name);
            } else {
                System.out.println("Invalid Name: " + name);
            }
        } catch (NullPointerException error) {
            System.out.println("Invalid Token");
        }
    }

    public static void updatePassword(String token, String password) {
        try {
            Integer userId = AuthService.getUserId(token);
            if (Validate.validatePassword(password)) {
                UserService.updatePassword(userId, password);
            } else {
                System.out.println("Invalid password: " + password);
            }
        } catch (NullPointerException error) {
            System.out.println("Invalid Token");
        }
    }

    public static void updateEmail(String token, String email) {
        try {
            Integer userId = AuthService.getUserId(token);
            if (Validate.validateEmail(email)) {
                UserService.updateEmail(userId, email);
            } else {
                System.out.println("Invalid email: " + email);
            }
        } catch (NullPointerException error) {
            System.out.println("Invalid Token");
        }
    }

    public static void removeUser(String token) {
        try {
            Integer userId = AuthService.getUserId(token);
            UserService.removeUser(userId);
        } catch (NullPointerException error) {
            System.out.println("Invalid Token");
        }
    }
}

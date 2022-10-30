package server;

public class UserController {
    private static UserController single_instance = null;
    private static UserService userService;
    private static AuthService authService;

    public UserController() {
        userService = UserService.getInstance();
    }
    public static UserController getInstance() {
        if (single_instance == null)
            single_instance = new UserController();

        return single_instance;
    }

    public static void updateUser(String token, String name, String email, String password) {
        String userId = AuthService.getUserId(token);
        System.out.println(userId);

        // validation

        UserService.updateName(userId, name);
    }
}

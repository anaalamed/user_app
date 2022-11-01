package server;

public class Server {
    private static Server single_instance = null;

    public static Server getInstance() {
        if (single_instance == null)
            single_instance = new Server();

        return single_instance;
    }

    private Server() {
        // init singletons
        AuthController.getInstance();
        AuthService.getInstance();
        UserController.getInstance();
        UserService.getInstance();
        UserRepository.getInstance();
    }
}

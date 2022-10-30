package server;

public class Server {
    private static Server single_instance = null;

    private AuthController authController;
//    private AuthService authService;


    public static Server getInstance() {
        if (single_instance == null)
            single_instance = new Server();

        return single_instance;
    }

    public Server() {
        authController = new AuthController();
    }


}

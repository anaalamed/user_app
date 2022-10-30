import server.AuthController;

public class Main {
    public static void main(String[] args) {

        AuthController authController = new AuthController();
        authController.registration("4", "b", "b", "11");

    }
}

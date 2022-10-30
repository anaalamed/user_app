import server.AuthController;
import server.Server;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();

        // id not from main - random at service
        AuthController.registration("1", "a", "a", "a");
        AuthController.registration("2", "b", "b", "b");

        // login failed
        AuthController.login("a", "b");

        // login success
        AuthController.login("a", "a");
        AuthController.login("b", "b");
    }
}

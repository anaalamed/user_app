import server.AuthController;
import server.Server;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();

        // id not from main - random at service
        AuthController.registration("1", "a", "a", "a");
        AuthController.registration("2", "b", "b", "b");

        // login failed
        String userToken = AuthController.login("b", "a");
        System.out.println("user token: " + userToken);

        // login success
        String userToken2 = AuthController.login("b", "b");
        System.out.println("\nuser token: " + userToken2);
    }
}

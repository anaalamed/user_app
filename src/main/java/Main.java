import server.AuthController;
import server.Server;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();

        // id not from main - random at service
        AuthController.registration("1", "b", "aa", "1");

    }
}

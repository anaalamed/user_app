import server.AuthController;
import server.Server;
import server.UserController;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();

        // id not from main - random at service
        AuthController.registration("1", "a", "a", "a");
        AuthController.registration("2", "b", "b", "b");

        // login failed
        AuthController.login("a", "b");

        // login success
        String token =  AuthController.login("a", "a");
        String token2 = AuthController.login("b", "b");

        System.out.println(token);
        System.out.println(token2);


        UserController.updateUser(token, "zzz", "a", "a");

    }
}

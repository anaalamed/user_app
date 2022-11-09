import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.AuthController;
import server.Server;
import server.UserController;
import server.UserRepository;

public class Main {

    public static void main(String[] args) {
        Server.getInstance(); // we initialize all the singletons needed for the server side

        System.out.println("Registration:");
        try {
            AuthController.registration("aaa@gmail.com", "david", "aaaaaaaa1");
            AuthController.registration("bbb@gmail.com", "ana", "aaaaaaaa1");
            AuthController.registration("ccc@gmail.com", "samer", "aaaaaaaa1");
            AuthController.registration("ddd@gmail.com", "delete", "aaaaaaaa1");
            System.out.println();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException);
        }
        //-------------------------------------------------------------------------------------------------------

        System.out.println("Login failed illustration: ");
        try {
            AuthController.login("aaa@gmail.com", "wrongPassword");       // password not validate
        } catch (IllegalArgumentException | NullPointerException exception) {
            System.out.println(exception);
        }

        try {
            AuthController.login("aaa@gmail.com", "wrongPassword1");      // password doesn't match
        } catch (IllegalArgumentException | NullPointerException exception) {
            System.out.println(exception);
        }
        //-------------------------------------------------------------------------------------------------------


        System.out.println("Login success: ");
        try {
            String tokenDavid = AuthController.login("aaa@gmail.com", "aaaaaaaa1");
            String tokenAna = AuthController.login("bbb@gmail.com", "aaaaaaaa1");
            String tokenSamer = AuthController.login("ccc@gmail.com", "aaaaaaaa1");
            String tokenDelete = AuthController.login("ddd@gmail.com", "aaaaaaaa1");

            System.out.println("token ana: " + tokenAna);
            System.out.println("token david: " + tokenDavid);
            System.out.println("token samer: " + tokenSamer);
            System.out.println("token to Delete User: " + tokenDelete);
            System.out.println();

            // update
            UserController.updateName(tokenDavid, "yudin");
            UserController.updateEmail(tokenAna, "new@gmail.com");
            UserController.updatePassword(tokenSamer, "new123456");
            UserController.removeUser(tokenDelete);


            System.out.println("hashmap users cache: " + UserRepository.getUsers());   // check only
        } catch (IllegalArgumentException | NullPointerException exception) {
            System.out.println(exception);
        }
    }
}

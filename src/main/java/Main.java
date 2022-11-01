import server.AuthController;
import server.Server;
import server.UserController;
import server.UserRepository;


/*
Implement an authentication flow:

    [ ] Client code:

        Can register a new user
        Can login with existing user
        Communicates with controllers only!

    [ ] Authentication controller:

        Validates client requests
        Uses AuthService to perform actions

    [ ] User controller:

        Validates client requests
        Can update user data (email/name/password) - via UserService
        Can delete a user - via UserService

    [ ] Authentication service:

        Can create a user in the user repository
        Validates user credentials when logging in

        User service:

        Can update/delete users from the repository

        User repository

        Can read/write/update/delete users from the system
        Stores users in json files

    [ ] Users have:

        [V] id - unique!
        [ ] email - unique!
        [V] name
        [V] password

    Flow:
        The users need to register first.
        After a user is registered he/she can login.
        When logging in the client receives a Token, this token is used to identify the user when performing actions via the UserController.
        An invalid or non-existing token will return an error.
        The program should save the token while running so the client can use the token only for the duration of the session.
        User data is persisted (saved in files) between sessions but tokens are not.
        Users can perform action via user controller only on themselves.
 */

public class Main {
    public static void main(String[] args) {
        Server.getInstance(); // we initialize all the singletons needed for the server side

        System.out.println("Registration: ");
        AuthController.registration("aaa@gmail.com", "david", "aaaaaaaa1");
        AuthController.registration("bbb@gmail.com", "ana", "aaaaaaaa1");
        AuthController.registration("ccc@gmail.com", "samer", "aaaaaaaa1");
        AuthController.registration("ddd@gmail.com", "delete", "aaaaaaaa1");
        System.out.println();

        try{
            // login failed
            System.out.println("Login failed illustration: ");
            AuthController.login("aaa@gmail.com", "wrongPassword");       // password not validate
            AuthController.login("aaa@gmail.com", "wrongPassword1");      // password doesn't match
        }
        catch (IllegalArgumentException illegalArgumentException)
        {
            System.out.println(illegalArgumentException);
        }
        System.out.println();

        // login success
        System.out.println("Login success: ");
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
    }
}

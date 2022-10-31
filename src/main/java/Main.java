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

        AuthController.registration("aaa@gmail.com", "david", "aaaaaaaa1");
        AuthController.registration("bbb@gmail.com", "ana", "aaaaaaaa1");

        // login failed

        AuthController.login("aaa@gmail.com", "wrong password");


        // login success
        String token = AuthController.login("aaa@gmail.com", "aaaaaaaa1");
        String token2 = AuthController.login("bbb@gmail.com", "aaaaaaaa1");

        System.out.println("token1: " + token);
        System.out.println("token2: " + token2);


        System.out.println("hashmap users cache: " + UserRepository.getUsers());   // check only

    }
}

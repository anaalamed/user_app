package server;

import utils.Files;

import java.util.HashMap;

public class UserService {
    private static UserService single_instance = null;

    public static UserService getInstance() {
        if (single_instance == null)
            single_instance = new UserService();

        return single_instance;
    }

    public static void updateName(Integer id, String name) {
        UserRepository.User user = UserRepository.getUserById(id);
        if (!user.getName().equals(name)) {
            user.setName(name);
            UserRepository.writeUserToDb(user);
        }
    }

    public static void updateEmail(Integer id, String email) {
        UserRepository.User user = UserRepository.getUserById(id);
        if (!user.getEmail().equals(email)) {
            user.setEmail(email);
            UserRepository.writeUserToDb(user);
        }
    }

    public static void updatePassword(Integer id, String password) {
        UserRepository.User user = UserRepository.getUserById(id);

        if (!user.getPassword().equals(password)) {
            user.setPassword(password);
            UserRepository.writeUserToDb(user);
        }
    }

    public static void removeUser(Integer id) {
        UserRepository.User user = UserRepository.getUserById(id);

        if (user != null) {
            UserRepository.removeUserFromDb(id);
        }
    }
}

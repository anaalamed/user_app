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

        if (user !=null) {
            if (!user.getName().equals(name)) {
                user.setName(name);
                UserRepository.writeUserToDb(user);
            }
        }
    }


    // function delete
}

package server;

public class UserService {
    private static UserService single_instance = null;

    public static UserService getInstance() {
        if (single_instance == null)
            single_instance = new UserService();

        return single_instance;
    }

    protected static void updateName(Integer id, String name) {
        UserRepository.User user = getUserById(id);

        if (user.getName().equals(name)) {
            throw new IllegalArgumentException("Cannot update the same name");
        }
        user.setName(name);
        UserRepository.writeUserToDb(user);
    }

    protected static void updateEmail(Integer id, String email) {
        UserRepository.User user = getUserById(id);
        // need to add validation for email already exists
        if (user.getEmail().equals(email)) {
            throw new IllegalArgumentException("Cannot update the same email");
        }
        user.setEmail(email);
        UserRepository.writeUserToDb(user);
    }

    protected static void updatePassword(Integer id, String password) {
        UserRepository.User user = getUserById(id);

        if (user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Cannot update the same password");
        }
        user.setPassword(password);
        UserRepository.writeUserToDb(user);
    }

    protected static void removeUser(Integer id) {
        UserRepository.User user = getUserById(id);

        if (user != null) {
            UserRepository.removeUserFromDb(id);
        }
    }

    private static UserRepository.User getUserById(Integer id) {
        return UserRepository.getUserById(id);
    }
}

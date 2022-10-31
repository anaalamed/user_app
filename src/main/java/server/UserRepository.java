package server;

import java.util.Map;
import java.util.UUID;

public class UserRepository {
    private static UserRepository single_instance = null;

    private Map<Integer, User> users;     // cache

    static class User {
        private int id;
        private String email, name, password;

        public User(String email, String name, String password) {
            id = generateUniqueId();
            this.email = email;
            this.name = name;
            this.password = password;
        }

        private int generateUniqueId() {
            UUID uuid = UUID.randomUUID();
            return uuid.hashCode();
        }
        public int getId(){
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }
    }


    public static UserRepository getInstance() {
        if (single_instance == null)
            single_instance = new UserRepository();

        return single_instance;
    }
    // methos read all files -> cache ...



    public void updateUser(User user) {
//        User user1 = users.get(user.id);   or

        if (users.containsKey(user.id)) {
            users.put(user.id, user);
        }
//        else create ...

        // write to file
    }

}

package server;

import utils.Files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRepository {
    private static UserRepository single_instance = null;
    private static Map<Integer, User> users = new HashMap<>();     // cache

    private static final String BASE_ROUTE = "src/main/java/server/repo";

    static class User {
        private int id;
        private String email, name, password;

        protected User(String email, String name, String password) {
            id = generateUniqueId();
            this.email = email;
            this.name = name;
            this.password = password;
        }

        private int generateUniqueId() {
            return UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        }

        public int getId() {
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


        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static UserRepository getInstance() {
        if (single_instance == null) {
            single_instance = new UserRepository();
            users = cacheUsersFilesFromRepo();
        }
        return single_instance;
    }

    protected static void writeUserToDb(User user) {
        int id = user.getId();

        HashMap<String, String> mapToJson = new HashMap<>();

        mapToJson.put("id", String.valueOf(id));
        mapToJson.put("email", user.getEmail());
        mapToJson.put("name", user.getName());
        mapToJson.put("password", user.getPassword());

        String filename = BASE_ROUTE + "/" + id + ".json";
        Files.writeJsonToFile(filename, mapToJson);
        users.put(user.getId(), user);
    }

    protected static void removeUserFromDb(int id) {
        String filename = BASE_ROUTE + "/" + id + ".json";
        Files.removeFile(filename);

        users.remove(id);
    }

    protected static User getUserById(Integer id) {
        try {
            return users.get(id);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    protected static User getUserByEmail(String email) {
        for (Integer i : users.keySet()) {
            if (users.get(i).getEmail().equals(email)) {
                return users.get(i);
            }
        }
        return null;
    }

    private static Map<Integer, User> cacheUsersFilesFromRepo() {
        File folder = new File(BASE_ROUTE);
        File[] listOfFiles = folder.listFiles();

        String filename = BASE_ROUTE + "/";

        for (int i = 0; i < listOfFiles.length; i++) {
            HashMap<String, String> fileContent = Files.readFromFile(filename + listOfFiles[i].getName());
            users.put(Integer.valueOf(fileContent.get("id")), new User(fileContent.get("email"), fileContent.get("name"), fileContent.get("password")));
        }
        return users;
    }
}

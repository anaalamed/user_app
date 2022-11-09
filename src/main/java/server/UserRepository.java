package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Files;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRepository {
    private static UserRepository single_instance = null;
    private static Map<Integer, User> users = new HashMap<>();     // cache

    private static final String BASE_ROUTE = "src/main/java/server/repo";

    private static Logger logger = LogManager.getLogger(UserRepository.class.getName());

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
            logger.trace("enter to generateUniqueId function");
            return UUID.randomUUID().hashCode() & Integer.MAX_VALUE;
        }

        public int getId() {
            logger.trace("enter to getId function");
            return id;
        }

        public String getEmail() {
            logger.trace("enter to getEmail function");
            return email;
        }

        public String getName() {
            logger.trace("enter to getName function");
            return name;
        }

        public String getPassword() {
            logger.trace("enter to getPassword function");
            return password;
        }


        public void setEmail(String email) {
            logger.trace("enter to setEmail function");
            this.email = email;
        }

        public void setName(String name) {
            logger.trace("enter to setName function");
            this.name = name;
        }

        public void setPassword(String password) {
            logger.trace("enter to setPassword function");
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
        logger.trace("enter to getUsers function");
        return users;
    }

    public static UserRepository getInstance() {
        logger.trace("enter to getInstance function");
        if (single_instance == null) {
            single_instance = new UserRepository();
            users = cacheUsersFilesFromRepo();
        }
        return single_instance;
    }

    protected static void writeUserToDb(User user) {
        logger.trace("enter to writeUserToDb function");
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
        logger.trace("enter to writeUserToDb function");
        String filename = BASE_ROUTE + "/" + id + ".json";
        Files.removeFile(filename);

        logger.debug("user with id: " + id + " has been removed");
        users.remove(id);
    }

    protected static User getUserById(Integer id) {
        logger.trace("enter to getUserById function");
        try {
            logger.debug("user with id: " + id + " has been returned");
            return users.get(id);
        } catch (NullPointerException e) {
            logger.warn(e);
        }
        logger.warn("null has been returned");
        return null;
    }

    protected static User getUserByEmail(String email) {
        logger.trace("enter to getUserByEmail function");
        for (Integer i : users.keySet()) {
            if (users.get(i).getEmail().equals(email)) {
                logger.debug("user with email: " + email + " has been returned");
                return users.get(i);
            }
        }
        logger.warn("null has been returned");
        return null;
    }

    private static Map<Integer, User> cacheUsersFilesFromRepo() {
        logger.trace("enter to cacheUsersFilesFromRepo function");
        File folder = new File(BASE_ROUTE);
        File[] listOfFiles = folder.listFiles();

        String filename = BASE_ROUTE + "/";

        for (int i = 0; i < listOfFiles.length; i++) {
            HashMap<String, String> fileContent = Files.readFromFile(filename + listOfFiles[i].getName());
            users.put(Integer.valueOf(fileContent.get("id")), new User(fileContent.get("email"), fileContent.get("name"), fileContent.get("password")));
        }
        logger.debug("cached status is: " + users.toString());
        return users;
    }
}

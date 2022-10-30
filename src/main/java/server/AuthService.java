package server;

import utils.Files;

import java.io.File;
import java.util.HashMap;

class AuthService {
    private static AuthService single_instance = null;
    static HashMap<String, String> mapUserTokens = new HashMap<>();


    private AuthService() {
    }

    public static AuthService getInstance() {
        if (single_instance == null)
            single_instance = new AuthService();

        return single_instance;
    }

    public void createNewUser(String id, String email, String  name, String password) {
        // validation: id unique, email unique ...

        User newUser = new User(id, email, name, password);

        HashMap<String, String> mapToJson = new HashMap<String, String>();
        mapToJson.put("id", id);
        mapToJson.put("email", email);
        mapToJson.put("name", name);
        mapToJson.put("password", password);

        String filename = "src/main/java/server/repo/" + id + ".json";
        Files.writeJsonToFile(filename, mapToJson);

    }

    public static String loginUser(String email, String password) {

        File folder = new File("src/main/java/server/repo");
        File[] listOfFiles = folder.listFiles();

        String filename = "src/main/java/server/repo/";

        for (int i = 0; i < listOfFiles.length; i++) {
                HashMap<String, String> fileContent =  Files.readFromFile(filename + listOfFiles[i].getName());

                if (fileContent.get("email").equals(email)) {
                    if (fileContent.get("password").equals(password)) {
                        // generate token
                        String token = "1234";
                        mapUserTokens.put( token, fileContent.get("id"));
                        System.out.println(mapUserTokens);
                        return token;
                    } else {
                        System.out.println("the password is invalid");
                    }
                }

        }
        System.out.println("login failed");
        return null;
    }

    public static String getUserId(String token) {
        return mapUserTokens.get(token);
    }

}

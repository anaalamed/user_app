package server;

import utils.Files;

import java.util.HashMap;

class AuthService {
    private static AuthService single_instance = null;


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


}

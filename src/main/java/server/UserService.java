package server;

import utils.Files;

import java.util.HashMap;

public class UserService {
    private static UserService single_instance = null;
    static final String filename = "src/main/java/server/repo/";

    public static UserService getInstance() {
        if (single_instance == null)
            single_instance = new UserService();

        return single_instance;
    }

    public static void updateName(String id, String name) {
        String path = filename + id  + ".json";

        HashMap<String, String> fileContent = Files.readFromFile(path);
        System.out.println(fileContent);

        fileContent.put("name", name);
        Files.writeJsonToFile(path, fileContent);





    }


    // function delete
}

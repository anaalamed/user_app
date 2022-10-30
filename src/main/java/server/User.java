package server;

import java.util.UUID;

public class User {
    private final int id;
    private final String email, name, password;

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
}

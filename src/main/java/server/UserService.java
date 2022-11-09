package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private static UserService single_instance = null;
    private static Logger logger = LogManager.getLogger(UserService.class.getName());


    public static UserService getInstance() {
        if (single_instance == null)
            single_instance = new UserService();
        logger.trace("singleton has been created: " + single_instance);
        return single_instance;
    }

    protected static void updateName(Integer id, String name) {
        logger.info("start updateName method");
        UserRepository.User user = getUserById(id);
        logger.debug("user " + user);

        if (user.getName().equals(name)) {
            logger.warn("Cannot update the same name");
            throw new IllegalArgumentException("Cannot update the same name");
        }
        user.setName(name);
        UserRepository.writeUserToDb(user);
    }

    protected static void updateEmail(Integer id, String email) {
        logger.info("start updateEmail method");

        UserRepository.User user = getUserById(id);
        logger.debug("user " + user);

        if (user.getEmail().equals(email)) {
            logger.warn("Cannot update the same email");
            throw new IllegalArgumentException("Cannot update the same email");
        }
        user.setEmail(email);
        UserRepository.writeUserToDb(user);
    }

    protected static void updatePassword(Integer id, String password) {
        logger.info("start updatePassword method");

        UserRepository.User user = getUserById(id);
        logger.debug("user " + user);

        if (user.getPassword().equals(password)) {
            logger.warn("Cannot update the same password");
            throw new IllegalArgumentException("Cannot update the same password");
        }
        user.setPassword(password);
        UserRepository.writeUserToDb(user);
    }

    protected static void removeUser(Integer id) {
        logger.info("start removeUser method");

        UserRepository.User user = getUserById(id);
        logger.debug("user " + user);

        if (user != null) {
            UserRepository.removeUserFromDb(id);
        }
    }

    private static UserRepository.User getUserById(Integer id) {
        return UserRepository.getUserById(id);
    }
}

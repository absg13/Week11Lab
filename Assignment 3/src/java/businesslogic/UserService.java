package businesslogic;

import dataaccess.UserDB;
import domainmodel.*;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUserId(username);
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(String username, String password, String email, boolean active, String firstname, String lastname, Role role, List<Note> noteList) throws Exception {
        User user = new User(username, password, email, active, firstname, lastname, role, noteList);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUserId(username);
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String email, boolean active, String firstname, String lastname, Role role, List<Note> noteList) throws Exception {
        User user = new User(username, password, email, active, firstname, lastname, role, noteList);
        return userDB.insert(user);
    }
}

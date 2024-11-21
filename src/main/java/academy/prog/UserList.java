package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.LinkedList;
import java.util.List;

public class UserList {
    private static final UserList userlist = new UserList();
    private Gson gson;
    private final List<User> ListU =  new LinkedList<>();

    public static UserList getInstance() {
        return userlist;
    }
    private UserList() {
        gson = new GsonBuilder().create();
    }
    public synchronized void add(User u) {
        ListU.add(u);
    }

    public synchronized String toJSON () {
        return gson.toJson(ListU);
    }

    public List<User> getListU() {
        return ListU;
    }
}


package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class User {

    @JsonCreator
    public User(@JsonProperty("_id")        ObjectId _id,
                @JsonProperty("first_name") String first_name,
                @JsonProperty("last_name")  String last_name,
                @JsonProperty("password")   String password,
                @JsonProperty("email")      String email) {
        this._id        = _id;
        this.first_name = first_name;
        this.last_name  = last_name;
        this.password   = password;
        this.email      = email;
    }

    private ObjectId _id;
    private String first_name;
    private String last_name;
    private String password;
    private String email;


    public ObjectId get_id() {
        return _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<User> _users = List.of(
            new User(new ObjectId(), "Max", "Muster", "test1234!", "max.muster@email.com"),
            new User(new ObjectId(), "Anna", "Schäfer", "test1234!", "anna.schäfer@email.com"),
            new User(new ObjectId(), "otto", "Haus", "test1234!", "otto.haus@email.com")
    );

    public static boolean _save(User user) {
        User oldUser = _findByEmail(user.email);
        User._remove(oldUser);
        return User._add(user);
    }

    public static boolean _add(User user) {
        return User._users.add(user);
    }

    public static User _remove(User user) {
        int index = User._users.indexOf(user);
        return User._users.remove(index);
    }

    public static User _findByEmail(String email) {
        User result = null;
        for(User user : User._users) {
            if(user.email.equals(email)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public static List<String> _getAllEmails() {
        List<String> emails = new ArrayList<>();
        for (User user : User._users) {
            emails.add(user.email);
        }
        return emails;
    }



}

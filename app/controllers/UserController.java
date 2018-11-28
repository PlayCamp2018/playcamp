package controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import models.Database.User;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserController extends Controller {

    @Inject
    UserRepository users;

    public Result get() {
        String sessEmail = session(Application.SESSION_KEY);
        if (sessEmail == null) { return ok("Please login first."); }

        User user = users.findByEmail(sessEmail);

        return ok(views.html.User.profilePage.render(user));
    }

    public Result save() {
        String sessEmail = session(Application.SESSION_KEY);
        if (sessEmail == null) { return ok("Please login first."); }

        //TODO: returned Results, only ok() gets correct displayed
        if (request() == null) {
            return ok("Request is null");
        }
        if (request().body() == null) {
            return ok("Request body is null");
        }

        JsonNode json = request().body().asJson();

        if (json == null) {
            return ok("JSON is null");
        }

        User user = users.findByEmail(sessEmail);

        if (user == null) {
            return ok("E-Mail not found.");
        }

        String firstname = json.get("firstname").textValue();
        String lastname = json.get("lastname").textValue();
        String password = json.get("password").textValue();
        String password2 = json.get("password2").textValue();

        if (!password.equals(password2)) {
            return ok("Passwords are not the same.");
        }

        //TODO: set email?
        //TODO: hash pw
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);

        users.save(user);

        return get();
    }

    public Result getAllUsers() {
        String sessEmail = session(Application.SESSION_KEY);
        if (sessEmail == null) { return ok("Please login first."); }

        //TODO: nested class should not be here
        class JsonUser {
            @JsonProperty("_id")
            ObjectId id;
            @JsonProperty("firstname")
            String firstname;
            @JsonProperty("lastname")
            String lastname;
            @JsonProperty("email")
            String email;
        }

        List<JsonUser> jsonUserList = new ArrayList<>();

        List<User> userList = users.getAllUsers();


        for (User u : userList) {
            JsonUser j = new JsonUser();
            j.id        = u.getId();
            j.firstname = u.getFirstname();
            j.lastname  = u.getLastname();
            j.email     = u.getEmail();
            jsonUserList.add(j);
        }

        return ok(Json.toJson(jsonUserList));
    }

}

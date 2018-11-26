package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Database.User;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UserRepository;
import utils.Utils;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginController extends Controller {

    @Inject
    UserRepository users;

    public Result index() {
        return ok(views.html.login.render());
    }

    public Result login() {
        //TODO: returned Results
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

        String email = json.get("login_email").asText();

        // TODO: use hash of PW
        String password = json.get("login_password").asText();
        String passwordHash = "";

        try {
            passwordHash = Utils.hash(password);
        } catch (NoSuchAlgorithmException e) {
            return internalServerError(e.getMessage());
        } catch (InvalidKeySpecException e) {
            return internalServerError(e.getMessage());
        }

        User user = users.findByEmail(email);

        if (user == null) {
            return ok("No such E-mail");
        }

        if (user.getPassword().equals(password)) {
            return ok("LOGGED IN");
        }

        return ok("Wrong password");
    }
}

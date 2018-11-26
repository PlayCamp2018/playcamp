package controllers;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.UserRepository;
import models.Database.User;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import com.fasterxml.jackson.databind.JsonNode;
import utils.Utils;

public class RegistrationController extends Controller {

    @Inject
    UserRepository userRepo;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.Registration.registration.render());
    }

    public Result registrationIndex() {
        return ok(views.html.Registration.registrationIndex.render());
    }

    public Result passwordRequest() {
        return ok(views.html.Registration.passwordRequest.render());
    }

    public Result save() {
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

        String userFirstname = json.get("user_firstname").textValue();
        String userLastname = json.get("user_lastname").textValue();
        String email = json.get("user_email").textValue();
        String password = json.get("user_password").textValue();
        String password2 = json.get("user_password2").textValue();

        // TODO: sout
        System.out.println("==================================");
        System.out.println(json);

        String passwordHash = "";

        if(password.equals(password2)) {
            try {
                //TODO hash exception handling
                passwordHash = Utils.hash(password);
            } catch (Exception exception) {
                return ok("Password hash error");
            }
        }

        User user = userRepo.findByEmail(email);

        // TODO: user should be already in DB
        if(user == null) {
            user = new User();
        }

        user.setFirstname(userFirstname);
        user.setLastname(userLastname);
        user.setEmail(email);
        // TODO: use passwordHash here, but currently hash is broken
        user.setPassword(password);
        user.setProfilePicID(null);
        userRepo.save(user);

        return index();
    }
}

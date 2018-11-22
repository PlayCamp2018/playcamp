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

    public Result save() {

        JsonNode json = request().body().asJson();
        String userFirstname = json.get("user_firstname").textValue();
        String userLastname = json.get("user_lastname").textValue();
        String email = json.get("user_email").textValue();
        String password = "123";//json.get("user_passwort").textValue();
        String password2 = "123";//json.get("user_passwort2").textValue();

        if(password.equals(password2)) {
            String passwordHash = Utils.hash(password);
            User user = userRepo.findByEmail(email);
        }else {

            //ToDo
        }

       // (if 1==2){

            //ToDo
       // }else{

            user.setFirstname(userFirstname);
            user.setLastname(userLastname);
            //user.setEmail(email);
            user.setPassword(passwordHash);
            user.setProfilePicID(null);
            userRepo.save(user);

     //       }


        return index();

        }

    }


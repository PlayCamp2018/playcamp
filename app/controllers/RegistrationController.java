package controllers;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.UserRepository;

//import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;

public class RegistrationController extends Controller {
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




        return ok();
    }
}

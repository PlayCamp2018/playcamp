package controllers;

import play.mvc.*;
import scala.App;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }


    /**
     * Main page after login
     * @return
     */
    public Result homepage() {
        String sessEmail = session(Application.SESSION_KEY);

        if (sessEmail == null) { return ok("Please login first."); }

        //TODO: get data from DB to fill page with data

        return ok(views.html.Homepage.homepage.render());
    }

}

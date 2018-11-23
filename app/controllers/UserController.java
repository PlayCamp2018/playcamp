package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public Result get() {
        return ok(views.html.User.profilePage.render());
    }
}

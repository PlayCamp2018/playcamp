package controllers;

import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {
    public Result index() {
        return ok(Json.toJson(User._users));
    }
    public Result get(int id) {
        return ok(Json.toJson(User._users.get(id)));
    }

    public Result getUser() {
        return ok(views.html.getUser.render());
    }
}

package controllers;

import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {
    public Result list() {
        return ok(Json.toJson(User._users));
    }
}

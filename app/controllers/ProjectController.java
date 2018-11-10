package controllers;

import models.Project;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ProjectController extends Controller {
    public Result index() {
        return ok(Json.toJson(Project._projects));
    }
    public Result get(int id) {
        return ok(Json.toJson(Project._projects.get(id)));
    }
}

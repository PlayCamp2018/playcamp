package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ProjectController extends Controller {

    public Result create() {
        return ok(views.html.Project.newProject.render());
    }

    public Result edit() {
        return ok(views.html.Project.editProject.render());
    }

    public Result get(){
        return ok(views.html.Project.project.render());
    }

}

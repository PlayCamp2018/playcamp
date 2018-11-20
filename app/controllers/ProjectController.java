package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import repositories.DBTest.ProjectRepository;

import javax.inject.Inject;

public class ProjectController extends Controller {

    @Inject
    private ProjectRepository projects;

     public Result showProjects() {
        projects.insertRandomProject();
        return ok(views.html.Project.projects.render(projects.getAllProjects()));
    }

}


package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import repositories.DBTest.ProjectRepository;
import repositories.UserRepository;

import javax.inject.Inject;

public class DBTestController extends Controller {

    @Inject
    private UserRepository users;

    public Result listUser() {
        users.insertRandomUser(new org.bson.types.ObjectId());
        return ok(views.html.DBTest.listUsers.render(users.getAllUsers()));
    }
    @Inject
    private ProjectRepository projects;

    public Result listProjects() {
        projects.insertRandomProject();
        return ok(views.html.DBTest.listProjects.render(projects.getAllProjects()));
    }

}
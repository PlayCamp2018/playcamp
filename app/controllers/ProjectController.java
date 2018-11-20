package controllers;

import models.Database.Project;
import models.Database.User;
import org.bson.types.ObjectId;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class ProjectController extends Controller {

    public Result edit() {

        Project pro = Project.TestProjects.projects.get(0);


        User proOwner = Project.TestProjects.findUserById(pro.getOwnerid());

        List<User> proUsers = new ArrayList<>();
        for (ObjectId id : pro.getUserList()) { proUsers.add(Project.TestProjects.findUserById(id)); }

        return ok(views.html.Project.editProject.render(pro, proOwner, proUsers));
    }

}

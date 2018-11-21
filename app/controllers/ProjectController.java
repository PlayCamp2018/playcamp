package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Database.Project;
import models.Database.User;
import org.bson.types.ObjectId;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.ArrayList;
import java.util.List;

public class ProjectController extends Controller {

    public Result index() {

        Project pro = Project.TestProjects.projects.get(0);


        User proOwner = Project.TestProjects.findUserById(pro.getOwnerid());

        List<User> proUsers = new ArrayList<>();
        for (ObjectId id : pro.getUserList()) { proUsers.add(Project.TestProjects.findUserById(id)); }

        return ok(views.html.Project.editProject.render(pro, proOwner, proUsers));
    }

    public Result edit() {
        // TODO: make change, send index back or so
        JsonNode json = request().body().asJson();

        String projectName = json.get("editProject_name").textValue();
        String projectDes = json.get("editProject_description").textValue();

        Project pro = Project.TestProjects.projects.get(0);
        pro.setName(projectName);
        pro.setBeschreibung(projectDes);

        // here

        //
        return index();
    }

}

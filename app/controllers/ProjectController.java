package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Database.Project;
import models.Database.User;
import org.bson.types.ObjectId;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.ProjectRepository;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProjectController extends Controller {

    @Inject
    UserRepository users;

    @Inject
    ProjectRepository projects;

    public Result create() {
        String sessEmail = session(Application.SESSION_KEY);
        if (sessEmail == null) { return ok("Please login first."); }

        User user = users.findByEmail(sessEmail);

        return ok(views.html.Project.newProject.render(user));
    }



    public Result edit() {
        String sessEmail = session(Application.SESSION_KEY);
        if (sessEmail == null) { return ok("Please login first."); }

        //TODO: NULLPOINTERS
        return ok(views.html.Project.editProject.render(null, null, null));
    }

    public Result get(){
        return ok(views.html.Project.project.render());
    }

    public Result save() {
        String sessEmail = session(Application.SESSION_KEY);
        if (sessEmail == null) { return ok("Please login first."); }

        //TODO: returned Results, only ok() gets correct displayed
        if (request() == null) {
            return ok("Request is null");
        }
        if (request().body() == null) {
            return ok("Request body is null");
        }

        JsonNode json = request().body().asJson();

        if (json == null) {
            return ok("JSON is null");
        }

        String projectName = json.get("projectName").asText();
        String projectDescription = json.get("projectDescription").asText();
        String ownerEmail = json.get("ownerEmail").asText();
        List<String> userEmailList = new ArrayList<>();

        if (json.get("userList").isArray()) {
            for (JsonNode email : json.get("userList")) {
                userEmailList.add(email.asText());
            }
        }

        ObjectId ownerId = users.findByEmail(ownerEmail).getId();

        List<ObjectId> userIdList = new ArrayList<>();

        for (String email : userEmailList) {
            userIdList.add(users.findByEmail(email).getId());
        }

        Project pro = new Project();
        pro.setName(projectName);
        pro.setBeschreibung(projectDescription);
        pro.setOwnerid(ownerId);
        pro.setUserList(userIdList);
        pro.setMessageBoardID(new ObjectId());

        boolean bRes = projects.insert(pro);

        if (!bRes) { return ok("NOT SAVED"); }

        return ok("Saved");
    }

}

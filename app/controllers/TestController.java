package controllers;

import models.Database.Meeting;
import models.Database.MessagePost;
import models.Database.Project;
import models.Database.User;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.MeetingRepository;
import repositories.MessagePostRepository;
import repositories.ProjectRepository;
import repositories.UserRepository;
import utils.Utils;

import javax.inject.Inject;
import java.util.List;

/**
 * only some test controller to do some stuff
 * like adding new test users to db
 */
public class TestController extends Controller {

    @Inject
    UserRepository users;

    @Inject
    ProjectRepository projects;

    @Inject
    MessagePostRepository messagePosts;

    @Inject
    MeetingRepository meetings;

    public Result index() {

        // --------------USER TEST-------------------
        User user;
        List<User> userList;

        users.insertRandomUser();
        userList = users.getAllUsers();
        user = users.findByEmail(userList.get(0).getEmail());
        users.removeById(user.getId());

        users.insertRandomUser();
        userList = users.getAllUsers();
        user = users.findById(userList.get(0).getId());
        users.removeByEmail(user.getEmail());

        users.insertRandomUser();
        userList = users.getAllUsers();
        user = users.findByEmail(userList.get(0).getEmail());
        user.setFirstname("Martina");
        users.save(user);
        users.removeById(user.getId());

        user = Utils.getRandomUser();
        users.insert(user);
        users.removeById(user.getId());

        System.out.println(user);

        // --------------PROJECT TEST-------------------
        Project project;
        List<Project> projectList;

        projects.insertRandomProject();
        projectList = projects.getAllProjects();
        project = projects.findById(projectList.get(0).getId());
        System.out.println(project);
        projects.removeById(project.getId());

        projects.insertRandomProject();
        projectList = projects.getAllProjects();
        project = projects.findById(projectList.get(0).getId());
        List<Project> pL = projects.findByOwnerId(project.getOwnerid());

        for (Project pI : pL) {
            System.out.println("ByOwner: " + pI);
        }

        projects.removeById(project.getId());

        projects.insertRandomProject();
        projectList = projects.getAllProjects();
        project = projects.findById(projectList.get(0).getId());
        project.setName("New name!");
        projects.save(project);
        project = projects.findById(projectList.get(0).getId());
        System.out.println(project);
        projects.removeById(project.getId());

        // --------------PROJECT TEST-------------------
        MessagePost mp;
        List<MessagePost> mpList;

        messagePosts.insertRandomProject();
        mpList = messagePosts.getAllMessagePosts();
        mp = messagePosts.findById(mpList.get(0).getId());
        System.out.println(mp.getMessage());
        messagePosts.removeById(mp.getId());

        messagePosts.insertRandomProject();
        mpList = messagePosts.getAllMessagePosts();
        mp = messagePosts.findById(mpList.get(0).getId());
        mpList = messagePosts.findByAuthorId(mp.getAuthorID());
        mpList = messagePosts.findByParentID(mp.getParentID());
        mpList = messagePosts.findByProjectId(mp.getProjectID());
        messagePosts.removeById(mp.getId());

        messagePosts.insertRandomProject();
        mpList = messagePosts.getAllMessagePosts();
        mp = messagePosts.findById(mpList.get(0).getId());
        mp.setMessage("TEST TEST TEST BLA BLA");
        messagePosts.save(mp);
        mp = messagePosts.findById(mp.getId());
        System.out.println(mp.getMessage());
        messagePosts.removeById(mp.getId());

        // --------------MEETING TEST-------------------
        Meeting m;
        List<Meeting> mList;

        meetings.insertRandomMeeting();
        mList = meetings.getAllMeetings();
        m = meetings.findById(mList.get(0).getId());
        System.out.println(m.getName());
        meetings.removeById(m.getId());

        meetings.insertRandomMeeting();
        mList = meetings.getAllMeetings();
        m = meetings.findById(mList.get(0).getId());
        mList = meetings.findByAuthorId(m.getAuthorId());
        mList = meetings.findByProjectId(m.getProjectId());
        meetings.removeById(m.getId());

        meetings.insertRandomMeeting();
        mList = meetings.getAllMeetings();
        m = meetings.findById(mList.get(0).getId());
        m.setName("DIES IST EIN TEST und SO");
        meetings.save(m);
        m = meetings.findById(m.getId());
        System.out.println(m.getName());
        meetings.removeById(m.getId());

        // --------------END TEST-------------------
        return ok("Test");
    }
}

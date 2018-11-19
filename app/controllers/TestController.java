package controllers;

import models.Database.*;
import org.bson.types.ObjectId;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.*;
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

    @Inject
    FilesRepository files;

    @Inject
    ChatMessageOrganizerRepository chatMessageOrganizers;

    @Inject
    ChatMessageRepository chatMessages;

    @Inject
    ChatRepository chats;

    public Result index() {

        ObjectId id = new ObjectId();

        // --------------USER TEST-------------------
        User user;
        List<User> userList;

        users.insertRandomUser(id);
        userList = users.getAllUsers();
        user = users.findById(id);
        user = users.findByEmail(user.getEmail());
        user.setFirstname("Martina");
        users.save(user);
        users.removeById(user.getId());

        user = Utils.getRandomUser(id);
        users.insert(user);
        users.removeById(user.getId());

        System.out.println(user);

        // --------------PROJECT TEST-------------------
        Project project;
        List<Project> projectList;

        projects.insertRandomProject(id);
        projectList = projects.getAllProjects();
        project = projects.findById(id);
        System.out.println(project);
        projectList = projects.findByOwnerId(project.getOwnerid());

        for (Project pI : projectList) {
            System.out.println("ByOwner: " + pI);
        }

        project.setName("New name!");
        projects.save(project);
        project = projects.findById(id);
        System.out.println(project);
        projects.removeById(project.getId());

        // --------------PROJECT TEST-------------------
        MessagePost mp;
        List<MessagePost> mpList;

        messagePosts.insertRandomProject(id);
        mpList = messagePosts.getAllMessagePosts();
        mp = messagePosts.findById(id);
        System.out.println(mp.getMessage());

        mp = messagePosts.findById(id);
        mpList = messagePosts.findByAuthorId(mp.getAuthorID());
        mpList = messagePosts.findByParentID(mp.getParentID());
        mpList = messagePosts.findByProjectId(mp.getProjectID());

        mp = messagePosts.findById(id);
        mp.setMessage("TEST TEST TEST BLA BLA");
        messagePosts.save(mp);
        mp = messagePosts.findById(mp.getId());
        System.out.println(mp.getMessage());
        messagePosts.removeById(mp.getId());

        // --------------MEETING TEST-------------------
        Meeting m;
        List<Meeting> mList;

        meetings.insertRandomMeeting(id);
        mList = meetings.getAllMeetings();
        m = meetings.findById(id);
        System.out.println(m.getName());

        m = meetings.findById(id);
        mList = meetings.findByAuthorId(m.getAuthorId());
        mList = meetings.findByProjectId(m.getProjectId());

        m = meetings.findById(id);
        m.setName("DIES IST EIN TEST und SO");
        meetings.save(m);
        m = meetings.findById(m.getId());
        System.out.println(m.getName());
        meetings.removeById(m.getId());

        // --------------END TEST-------------------

        // --------------FILES TEST-------------------
        Files f;
        List<Files> fList;

        ObjectId id2 = new ObjectId();

        files.insertRandomFiles(id);
        files.insertRandomFiles(id2);
        fList = files.getAllFiles();
        f = files.findById(id);
        System.out.println(f.getFilename());

        ObjectId authorId = new ObjectId();
        for (Files fi : fList) {
            fi.setAuthorId(authorId);
            files.save(fi);
        }
        fList = files.findByAuthorId(authorId);

        System.out.println("FILES BY AUTHOR COUNT: " + fList.size());

        String filename = "NEW_TEST_NAME";
        for (Files fi : fList) {
            fi.setFilename(filename);
            files.save(fi);
        }
        fList = files.findByFilename(filename);
        System.out.println("FILES BY FILENAME COUNT: " + fList.size());

        files.removeById(id);
        files.removeById(id2);

        // --------------CHATMESSAGEORGINIZER TEST-------------------
        ChatMessageOrganizer cmo;
        List<ChatMessageOrganizer> cmoList;

        chatMessageOrganizers.insertRandomChatMessageOrganizer(id);
        cmo = chatMessageOrganizers.findById(id);
        cmoList = chatMessageOrganizers.findByChatMessageID(cmo.getChatMessageID());
        cmoList = chatMessageOrganizers.findByUserId(cmo.getUserID());
        cmoList = chatMessageOrganizers.getAllChatMessageOrganizers();
        cmo.setUserID(new ObjectId());
        chatMessageOrganizers.save(cmo);
        chatMessageOrganizers.removeById(cmo.getId());

        // --------------CHATMESSAGE TEST-------------------
        ChatMessage cm;
        List<ChatMessage> cmList;

        chatMessages.insertRandomChatMessage(id);
        cm = chatMessages.findById(id);
        cmList = chatMessages.findByAuthorId(cm.getAuthorId());
        cmList = chatMessages.findByChatId(cm.getChatId());
        cmList = chatMessages.getAllChatMessages();
        cm.setMessage("TEST MESSAGE UND SO");
        chatMessages.save(cm);
        cm = chatMessages.findById(cm.getId());
        System.out.println(cm.getMessage());
        chatMessages.removeById(cm.getId());

        // --------------CHATMESSAGE TEST-------------------
        Chat c;
        List<Chat> cList;

        chats.insertRandomChat(id);
        c = chats.findById(id);
        cList = chats.getAllChats();
        cList = chats.findByProjectID(c.getProjectID());
        c.setName("CHAT TEST NAME");
        chats.save(c);
        c = chats.findById(c.getId());
        System.out.println(c.getName());
        chats.removeById(c.getId());


        return ok("Test");
    }
}

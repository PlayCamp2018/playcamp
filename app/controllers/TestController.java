package controllers;

import models.Database.User;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * only some test controller to do some stuff
 * like adding new test users to db
 */
public class TestController extends Controller {

    @Inject
    UserRepository users;

    public Result index() {
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

        System.out.println(user);
        return ok("Test");
    }
}

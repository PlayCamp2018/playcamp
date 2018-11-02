package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import repositories.DBTest.UsersRepository;

import javax.inject.Inject;

public class DBTestController extends Controller {

    @Inject
    private UsersRepository users;

    public Result listUser() {
        users.insertRandomUser();
        return ok(views.html.DBTest.listUsers.render(users.getAllUsers()));
    }
}
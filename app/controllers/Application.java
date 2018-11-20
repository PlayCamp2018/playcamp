package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {

    public Result register(String mailadd){

        return ok("User Registriert");
    }


    public Result passwordReset(String mailadd){

        return ok("PasswortReset");
    }


}
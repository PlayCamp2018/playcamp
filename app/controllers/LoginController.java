package controllers;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.formdata.LoginFormData;
import play.mvc.Security;

import javax.inject.Inject;


public class LoginController extends Controller{

    @Inject
    Secured sec;

    private final Form<LoginFormData> formData;

    @Inject
    public LoginController(FormFactory formFactory) {
        this.formData = formFactory.form(LoginFormData.class);
    }

    /**
     * Provides the Index page.
     * @return The Index page.
     */
    public Result index() {
        return ok(index.render("Home", sec.isLoggedIn(ctx()), sec.getUserInfo(ctx())));
    }

    /**
     * Provides the Login page (only to unauthenticated users).
     * @return The Login page.
     */
    public Result login() {
        /**final Form<LoginFormData> formD = formData;*/
        return ok(login.render("Login", sec.isLoggedIn(ctx()), sec.getUserInfo(ctx()), formData));
    }



    /**
     * Processes a login form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of LoginFormData.
     * The binding process will invoke the LoginFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public Result postLogin() {

        // Get the submitted form data from the request object, and run validation.
        Form<LoginFormData> boundForm = formData.bindFromRequest();

        if (boundForm.hasErrors()) {
            flash("error", "Login credentials not valid.");
            return badRequest(login.render("Login", sec.isLoggedIn(ctx()), sec.getUserInfo(ctx()), boundForm));
        }
        else {
            // email/password OK, so now we set the session variable and only go to authenticated pages.
            session().clear();
            session("email", boundForm.get().email);
            return redirect(routes.LoginController.index());
        }
    }

    /**
     * Logs out (only for authenticated users) and returns them to the Index page.
     * @return A redirect to the Index page.
     */
    @Security.Authenticated(Secured.class)
    public Result logout() {
        session().clear();
        return redirect(routes.LoginController.index());
    }

    /**
     * Provides the Profile page (only to authenticated users).
     * @return The Profile page.
     */
    /**@Security.Authenticated(Secured.class)
    public static Result profile() {
        return ok(Profile.render("Profile", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx())));
    }
**/
}

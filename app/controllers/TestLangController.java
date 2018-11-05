package controllers;

import play.mvc.*;

import play.i18n.Messages.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * https://www.playframework.com/documentation/2.6.x/JavaI18N
 */
public class TestLangController extends Controller{

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        //Messages messages = Http.Context.current().messages();
        //String langTitle = messages.at("lang.title");
        //ctx().changeLang("de");
        return ok(views.html.lang.render());
    }

}

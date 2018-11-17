package controllers;

import play.mvc.Controller;

import javax.inject.Inject;

import play.api.libs.mailer.MailerClient;
import play.libs.mailer.Email;
import play.mvc.Result;


public class TestMailController extends Controller {
    // Ticket https://studi.f4.htw-berlin.de/redmine/issues/13075
    // Mailer plugin

    @Inject
    MailerClient mailer;

    public Result sendTestMail(String mailTo) {
        String cid = "1234";
        final Email email = new Email()
                .setSubject("Simple email")
                .setFrom("Mister FROM <playcamp2018@gmx.de>")
                .addTo(mailTo)
                .setBodyText("Du möchtest dich registrieren!")
                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        try {
            String id = mailer.send(email);
            return ok("Email " + id + " sent!");
        } catch (Exception e) {
            System.out.println("No Mail send! " + e.toString());
            throw e;
        }
    }

    public Result index() {
        return ok(views.html.Test.mailtest.render());
    }


    public Result submit() {
        String mail = request().getQueryString("email");
        sendTestMail(mail);
        return ok(views.html.Test.mailtest.render());
    }

    public Result dialog() {
        return ok(views.html.Test.dialog.render());
    }

}
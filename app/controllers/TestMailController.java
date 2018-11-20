package controllers;

import play.mvc.Controller;

import javax.inject.Inject;

import play.api.libs.mailer.MailerClient;
import play.libs.mailer.Email;
import play.mvc.Result;
import play.Configuration;
import javax.inject.Inject;
import repositories.*;
import models.Database.User;
import org.bson.types.ObjectId;


public class TestMailController extends Controller {


    private enum DialogType {SUCCESS, INFO, ERROR, WARN};

    @Inject
    private Configuration configuration;

    @Inject
    private UserRepository users;

    @Inject
    private ProjectRepository projects;


    // Ticket https://studi.f4.htw-berlin.de/redmine/issues/13075
    // Mailer plugin

    @Inject
    private MailerClient mailer;

    /**
     * Function to send a mail as text and html.
     *
     * @param mailFrom The mailaddress of the mail-sender.
     * @param mailTo The mailaddress of the mail recipient.
     * @param mailSubject The subject of the mail.
     * @param mailHtmlBody The html content of the mail.
     * @param mailTextBody The text content of the mail.
     * @return Return a text with the mail subject
     * @throws Throws Exception if anything go wrong.
     */
    public Result sendMail(String mailFrom, String mailTo, String mailSubject,
                           String mailHtmlBody, String mailTextBody) {
        final Email email = new Email()
                .setSubject(mailSubject)
                .setFrom(mailFrom)
                .addTo(mailTo)
                .setBodyText(mailTextBody)
                .setBodyHtml(mailHtmlBody);
        try {
            String id = mailer.send(email);
            return ok("Email: '" + mailSubject + "' sent!");
        } catch (Exception e) {
            System.out.println("No Mail send! " + e.toString());
            throw e;
        }
    }

    private String getFromMailaddress(){
        String value = configuration.getString("play.mailer.user");
        return value;
    }

//    public Result register(String mailadd){
//        ObjectId id = ObjectId.get();
//        //TODO:
//        // * User
//        // * reqestURL?
//        // * mailFrom
//
//       // String mailFrom = "mailFrom";
//       // User u = User(mailadd);
//       // users.save(u);
//
//        String t = "Neuen Account anlegen";
//        String rT = t;
//        String rU = "http://localhost:9000";
//
//        String m = "Sie wollen sich mit der Mailadresse " + u.getEmail() + " bei PlayCamp registrieren.\n" +
//                "Schließen Sie jetzt ihre Registrierung ab.\n";
//        String text = m + "\nRegistrierung abschliessen mit folgender Url: " + rU;
//        String html= views.html.Test.mail.render(t,m,rT,rU);
//        thise.sendMail( mailFrom, u.getEmail(), t, html, text);
//    }
//
//
//    public Result passwordReset(String mailadd){
//        //TODO:
//        // * User
//        // * reqestURL?
//        // * mailFrom
//
//        String mailFrom = "mailFrom";
//        User u = User.findByEmail (mailadd);
//
//        if (u == null){
//            Exception e = new Exception("Mailaddresse ist nicht registriert." );
//            throw e;
//        }
//
//
//
//        String t = "Passwort zurücksetzen";
//        String rT = "Neues Passwort vergeben";
//        String rU = "http://localhost:9000";
//
//        String m = "Sie haben ihr Password vergessen und wollen diese mit der Mailaddresse" +
//                u.getEmail() + " zurücksetzten.\n" +
//                "Vergeben Sie ein neues Passswort.\n";
//        String text = m + "\nZum Passwort zurücksetzen die folgende Url aufrufen: " + rU;
//        String html= views.html.Test.mail.render(t,m,rT,rU);
//        thise.sendMail(mailFrom, u.getEmail(), t, html, text);
//    }
//
//
//
//    public Result inviteProject(String mailadd, String projectID){
//        //TODO:
//        // * User
//        // * reqestURL?
//        // * mailFrom
//
//        String mailFrom = "mailFrom";
//        User uTo = User.findByEmail (mailadd);
//        Project prj = projects.findbyID(projectID);
//        User uFrom = User.findByID(prj.getOwnerid());
//
//        if (uTo == null){
//            Exception e = new Exception("Mailaddresse ist nicht registriert." );
//            throw e;
//        }
//
////
////
////        String t = "Passwort zurücksetzen";
////        String rT = "Neues Passwort vergeben";
////        String rU = "http://localhost:9000";
////
////        String m = "Sie haben ihr Password vergessen und wollen diese mit der Mailaddresse" +
////                u.getEmail() + " zurücksetzten.\n" +
////                "Vergeben Sie ein neues Passswort.\n";
////        String text = m + "\nZum Passwort zurücksetzen die folgende Url aufrufen: " + rU;
////        String html= views.html.Test.mail.render(t,m,rT,rU);
////        thise.sendMail(mailFrom, u.getEmail(), t, html, text);
//    }


    public Result dialog() {
        String type = request().getQueryString("dialog");
        DialogType dtype =  DialogType.valueOf(type);
        String title = "Mein Title!";
        String mess = "Meine Message ....";
        String s = getFromMailaddress();
        ObjectId id = ObjectId.get();
        return ok(getDialog(dtype, title, mess));
    }

    private String getDialog(DialogType type, String title, String message) {
        String ret = "";

        switch (type) {
            case SUCCESS:
                ret = "<div class='modal-header alert alert-success' role='alert'>";
                break;
            case WARN:
                ret = "<div class='modal-header alert alert-warning' role='alert'>";
                break;
            case INFO:
                ret = "<div class='modal-header alert alert-info' role='alert'>";
                break;
            case ERROR:
                ret = "<div class='modal-header alert alert-danger' role='alert'>";
                break;
        }

        // FixMe: Path by assets.versioned ...
        ret += "<img src='/assets/images/playcamp_logo_full_50.png' />";

        ret += "<h5 class='pl-sm-3 modal-title' id='exampleModalLongTitle'>" + title + "</h5>";

        ret += "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>";
        ret += "<span aria-hidden='true'>&times;</span></button>";

        ret += "</div><div class='modal-body'>";
        ret += message;

        ret += "</div><div class='modal-footer'>";
        ret += "<button type='button' class='btn btn-secondary' data-dismiss='modal'>Schließen</button></div>";

        return ret;
    }


    // Test Funktionienen


    public Result mail() {
        String t = "Passwortreset";
        String m = "Sie wollen ....";
        String rT = "Passwort resetten";
        String rU = "http://localhost:9000";

        return ok(views.html.Test.mail.render(t,m,rT,rU));
    }

    public Result mailtest() {
        return ok(views.html.Test.mailtest.render());
    }


    public Result submit() {
        String mail = request().getQueryString("email");
        sendTestMail(mail);
        return ok(views.html.Test.mailtest.render());
    }


    public Result dialogtest() {
        return ok(views.html.Test.dialog.render());
    }



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


}
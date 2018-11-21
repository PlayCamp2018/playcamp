package controllers;

import play.mvc.Controller;

import javax.inject.Inject;

import play.api.libs.mailer.MailerClient;
import play.libs.mailer.Email;
import play.mvc.Result;
import play.twirl.api.Html;
import play.Configuration;
import javax.inject.Inject;
import repositories.*;
import models.Database.User;
import org.bson.types.ObjectId;
import utils.Dialog;


public class TestMailController extends Controller {


    @Inject
    private Configuration configuration;

    @Inject
    private UserRepository users;

    @Inject
    private ProjectRepository projects;


    // Ticket https://studi.f4.htw-berlin.de/redmine/issues/13075

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

    public Result register() {
        String mail = request().getQueryString("mail");
        //Todo:
        // * evt. diese func in den AppControler
        // * ist Acc schon vorhanden
        // ** im Reg_status oder nicht
        // * neuen Schatten-Acc anlegen

        String rU = "http://localhost:9000";

        return registerMail(mail, rU);
    }

    /**
     * This function generate and send the Mail to open the user-registration.
     * @param mail The Mailaddress of new user.
     * @param requestUrl The request url in the email.
     * @return Returns the HTML-Code vor the modal-bootstrap-dialog.
     * @see utils.Dialog#getDialog(Dialog.Type, String, String)
     */
    private Result registerMail(String mail, String requestUrl){

        String t = "Account registrieren";
        String rT = "Registrierung abschließen";

        String m = "Sie wollen sich mit der Mailadresse " + mail + " bei PlayCamp registrieren.\n" +
                "Schließen Sie jetzt ihre Registrierung ab.\n";
        String text = m + "\nRegistrierung abschliessen mit folgender Url: " + requestUrl;
        play.twirl.api.Html html = views.html.Test.mail.render(t,m,rT,requestUrl);

        Dialog.Type dtype;
        String title = "";
        String mess = "";

        try{
            this.sendMail( this.getFromMailaddress(), mail, t, html.toString(), text);
            dtype =  Dialog.Type.SUCCESS;
            title = "Registrierung verschickt";
            mess = "Sie haben eine Registrierung mit der Mailadresse '" + mail + "' angefordet.\n" +
                    "Bitte prüfen Sie ihr Postfach, um die Registrierung abzuschliessen.";
        } catch (Exception e) {
            dtype =  Dialog.Type.ERROR;
            title = "Fehler beim Mailversand";
            mess = "Beim verschicken der Mail ist ein Fehler aufgetreten.\n" +
            "Fehler: " + e;
        }
        return ok(Dialog.getDialog(dtype, title, mess));
    }


    public Result passwordReset() {
        String mail = request().getQueryString("mail");
        //Todo:
        // * evt. diese func in den AppControler
        // * ist Acc vorhanden ?
        // ** im Reg_status oder nicht
        // * setzen reqest id
        // * reqestURL?

//        String mailFrom = "mailFrom";
//        User u = User.findByEmail (mailadd);
//
//        if (u == null){
//            Exception e = new Exception("Mailaddresse ist nicht registriert." );
//            throw e;
//        }

        String rU = "http://localhost:9000";

        return passwordResetMail(mail, rU);
    }

    public Result passwordResetMail(String mail, String requestUrl){

        String t = "Passwort zurücksetzen";
        String rT = "Neues Passwort vergeben";

        String m = "Sie haben ihr Password vergessen und wollen diese mit der Mailaddresse '" +
                mail + "' zurücksetzten.\n" +
                "Vergeben Sie ein neues Passswort.\n";
        String text = m + "\nZum Passwort zurücksetzen die folgende Url aufrufen: " + requestUrl;
        play.twirl.api.Html html = views.html.Test.mail.render(t,m,rT,requestUrl);

        Dialog.Type dtype;
        String title = "";
        String mess = "";

        try{
            this.sendMail( this.getFromMailaddress(), mail, t, html.toString(), text);
            dtype =  Dialog.Type.SUCCESS;
            title = "Passwortreset";
            mess = "Sie haben einen Passwortreset mit der Mailadresse '" + mail + "' angefordet.\n" +
                    "Bitte prüfen Sie ihr Postfach, um die Passwortänderung durchzuführen.";
        } catch (Exception e) {
            dtype =  Dialog.Type.ERROR;
            title = "Fehler beim Mailversand";
            mess = "Beim verschicken der Mail ist ein Fehler aufgetreten.\n" +
                    "Fehler: " + e;
        }
        return ok(Dialog.getDialog(dtype, title, mess));
    }
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
        Dialog.Type dtype =  Dialog.Type.valueOf(type);
        String title = "Mein Title!";
        String mess = "Meine Message ....";
        String s = getFromMailaddress();
        ObjectId id = ObjectId.get();
        return ok(Dialog.getDialog(dtype, title, mess));
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
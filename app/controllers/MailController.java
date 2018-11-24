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
import utils.Utils;


public class MailController extends Controller {

    @Inject
    private MailerClient mailer;

    @Inject
    private Configuration configuration;

    private String getFromMailaddress(){
        String value = configuration.getString("play.mailer.user");
        return value;
    }


    /**
     * Function to send a mail as text and html.
     *
     * @param mailFrom The mailaddress of the mail-sender.
     * @param mailTo The mailaddress of the mail recipient.
     * @param mailSubject The subject of the mail.
     * @param mailHtmlBody The html content of the mail.
     * @param mailTextBody The text con tent of the mail.
     * @return Return a text with the mail subject
     * @throws Throws Exception if anything go wrong.
     */
    private Result sendMail(String mailTo, String mailSubject,
                           String mailHtmlBody, String mailTextBody) throws Exception {
        final Email email = new Email()
                .setSubject(mailSubject)
                .setFrom(this.getFromMailaddress())
                .addTo(mailTo)
                .setBodyText(mailTextBody)
                .setBodyHtml(mailHtmlBody);
        try {
            String id = mailer.send(email);
            return ok("Email: '" + mailSubject + "' sent!");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Helper funktion to generate the Error message from
     * mail errror.
     *
     * @param err Exception to generate the Error message
     * @return Retruns the mail-err-description as play.mvc.Result ok
     */
    private Result getMailErrorMesssage(Exception err){
        return ok(views.html.Dialog.simple.render(
                utils.Utils.DialogType.ERROR,
                "Fehler beim Mailversand",
                "Beim verschicken der Mail ist ein Fehler aufgetreten.\n" +
                        "Fehler: " + err
        ));
    }

    public Result register(String mail){
        return this.registerMail(mail, "http://localhost/asdfasd");
    }

    /**
     * This function generate and send the Mail to open the user-registration.
     * @param mail The Mailaddress of new user.
     * @param requestUrl The request url in the email.
     * @return Returns the HTML-Code vor the modal-bootstrap-dialog.
     */
    private Result registerMail(String mail, String requestUrl){

        String t = "Account registrieren";
        String rT = "Registrierung abschließen";

        String m = "Sie wollen sich mit der Mailadresse " + mail + " bei PlayCamp registrieren.\n" +
                "Schließen Sie jetzt ihre Registrierung ab.\n";
        String text = m + "\nRegistrierung abschliessen mit folgender Url: " + requestUrl;
        play.twirl.api.Html html = views.html.Mail.mail.render(t,m,rT,requestUrl);

        try{
            this.sendMail(mail, t, html.toString(), text);
            return ok(views.html.Dialog.simple.render(
                    utils.Utils.DialogType.SUCCESS,
                    "Registrierung verschickt",
                    "Sie haben eine Registrierung mit der Mailadresse '" + mail + "' angefordet.\n" +
                            "Bitte prüfen Sie ihr Postfach, um die Registrierung abzuschliessen."
            ));
        } catch (Exception e) {
            return getMailErrorMesssage(e);
        }
    }


    public Result passwordReset(String mail){
        return this.passwordResetMail(mail, "http://localhost/asdfasd");
    }

    /**
     * This function generate and send the mail to initate the
     * passwort restet.
     * @param mail The Mailaddress of user.
     * @param requestUrl The request url in the email.
     * @return Returns the HTML-Code vor the modal-bootstrap-dialog.
     */
    public Result passwordResetMail(String mail, String requestUrl){

        String t = "Passwort zurücksetzen";
        String rT = "Neues Passwort vergeben";

        String m = "Sie haben ihr Password vergessen und wollen diese mit der Mailaddresse '" +
                mail + "' zurücksetzten.\n" +
                "Vergeben Sie ein neues Passswort.\n";
        String text = m + "\nZum Passwort zurücksetzen die folgende Url aufrufen: " + requestUrl;
        play.twirl.api.Html html = views.html.Mail.mail.render(t,m,rT,requestUrl);


        try{
            this.sendMail(mail, t, html.toString(), text);
            return ok(views.html.Dialog.simple.render(
                    utils.Utils.DialogType.SUCCESS,
                    "Passwortreset",
                    "Sie haben einen Passwortreset mit der Mailadresse '" + mail + "' angefordet.\n" +
                            "Bitte prüfen Sie ihr Postfach, um die Passwortänderung durchzuführen."
            ));
        } catch (Exception e) {
            return getMailErrorMesssage(e);
        }
    }


    //ToDo: Remove Testfunktionen

    public Result mailtest() {
        return ok(views.html.Test.mailtest.render());
    }

    public Result dialog() {
        String type = request().getQueryString("dialog");
        utils.Utils.DialogType dtype =  utils.Utils.DialogType.valueOf(type);
        String title = "Mein Title!";
        String mess = "Meine Message ....";
        String s = getFromMailaddress();
        ObjectId id = ObjectId.get();
        return ok(views.html.Dialog.simple.render(
                dtype, title, mess
        ));
        //return ok(Dialog.getDialog(dtype, title, mess));
    }

      public Result dialogtest() {
            return ok(views.html.Test.dialog.render());
        }


}
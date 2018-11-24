package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;
import play.mvc.Http.RequestBuilder;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
//import mstatic play.test.Helpers.route;

public class MailControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

//    @Test
//    public void testIndex() {
//        RequestBuilder request = Helpers.fakeRequest()
//                .method(GET)
//                .uri("/")
//                .pa
//
//        Result result = route(app, request);
//        assertEquals(OK, result.status());
//    }

    MailController mailer = new MailController();

    @Test
    public void testRegister() {
        Result result = mailer.register( "test@testdomain.xyz", "http://localhost:9090");
        assertEquals(OK, result.status());
    }

    @Test
    public void testPasswortRest() {
        Result result = mailer.passwordReset( "test@testdomain.xyz", "http://localhost:9090");
        assertEquals(OK, result.status());

    }
}

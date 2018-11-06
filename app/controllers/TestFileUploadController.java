package controllers;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestFileUploadController extends Controller {
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.TestFileUpload.uploadFile.render());
    }

    @BodyParser.Of(BodyParser.MultipartFormData.class)
    public Result upload() throws IOException {

        System.out.println("-----------");

        final Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        final Http.MultipartFormData.FilePart<File> filePart = formData.getFile("profile_picture");

        System.out.println(filePart.getFilename());

        File file = null;
        ByteArrayInputStream input = null;

        if (filePart != null) {
            file = filePart.getFile();
            System.out.println(file.getName());
            System.out.println(file.getPath());
            System.out.println(file.getTotalSpace());

            byte[] byteArray = new FileInputStream(file).readAllBytes();
            input = new ByteArrayInputStream(byteArray);
        }

        System.out.println("-----------");

        // return ok(views.html.TestFileUpload.uploadFile.render());
        // return ok(file).as("image/svg");
        return ok(file).as("image/svg");
    }
}

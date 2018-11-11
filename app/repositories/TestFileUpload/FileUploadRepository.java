package repositories.TestFileUpload;

import com.mongodb.WriteResult;
import models.DBTest.User;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;

import javax.inject.Inject;
import java.nio.charset.Charset;
import java.util.Random;

public class FileUploadRepository {

    @Inject
    public PlayJongo jongo;

    public MongoCollection users() {
        return jongo.getCollection("DB.users");
    }

    public User[] getAllUsers() {
        MongoCursor<User> lUsers = users().find().as(User.class);

        User aUsers[] = new User[lUsers.count()];
        int i = 0;

        while(lUsers.hasNext()) {
            aUsers[i] = lUsers.next();
            i++;
        }

        return aUsers;
    }

    public boolean insertRandomUser() {
        User u = new User();

        byte[] array = new byte[8];
        new Random().nextBytes(array);
        u.firstname = new String(array, Charset.forName("UTF-8"));

        new Random().nextBytes(array);
        u.lastname = new String(array, Charset.forName("UTF-8"));

        u.email = u.firstname + "." + u.lastname + "@random.com";
        WriteResult wRes =  users().insert(u);

        return wRes.wasAcknowledged();
    }

    public User findByEmail(String email) {
        return users().findOne("{email: #}", email).as(User.class);
    }

}

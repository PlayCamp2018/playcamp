package repositories.DBTest;

import com.mongodb.WriteResult;
import models.DBTest.Users;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;

import javax.inject.Inject;
import java.nio.charset.Charset;
import java.util.Random;


public class UsersRepository {

    @Inject
    public PlayJongo jongo;

    public MongoCollection users() {
        return jongo.getCollection("DB.users");
    }

    public Users[] getAllUsers() {
        MongoCursor<Users> lUsers = users().find().as(Users.class);

        Users aUsers[] = new Users[lUsers.count()];
        int i = 0;

        while(lUsers.hasNext()) {
            aUsers[i] = lUsers.next();
            i++;
        }

        return aUsers;
    }

    public boolean insertRandomUser() {
        Users u = new Users();

        byte[] array = new byte[8];
        new Random().nextBytes(array);
        u.firstname = new String(array, Charset.forName("UTF-8"));

        new Random().nextBytes(array);
        u.lastname = new String(array, Charset.forName("UTF-8"));

        u.email = u.firstname + "." + u.lastname + "@random.com";
        WriteResult wRes =  users().insert(u);

        return wRes.wasAcknowledged();
    }

    public Users findByEmail(String email) {
        return users().findOne("{email: #}", email).as(Users.class);
    }
}
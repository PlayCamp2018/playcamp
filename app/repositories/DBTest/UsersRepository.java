package repositories.DBTest;

import com.mongodb.WriteResult;
import models.Database.User;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;

import javax.inject.Inject;


public class UsersRepository {

    @Inject
    public PlayJongo jongo;

    public MongoCollection users() {
        return jongo.getCollection("DB.users");
    }

    public User[] getAllUsers() {
        MongoCursor<User> lUsers = users().find().as(User.class);

        User aUsers[] = new User[lUsers.count()];
        int i = 0;

        while (lUsers.hasNext()) {
            aUsers[i] = lUsers.next();
            i++;
        }

        return aUsers;
    }

    public boolean insertRandomUser() {
        User u = new User();

        u.setFirstname("Max");

        u.setLastname("Musterman");

        u.setEmail( u.getFirstname() + "." + u.getLastname() + "@random.com");

        u.setPassword("1234");

        //u.setid = new org.bson.types.ObjectId();

        u.setProfilePicID(new org.bson.types.ObjectId());

        WriteResult wRes =  users().insert(u);
        return wRes.wasAcknowledged();
    }

    public User findByEmail(String email) {
        return users().findOne("{email: #}", email).as(User.class);
    }
}
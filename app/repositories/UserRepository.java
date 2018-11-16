package repositories;

import com.mongodb.WriteResult;
import models.Database.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;

public class UserRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection users() {
        return jongo.getCollection("DB.users");
    }

    public List<User> getAllUsers() {
        MongoCursor<User> cursor = users().find().as(User.class);
        List<User> users = new ArrayList<>();

        while (cursor.hasNext()) {
            users.add(cursor.next());
        }

        return users;
    }

    public User findById(ObjectId id) { return users().findOne(id).as(User.class); }
    public User findByEmail(String email) {
        return users().findOne("{email: #}", email).as(User.class);
    }

    public boolean save(User user) {
        WriteResult wRes = users().save(user);
        return wRes.wasAcknowledged();
    }


    public boolean insert(User user) {
        WriteResult wRes = users().insert(user);
        return wRes.wasAcknowledged();
    }


    public boolean removeById(ObjectId id) {
        WriteResult wRes = users().remove(id);
        return wRes.wasAcknowledged();
    }
    public boolean removeByEmail(String email) {
        WriteResult wRes = users().remove("{email: #}", email);
        return wRes.wasAcknowledged();
    }


    public boolean insertRandomUser() {
        User u = new User(
//                random(6, true, false),
//                random(10, true, false),
//                "1234",
//                "max.muster@email.com",
//                new ObjectId()
        );
        u.setFirstname(random(6, true, false));
        u.setLastname(random(10, true, false));
        u.setEmail(u.getFirstname()+"."+u.getLastname()+"@email.com");
        u.setPassword("1234");
        u.setProfilePicID(new ObjectId());

        WriteResult wRes =  users().insert(u);
        return wRes.wasAcknowledged();
    }
}

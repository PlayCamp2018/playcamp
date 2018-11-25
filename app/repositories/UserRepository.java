package repositories;

import com.mongodb.WriteResult;
import models.Database.User;
import org.bson.types.ObjectId;
import org.jongo.Find;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection users() {
        return jongo.getCollection("DB.users");
    }

    /**
     * Returns a List<User> with all users
     * @return the user list, empty if none
     */
    public List<User> getAllUsers() {
        MongoCursor<User> cursor = users().find().as(User.class);
        List<User> users = new ArrayList<>();

        while (cursor.hasNext()) {
            users.add(cursor.next());
        }

        return users;
    }


    /**
     * Finds user with given id
     * @param id given user id
     * @return the found User or null
     */
    public User findById(ObjectId id) { return users().findOne(id).as(User.class); }
    /**
     * Finds user with given email
     * @param email given user email
     * @return the found User or null
     */
    public User findByEmail(String email) { return users().findOne("{email: #}", email).as(User.class); }


    /**
     * Updates/saves the given user
     * @param user the user to update
     * @return true if success else false
     */
    public boolean save(User user) {
        WriteResult wRes = users().save(user);
        return wRes.wasAcknowledged();
    }


    /**
     * Adds a new user
     * @param user the user to add
     * @return true if success else false
     */
    public boolean insert(User user) {
        WriteResult wRes = users().insert(user);
        return wRes.wasAcknowledged();
    }


    /**
     * Removes the user with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = users().remove(id);
        return wRes.wasAcknowledged();
    }
    /**
     * Removes the user with the given email
     * @param email the given email
     * @return true if success else false
     */
    public boolean removeByEmail(String email) {
        WriteResult wRes = users().remove("{email: #}", email);
        return wRes.wasAcknowledged();
    }


    /**
     * Inserts a random user
     * @return true if success else false
     */
    public boolean insertRandomUser(ObjectId id) {
        User u = Utils.getRandomUser(id);
        return insert(u);
    }

    public Boolean isValid(String email, String password) {
        return findByEmail(email).getPassword().equals(password);
    }


}

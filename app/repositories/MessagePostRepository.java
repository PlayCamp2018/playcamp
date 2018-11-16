package repositories;

import com.mongodb.WriteResult;
import models.Database.MessagePost;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MessagePostRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection messagePosts() {
        return jongo.getCollection("DB.messagePosts");
    }

    /**
     * Returns a List<MessagePost> with all message posts
     * @return the message post list, empty if none
     */
    public List<MessagePost> getAllMessagePosts() {
        MongoCursor<MessagePost> cursor = messagePosts().find().as(MessagePost.class);
        List<MessagePost> messagePosts = new ArrayList<>();

        while (cursor.hasNext()) { messagePosts.add(cursor.next()); }

        return messagePosts;
    }

    /**
     * Finds MessagePost with given id
     * @param id given MessagePost id
     * @return the found MessagePost or null
     */
    public MessagePost findById(ObjectId id) { return messagePosts().findOne(id).as(MessagePost.class); }
    /**
     * Finds MessagePost by given projectID
     * @param projectID given projectID
     * @return the found MessagePost list or empty if none
     */
    public List<MessagePost> findByProjectId(ObjectId projectID) {
        MongoCursor<MessagePost> cursor = messagePosts().find("{projectID: #}", projectID).as(MessagePost.class);
        List<MessagePost> messagePosts = new ArrayList<>();

        while (cursor.hasNext()) { messagePosts.add(cursor.next()); }

        return messagePosts;
    }
    /**
     * Finds MessagePost by given authorID
     * @param authorID given authorID
     * @return the found MessagePost list or empty if none
     */
    public List<MessagePost> findByAuthorId(ObjectId authorID) {
        MongoCursor<MessagePost> cursor = messagePosts().find("{authorID: #}", authorID).as(MessagePost.class);
        List<MessagePost> messagePosts = new ArrayList<>();

        while (cursor.hasNext()) { messagePosts.add(cursor.next()); }

        return messagePosts;
    }
    /**
     * Finds MessagePost by given parentID
     * @param parentID given parentID
     * @return the found MessagePost list or empty if none
     */
    public List<MessagePost> findByParentID(ObjectId parentID) {
        MongoCursor<MessagePost> cursor = messagePosts().find("{parentID: #}", parentID).as(MessagePost.class);
        List<MessagePost> messagePosts = new ArrayList<>();

        while (cursor.hasNext()) { messagePosts.add(cursor.next()); }

        return messagePosts;
    }


    /**
     * Updates/saves the given MessagePost
     * @param mp the MessagePost to update
     * @return true if success else false
     */
    public boolean save(MessagePost mp) {
        WriteResult wRes = messagePosts().save(mp);
        return wRes.wasAcknowledged();
    }


    /**
     * Adds a new MessagePost
     * @param mp the MessagePost to add
     * @return true if success else false
     */
    public boolean insert(MessagePost mp) {
        WriteResult wRes = messagePosts().insert(mp);
        return wRes.wasAcknowledged();
    }


    /**
     * Removes the MessagePost with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = messagePosts().remove(id);
        return wRes.wasAcknowledged();
    }


    /**
     * Inserts a random user
     * @return true if success else false
     */
    public boolean insertRandomProject(ObjectId id) {
        MessagePost mp = Utils.getRandomMessagePost(id);
        return insert(mp);
    }
}

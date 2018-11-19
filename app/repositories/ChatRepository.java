package repositories;

import com.mongodb.WriteResult;
import models.Database.Chat;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection chats() {
        return jongo.getCollection("DB.chats");
    }

    /**
     * Returns a List<Chat> with all Chats
     * @return the Chat list, empty if none
     */
    public List<Chat> getAllChats() {
        MongoCursor<Chat> cursor = chats().find().as(Chat.class);
        List<Chat> chats = new ArrayList<>();

        while(cursor.hasNext()) { chats.add(cursor.next()); }

        return chats;
    }

    /**
     * Returns a List<Chat> with all Chat
     * @return the Chat list, empty if none
     */
    public Chat findById(ObjectId id) { return chats().findOne(id).as(Chat.class); }
    /**
     * Returns a List<Chat> with all ChatMessageOrganizer for given projectID
     * @param projectID given projectID
     * @return the Chat list, empty if none
     */
    public List<Chat> findByProjectID(ObjectId projectID) {
        MongoCursor<Chat> cursor = chats().find("{projectID: #}", projectID).as(Chat.class);
        List<Chat> chats = new ArrayList<>();

        while(cursor.hasNext()) { chats.add(cursor.next()); }

        return chats;
    }

    /**
     * Adds a new Chat
     * @param c the Chat to add
     * @return true if success else false
     */
    public boolean insert(Chat c) {
        WriteResult wRes = chats().insert(c);
        return wRes.wasAcknowledged();
    }

    /**
     * Updates/saves the given Chat
     * @param c the Chat to update
     * @return true if success else false
     */
    public boolean save(Chat c) {
        WriteResult wRes = chats().save(c);
        return wRes.wasAcknowledged();
    }

    /**
     * Removes the Chat with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = chats().remove(id);
        return wRes.wasAcknowledged();
    }

    /**
     * Inserts a random Chat
     * @return true if success else false
     */
    public boolean insertRandomChat(ObjectId id) {
        Chat c = Utils.getRandomChat(id);
        return insert(c);
    }
}

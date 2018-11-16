package repositories;

import com.mongodb.WriteResult;
import models.Database.ChatMessageOrganizer;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageOrganizerRepository {

    @Inject
    public PlayJongo jongo;

    public MongoCollection chatMessageOrganizers() {
        return jongo.getCollection("DB.chatMessageOrganizers");
    }

    /**
     * Returns a List<ChatMessageOrganizer> with all ChatMessageOrganizers
     * @return the ChatMessageOrganizer list, empty if none
     */
    public List<ChatMessageOrganizer> getAllChatMessageOrganizers() {
        MongoCursor<ChatMessageOrganizer> cursor = chatMessageOrganizers().find().as(ChatMessageOrganizer.class);
        List<ChatMessageOrganizer> chatMessageOrganizers = new ArrayList<>();

        while(cursor.hasNext()) { chatMessageOrganizers.add(cursor.next()); }

        return chatMessageOrganizers;
    }

    /**
     * Finds ChatMessageOrganizer with given id
     * @param id given ChatMessageOrganizer id
     * @return the found ChatMessageOrganizer or null
     */
    public ChatMessageOrganizer findById(ObjectId id) { return chatMessageOrganizers().findOne(id).as(ChatMessageOrganizer.class); }
    /**
     * Returns a List<ChatMessageOrganizer> with all ChatMessageOrganizer for given userID
     * @param userID given userID
     * @return the ChatMessageOrganizer list, empty if none
     */
    public List<ChatMessageOrganizer> findByUserId(ObjectId userID) {
        MongoCursor<ChatMessageOrganizer> cursor = chatMessageOrganizers().find("{userID: #}", userID).as(ChatMessageOrganizer.class);
        List<ChatMessageOrganizer> chatMessageOrganizers = new ArrayList<>();

        while(cursor.hasNext()) { chatMessageOrganizers.add(cursor.next()); }

        return chatMessageOrganizers;
    }
    /**
     * Returns a List<ChatMessageOrganizer> with all ChatMessageOrganizer for given chatMessageID
     * @param chatMessageID given chatMessageID
     * @return the ChatMessageOrganizer list, empty if none
     */
    public List<ChatMessageOrganizer> findByChatMessageID(ObjectId chatMessageID) {
        MongoCursor<ChatMessageOrganizer> cursor = chatMessageOrganizers().find("{chatMessageID: #}", chatMessageID).as(ChatMessageOrganizer.class);
        List<ChatMessageOrganizer> chatMessageOrganizers = new ArrayList<>();

        while(cursor.hasNext()) { chatMessageOrganizers.add(cursor.next()); }

        return chatMessageOrganizers;
    }

    /**
     * Adds a new ChatMessageOrganizer
     * @param cmo the ChatMessageOrganizer to add
     * @return true if success else false
     */
    public boolean insert(ChatMessageOrganizer cmo) {
        WriteResult wRes = chatMessageOrganizers().insert(cmo);
        return wRes.wasAcknowledged();
    }

    /**
     * Updates/saves the given ChatMessageOrganizer
     * @param cmo the ChatMessageOrganizer to update
     * @return true if success else false
     */
    public boolean save(ChatMessageOrganizer cmo) {
        WriteResult wRes = chatMessageOrganizers().save(cmo);
        return wRes.wasAcknowledged();
    }

    /**
     * Removes the ChatMessageOrganizer with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = chatMessageOrganizers().remove(id);
        return wRes.wasAcknowledged();
    }

    /**
     * Inserts a random ChatMessageOrganizer
     * @return true if success else false
     */
    public boolean insertRandomChatMessageOrganizer(ObjectId id) {
        ChatMessageOrganizer cmo = Utils.getRandomChatMessageOrganizer(id);
        return insert(cmo);
    }
}

package repositories;

import com.mongodb.WriteResult;
import models.Database.ChatMessage;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import uk.co.panaxiom.playjongo.PlayJongo;
import utils.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageRepository {
    @Inject
    public PlayJongo jongo;

    public MongoCollection chatMessages() {
        return jongo.getCollection("DB.chatMessages");
    }

    /**
     * Returns a List<ChatMessage> with all ChatMessages
     * @return the ChatMessage list, empty if none
     */
    public List<ChatMessage> getAllChatMessages() {
        MongoCursor<ChatMessage> cursor = chatMessages().find().as(ChatMessage.class);
        List<ChatMessage> chatMessages = new ArrayList<>();

        while(cursor.hasNext()) { chatMessages.add(cursor.next()); }

        return chatMessages;
    }

    /**
     * Returns a List<ChatMessage> with all ChatMessages
     * @return the ChatMessage list, empty if none
     */
    public ChatMessage findById(ObjectId id) { return chatMessages().findOne(id).as(ChatMessage.class); }
    /**
     * Returns a List<ChatMessage> with all ChatMessageOrganizer for given authorId
     * @param authorId given authorId
     * @return the ChatMessage list, empty if none
     */
    public List<ChatMessage> findByAuthorId(ObjectId authorId) {
        MongoCursor<ChatMessage> cursor = chatMessages().find("{authorId: #}", authorId).as(ChatMessage.class);
        List<ChatMessage> chatMessages = new ArrayList<>();

        while(cursor.hasNext()) { chatMessages.add(cursor.next()); }

        return chatMessages;
    }
    /**
     * Returns a List<ChatMessage> with all ChatMessageOrganizer for given chatId
     * @param chatId given chatId
     * @return the ChatMessage list, empty if none
     */
    public List<ChatMessage> findByChatId(ObjectId chatId) {
        MongoCursor<ChatMessage> cursor = chatMessages().find("{chatId: #}", chatId).as(ChatMessage.class);
        List<ChatMessage> chatMessages = new ArrayList<>();

        while(cursor.hasNext()) { chatMessages.add(cursor.next()); }

        return chatMessages;
    }

    /**
     * Adds a new ChatMessage
     * @param cm the ChatMessage to add
     * @return true if success else false
     */
    public boolean insert(ChatMessage cm) {
        WriteResult wRes = chatMessages().insert(cm);
        return wRes.wasAcknowledged();
    }

    /**
     * Updates/saves the given ChatMessage
     * @param cm the ChatMessage to update
     * @return true if success else false
     */
    public boolean save(ChatMessage cm) {
        WriteResult wRes = chatMessages().save(cm);
        return wRes.wasAcknowledged();
    }

    /**
     * Removes the ChatMessage with the given id
     * @param id the given id
     * @return true if success else false
     */
    public boolean removeById(ObjectId id) {
        WriteResult wRes = chatMessages().remove(id);
        return wRes.wasAcknowledged();
    }

    /**
     * Inserts a random ChatMessage
     * @return true if success else false
     */
    public boolean insertRandomChatMessage(ObjectId id) {
        ChatMessage cm = Utils.getRandomChatMessage(id);
        return insert(cm);
    }
}

package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.Date;

/**
 * This class describes the chatmessage and its attributes of playcamp.
 */


public class ChatMessage {

    //Leerer Konstruktor für Tests
    public ChatMessage() {
    }

    /**
     *
     * @param id The id of the chatmessage as org.bson.types.ObjectId.
     * @param createDate The date when the message was created as java.util.Date.
     * @param chatId The id of the chat to which the message belongs.
     * @param message The content of the message as String.
     * @param authorId The id of the author who created the message.
     */

    @JsonCreator
    public ChatMessage(@JsonProperty("_id") ObjectId id,
                       @JsonProperty("createDate") Date createDate,
                       @JsonProperty("chatId") ObjectId chatId,
                       @JsonProperty("message") String message,
                       @JsonProperty("authorId") ObjectId authorId) {

        this.id = id;
        this.createDate = createDate;
        this.chatId = chatId;
        this.message = message;
        this.authorId = authorId;
    }

    /** The id of the chatmessage as org.bson.types.ObjectId. */
    @JsonProperty("_id")
    private ObjectId id;
    /** The date when the message was created as java.util.Date. */
    @JsonProperty("createDate")
    private Date createDate;
    /** The id of the chat to which the message belongs as org.bson.types.ObjectId. */
    @JsonProperty("chatId")
    private ObjectId chatId;
    /** The content of the message as String. */
    @JsonProperty("message")
    private String message;
    /** The id of the author who created the message. */
    @JsonProperty("authorId")
    private ObjectId authorId;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ObjectId getChatId() {
        return chatId;
    }

    public void setChatId(ObjectId chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

}
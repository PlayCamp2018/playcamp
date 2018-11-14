package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * This class describes the chatmessageorganizer and its attributes of playcamp.
 */

public class ChatMessageOrganizer {

    //Leerer Konstruktor für Tests
    public ChatMessageOrganizer() {
    }

    /**
     *
     * @param id The id of the chatmessageorganizer as org.bson.types.ObjectId.
     * @param chatMessageID The id of the chatmessage which has to be organized.
     * @param userID The id of the user who the chatmessageorganizer belongs to.
     */
    @JsonCreator
    public ChatMessageOrganizer(@JsonProperty("id") ObjectId id,
                                @JsonProperty("chatMessageID") ObjectId chatMessageID,
                                @JsonProperty("userID") ObjectId userID) {

        this.id = id;
        this.chatMessageID = chatMessageID;
        this.userID = userID;
    }

    /** The id of the chatmessageorganizer as org.bson.types.ObjectId. */
    @JsonProperty("id")
    public ObjectId id;
    /** The id of the chatmessage which has to be organized. */
    @JsonProperty("chatMessageID")
    public ObjectId chatMessageID;
    /** The id of the user who the chatmessageorganizer belongs to. */
    @JsonProperty("userID")
    public ObjectId userID;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getChatMessageID() {
        return chatMessageID;
    }

    public void setChatMessageID(ObjectId chatMessageID) {
        this.chatMessageID = chatMessageID;
    }

    public ObjectId getUserID() {
        return userID;
    }

    public void setUserID(ObjectId userID) {
        this.userID = userID;
    }
}


package models.DBTest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.Date;


public class ChatMessage {

    public ChatMessage() {
    }

    @JsonCreator
    public ChatMessage(@JsonProperty("id") ObjectId _id,
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

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("createDate")
    public Date createDate;
    @JsonProperty("chatId")
    public ObjectId chatId;
    @JsonProperty("message")
    public String message;
    @JsonProperty("authorId")
    public ObjectId authorId;

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
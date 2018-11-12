package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.io.File;
import java.sql.Blob;


public class Files {

    public Files() {
    }

    @JsonCreator
    public Files(@JsonProperty("id") ObjectId id,
                 @JsonProperty("file") File file,
                 @JsonProperty("blob") Blob blob,
                 @JsonProperty("authorId") ObjectId authorId,
                 @JsonProperty("filename") String filename) {

        this.id = id;
        this.file = file;
        this.blob = blob;
        this.authorId = authorId;
        this.filename = filename;
    }

    @JsonProperty("id")
    public ObjectId id;
    @JsonProperty("file")
    public File file;
    @JsonProperty("blob")
    public Blob blob;
    @JsonProperty("authorId")
    public ObjectId authorId;
    @JsonProperty("filename")
    public String filename;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
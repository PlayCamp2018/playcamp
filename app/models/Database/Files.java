package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.io.File;
import java.sql.Blob;

/**
 * This class describes the files and its attributes of playcamp.
 */

public class Files {

    //Leerer Konstruktor für Tests
    public Files() {
    }

    /**
     *
     * @param id The id of the file as org.bson.types.ObjectId.
     * @param file The actual content of the file as java.io.File.
     * @param blob The blob of the file as java.sql.Blob.
     * @param authorId The id of the user who sent the file as org.bson.types.ObjectId.
     * @param filename The name of the file as String.
     */

    @JsonCreator
    public Files(@JsonProperty("_id") ObjectId id,
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

    /** The id of the file as as org.bson.types.ObjectId. */
    @JsonProperty("_id")
    private ObjectId id;
    /** The actual content of the file as java.io.File. */
    @JsonProperty("file")
    private File file;
    /** The blob of the file as java.sql.Blob. */
    @JsonProperty("blob")
    private Blob blob;
    /** The id of the user who sent the file as org.bson.types.ObjectId. */
    @JsonProperty("authorId")
    private ObjectId authorId;
    /** The name of the file as String. */
    @JsonProperty("filename")
    private String filename;


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
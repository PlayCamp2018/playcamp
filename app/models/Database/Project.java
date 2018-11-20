package models.Database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import java.util.*;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * This class describes the project and its attributes of playcamp.
 */

public class Project {

    //Leerer Konstruktor für Tests
    public Project (){}

    /**
     * The constructor as json-creator used by jongo.
     * @param id The id of the project as org.bson.types.ObjectId.
     * @param name The name of the project as String.
     * @param beschreibung The description of the project as String.
     * @param ownerid The ownerid of the project as org.bson.types.ObjectId.
     * @param userList The list of users in the project with their ids as org.bson.types.ObjectId.
     * @param messageBoardID The id of the messageBoard belonging to the project as org.bson.types.ObjectId.
     */

    @JsonCreator
    public Project(@JsonProperty("_id") ObjectId id,
                   @JsonProperty("name") String name,
                   @JsonProperty("beschreibung") String beschreibung,
                   @JsonProperty("ownerid") ObjectId ownerid,
                   @JsonProperty("userList") List<ObjectId> userList,
                   @JsonProperty("messageBoardID") ObjectId messageBoardID
                   ) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.ownerid = ownerid;
        this.userList = userList;
        this.messageBoardID = messageBoardID;
    }

    /** The id of the project as org.bson.types.ObjectId. */
    @JsonProperty("_id")
    private ObjectId id;
    /** The name of the project as String. */
    @JsonProperty("name")
    private String name;
    /** The description of the project as String. */
    @JsonProperty("beschreibung")
    private String beschreibung;
    /** The ownerid of the project as org.bson.types.ObjectId. */
    @JsonProperty("ownerid")
    private ObjectId ownerid;
    /** The list of users in the project with their ids as org.bson.types.ObjectId.*/
    @JsonProperty("userList")
    private List<ObjectId> userList;
    /** The id of the messageBoard belonging to the project as org.bson.types.ObjectId.*/
    @JsonProperty("messageBoardID")
    private ObjectId messageBoardID;



    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return  name;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public ObjectId getOwnerid() { return ownerid; }
    public List<ObjectId> getUserList() {
        return userList;
    }
    public ObjectId getMessageBoardID() {
        return messageBoardID;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setOwnerid(ObjectId ownerid) {
        this.ownerid = ownerid;
    }

    public void setUserList(List<ObjectId> userList) {
        this.userList = userList;
    }

    public void setMessageBoardID(ObjectId messageBoardID) {
        this.messageBoardID = messageBoardID;
    }

    @Override
    public String toString() { return getName() +": "+ getBeschreibung(); }

    public static class TestProjects {
        public static List<User> pro01DBUsers = new ArrayList<>(asList(
                new User(new ObjectId(), "Max", "Mustermann", "test1234", "max.mustermann@email.com", new ObjectId()),
                new User(new ObjectId(), "Peter", "Flusel", "test1234", "peter.flusel@email.com", new ObjectId()),
                new User(new ObjectId(), "Martina", "Musterfrau", "test1234", "martina.musterfrau@email.com", new ObjectId()),
                new User(new ObjectId(), "Anna", "Bauer", "test1234", "anna.bauer@email.com", new ObjectId()),
                new User(new ObjectId(), "Zeus", "Olympia", "test1234", "zeus.olympia@email.com", new ObjectId()),
                new User(new ObjectId(), "Alexandra", "Da", "test1234", "alexandra.da@email.com", new ObjectId())
        ));

        public static ObjectId proId01 = new ObjectId();

        public static List<ObjectId> pro01Users = new ArrayList<>(asList(
                pro01DBUsers.get(1).getId(),
                pro01DBUsers.get(2).getId(),
                pro01DBUsers.get(3).getId(),
                pro01DBUsers.get(4).getId()
        ));

        public static ObjectId messageBoardId01 = new ObjectId();

        public static List<Project> projects = new ArrayList<>(asList(
                new Project(proId01, "Project01", "Auf der Registerkarte 'Einfügen' enthalten die Kataloge Elemente, die mit dem generellen Layout des Dokuments koordiniert werden sollten. Mithilfe dieser Kataloge können Sie Tabellen, Kopfzeilen, Fußzeilen, Listen, Deckblätter und sonstige Dokumentbausteine einfügen. Wenn Sie Bilder, Tabellen oder Diagramme erstellen, werden diese auch mit dem aktuellen Dokumentlayout koordiniert.\n" +
                        "Die Formatierung von markiertem Text im Dokumenttext kann auf einfache Weise geändert werden, indem Sie im Schnellformatvorlagen-Katalog auf der Registerkarte 'Start' ein Layout für den markierten Text auswählen. Text können Sie auch direkt mithilfe der anderen Steuerelemente auf der Registerkarte 'Start' formatieren. Die meisten Steuerelemente ermöglichen die Auswahl zwischen dem Layout des aktuellen Designs oder der direkten Angabe eines Formats.\n" +
                        "Wählen Sie neue Designelemente auf der Registerkarte 'Seitenlayout' aus, um das generelle Layout des Dokument s zu ändern. Verwenden Sie den Befehl zum Ändern des aktuellen Schnellformatvorlagen-Satzes, um die im Schnellformatvorlagen-Katalog verfügbaren Formatvorlagen zu ändern. Die Design- und die Schnellformatvorlagen-Kataloge stellen beide Befehle zum Zurücksetzen bereit, damit Sie immer die Möglichkeit haben, das ursprüngliche Layout des Dokument s in der aktuellen Vorlage wiederherzustellen.\n",
                        pro01DBUsers.get(0).getId(),
                        pro01Users,
                        messageBoardId01)
        ));

        public static User findUserById(ObjectId id) {
            User user = null;
            for (User u : pro01DBUsers) {
                if(u.getId().equals(id)) { user = u; break; }
            }
            return user;
        }
    }
}

package models.DBTest;

import static org.junit.Assert.*;
import org.junit.Test;

<<<<<<< HEAD
//import org.jongo.Jongo;
=======
//import org.jongo.Jongo;   
>>>>>>> 12eae228ba26f904a1a9e25277046d1ec9f0faaa
//import org.jongo.MongoCollection;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.github.fakemongo.Fongo;
//import com.mongodb.DB;

public class UserTest {

    @Test
    public void test_user_001() {
        Users u = new Users( new org.bson.types.ObjectId() ,
                "FirstName1 ", "LastName1", "Test@mail.lan");
        assertEquals ( u.getFirstname(), "FirstName1 ");


    }

    @Test
    public void test_user_mail_001() {
        try{

            Users u = new Users( new org.bson.types.ObjectId() ,
                    "FirstName1 ", "LastName1", "keine.Mailadresse");

            assertTrue("Das ist keine gültige mailadresse ;)",false);
        }catch( Exception e){
            assertTrue("Wrong Mailadress", true);
        }

    }


}






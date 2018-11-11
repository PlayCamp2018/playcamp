package models.DBTest;

import static org.junit.Assert.*;
import org.junit.Test;

//import org.jongo.Jongo;   
//import org.jongo.MongoCollection;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.github.fakemongo.Fongo;
//import com.mongodb.DB;

public class UserTest {

    @Test
    public void test_user_001() {
        User u = new User( new org.bson.types.ObjectId() ,
                "FirstName1 ", "LastName1", "Test@mail.lan");
        assertEquals ( u.getFirstname(), "FirstName1 ");


    }

    @Test
    public void test_user_mail_001() {
        try{

            User u = new User( new org.bson.types.ObjectId() ,
                    "FirstName1 ", "LastName1", "keine.Mailadresse");

            assertFalse("Das ist keine gültige mailadresse ;)",false);
        }catch( Exception e){
            assertTrue("Wrong Mailadress", true);
        }

    }


}






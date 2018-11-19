package utils;

import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import static org.junit.Assert.*;

public class UtilsTest {

    private byte[] salt;
    private String exp;
    private String pwd;


    private void init_testdata() {
        salt =  Utils.toByte("6237646636666432303632323035323937653362613536646138623131623833");
        exp = "1000:6237646636666432303632323035323937653362613536646138623131623833:65083d5e023f32e32ca36fa2314babe64dc4df0b2f926f198429ba1e4602c64a4f866c51c71611a9f66a8a9d8a5f551de8d0ad8005a00eebc891e171eacd44d2";
        pwd = "TestString";
    }

    @Test
    public void test_hash() throws java.security.NoSuchAlgorithmException, InvalidKeySpecException {
        init_testdata();

        String hash = Utils.hash(pwd, salt);
        assertEquals(exp , hash);
        hash = Utils.hash("TestString",salt, Utils.DEFAULT_IT);
        assertEquals(exp , hash);
        hash = Utils.hash(pwd, Utils.DEFAULT_IT, salt);
        assertEquals(exp , hash);


    }

    @Test
    public void test_hashPBKDF2WithHmacSHA1_2() {
        try {
            init_testdata();
            String res = Utils.hash("TestString");
            assertNotNull(res);
            res = Utils.hash("TestString", Utils.DEFAULT_IT);
            assertNotNull(res);
        } catch (Exception e) {
            assertTrue("Exception on hashBBKDF2WithHmacSHA1", false);
        }
    }

    @Test
    public void test_toHex() {
        try {
            String s = Utils.toHex(null);
            assertTrue("No Exception on emty ByteArray", false);
        } catch (Exception e) {
            assertTrue("Exception, all fine.", true);
        }
    }

    @Test
    public void test_getSalt() {
        try {
            byte[] h = Utils.getSalt();
            assertEquals(h.length, 16);
        } catch (Exception e) {
            assertTrue("No Salt", false);
        }
    }
}

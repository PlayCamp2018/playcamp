package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void test_utils_hash() {
        String h = Utils.sha256("TestString");
        assertEquals(h, "6dd79f2770a0bb38073b814a5ff000647b37be5abbde71ec9176c6ce0cb32a27");
    }

    @Test
    public void test_hashPBKDF2WithHmacSHA1() {
        String salt = "b7df6fd2062205297e3ba56da8b11b83";
        byte[] b = salt.getBytes();
        try {
            String h = Utils.hashPBKDF2WithHmacSHA1("TestString", b);
            assertEquals(h, "1000:6237646636666432303632323035323937653362613536646138623131623833:65083d5e023f32e32ca36fa2314babe64dc4df0b2f926f198429ba1e4602c64a4f866c51c71611a9f66a8a9d8a5f551de8d0ad8005a00eebc891e171eacd44d2");
        } catch (Exception e) {
            assertTrue("Exception on hashBBKDF2WithHmacSHA1", false);
        }
    }

    @Test
    public void test_hashPBKDF2WithHmacSHA1_2() {
        try {
            String h = Utils.hashPBKDF2WithHmacSHA1("TestString");
            assertNotNull(h);
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

package utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import models.Database.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.bson.types.ObjectId;

/**
 * Helper Class for hashing the password PBKDF2WithHmacSHA1.
 */
public abstract class Utils {

    /**
     * Default hash integration of passworrd string
     */
    public static final int DEFAULT_IT = 1000;


    /**
     * This funktion hash a given String by the
     * given parameter values. the String
     * Hash by PBKDF2WithHmacSHA1.
     * https://de.wikipedia.org/wiki/PBKDF2
     *
     * @param password   The input password string for hashing
     * @param iterations The hash iteration for the password string.
     * @param salt       The salt to hash the password string.
     * @return Return the full password hash entry with iterations,
     * Salt and the hash result. pattern: "iteration, salt, result-hash"
     * @throws NoSuchAlgorithmException If the PBKDF2WithHmacSHA1 notsupported.
     * @throws InvalidKeySpecException
     * @Pattern: "iteration, salt, result-hash"
     * Easy use: <pre>String h = Utils.hash(String ( "PlainPwd" </pre>
     * Return the full password hash entry with iterations,
     * Salt and the hash result.
     * pattern: "iteration, salt, result-hash"
     * see also {@link "UtilsTest" int, byte[]) Tests }.
     */
    public static String hash(String password, int iterations, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        char[] chars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        //PBKDF2WithHmacSHA1,
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * Use the {@link #hash(String, int, byte[]) main doc. }.
     */
    public static String hash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = getSalt();
        return Utils.hash(password, salt);
    }

    /**
     * Use the {@link #hash(String, int, byte[]) main doc. }.
     */
    public static String hash(String password, int iterations)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = getSalt();
        return Utils.hash(password, iterations, salt);
    }

    /**
     * Use the {@link #hash(String, int, byte[]) main doc. }.
     */
    public static String hash(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Utils.hash(password, DEFAULT_IT, salt);
    }

    /**
     * Use the {@link #hash(String, int, byte[]) main doc. }.
     */
    public static String hash(String password, byte[] salt, int iterations)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Utils.hash(password, iterations, salt);
    }


    /**
     * This function convert an byte array int hex String.
     *
     * @param array The array of bytes to convert.
     * @return Returns the hex value as String.
     */
    public static final String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    /**
     * This function generate the salt string for the new password.
     *
     * @return Returns the the generated random 16 byte array
     * @throws NoSuchAlgorithmException Throws if the java eviremant
     *                                  suport not"SHA1PRNG"
     */
    public static final byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * This methode get the String hexsalt to calculate th byte array.
     *
     * @param hexsalt The string of hex values to generate the byte array
     * @return Returnt a array of bytes.
     */
    public static final byte[] toByte(final String hexsalt) {
        byte[] arr = new byte[hexsalt.length() / 2];
        for (int start = 0; start < hexsalt.length(); start += 2) {
            String thisByte = hexsalt.substring(start, start + 2);
            arr[start / 2] = Byte.parseByte(thisByte, 16);
        }
        return arr;
    }


    /**
     * Returns a random User, first and last name are numbers
     * @param id of the new User
     * @return random User
     */
    public static User getRandomUser(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 2000000 + 1);
        User u = new User(
                id,
                "" + randomNum,
                "" + (randomNum + 1),
                "test1234",
                randomNum + "." + (randomNum + 1) + "@test.test",
                new ObjectId()
        );
        return u;
    }
    /**
     * Returns a random Project, name and beschreibung are numbers
     * @param id of the new Project
     * @return random Project
     */
    public static Project getRandomProject(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 2000000 + 1);

        List<ObjectId> list = new ArrayList<>();
        list.add(new ObjectId());
        list.add(new ObjectId());

        Project p = new Project(
                id,
                "" + randomNum,
                "" + (randomNum + 1),
                new ObjectId(),
                list,
                new ObjectId()
        );

        return p;
    }
    /**
     * Returns a random MessagePost, message is a number
     * @param id of the new MessagePost
     * @return random MessagePost
     */
    public static MessagePost getRandomMessagePost(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 20000000 + 1);
        List<ObjectId> list = new ArrayList<>();
        list.add(new ObjectId());
        list.add(new ObjectId());

        MessagePost mp = new MessagePost(
                id,
                new ObjectId(),
                new Date(),
                new ObjectId(),
                list,
                "" + randomNum,
                new ObjectId()
        );

        return mp;
    }
    /**
     * Returns a random Meeting, name is a random number
     * @param id of the new Meeting
     * @return random MessagePost
     */
    public static Meeting getRandomMeeting(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 20000000 + 1);
        Map map = new HashMap<ObjectId, Meeting.Member>();
        map.put(new ObjectId(), Meeting.Member.YES);
        map.put(new ObjectId(), Meeting.Member.NO);
        map.put(new ObjectId(), Meeting.Member.UNKNOWN);

        Meeting m = new Meeting(
                id,
                new ObjectId(),
                "TEST_" + randomNum,
                new Date(),
                new ObjectId(),
                map
        );

        return m;
    }
    /**
     * Returns a random Files, name is a random number
     * @param id of the new Files
     * @return random Files
     */
    public static Files getRandomFiles(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 20000000 + 1);
        Files f = new Files(
                id,
                null,
                null,
                new ObjectId(),
                "TEST_" + randomNum
        );

        return f;
    }
    /**
     * Returns a random ChatMessageOrganizer
     * @param id of the new ChatMessageOrganizer
     * @return random ChatMessageOrganizer
     */
    public static ChatMessageOrganizer getRandomChatMessageOrganizer(ObjectId id) {
        ChatMessageOrganizer cmo = new ChatMessageOrganizer(
                id,
                new ObjectId(),
                new ObjectId()
        );

        return cmo;
    }
    /**
     * Returns a random ChatMessage
     * @param id of the new ChatMessage
     * @return random ChatMessage
     */
    public static ChatMessage getRandomChatMessage(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 20000000 + 1);
        ChatMessage cm = new ChatMessage(
                id,
                new Date(),
                new ObjectId(),
                "MSG_" + randomNum,
                new ObjectId()
        );

        return cm;
    }
    /**
     * Returns a random Chat
     * @param id of the new Chat
     * @return random Chat
     */
    public static Chat getRandomChat(ObjectId id) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 20000000 + 1);
        Chat c = new Chat(
                id,
                "NAME_" + randomNum,
                new ObjectId()
        );

        return c;
    }
}


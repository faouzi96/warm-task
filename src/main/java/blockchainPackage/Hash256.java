package blockchainPackage;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash256 extends Exception{
    private String hash;

    public Hash256(String input) throws NoSuchAlgorithmException {
        this.hash = hashSha256(input);
    }
    // Convert bytes to string and create our Hash of 64bit
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    private static String hashSha256(String input) throws NoSuchAlgorithmException {
        Charset UTF_8 = StandardCharsets.UTF_8;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] result = md.digest(input.getBytes(UTF_8));
        return bytesToHex(result);
    }

    public String getHash(){
        return this.hash;
    }
    public int getHashLength() {
        return this.hash.length();
    }
}

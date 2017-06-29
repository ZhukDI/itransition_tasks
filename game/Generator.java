import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest; //
import java.security.NoSuchAlgorithmException; //
import java.security.SecureRandom;

public class Generator {
    public static String generateHash(String key, String choice) throws NoSuchAlgorithmException {
//        String test = key;
        String phrase = choice;
//        String algorithm = "HmacSHA256";

        Charset utfCs = Charset.forName("US-ASCII");
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(utfCs.encode(key).array(), "HmacSHA256");
        try {
            sha256_HMAC.init(secretKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] mac_data = sha256_HMAC.doFinal(utfCs.encode(phrase).array());
        String result = DatatypeConverter.printHexBinary(mac_data);
        return result;
    }

    public static String generateKey() {
        String key = "";
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[10];
        random.nextBytes(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);  // if you want little-endian
        int result = Math.abs(buffer.getShort());
//        System.out.println(result);
        key = String.valueOf(result % 10000);
        return key;
    }


}

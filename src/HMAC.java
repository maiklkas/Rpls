import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HMAC {

    public static String calcHmacSha256(String msg, String keyString) throws NoSuchAlgorithmException, InvalidKeyException {
        String digest = null;
        SecretKeySpec key = new SecretKeySpec((keyString).getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes(StandardCharsets.US_ASCII));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        return digest;
    }

    static public String generateKey(int length) {
        char[] symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            char c = symbols[random.nextInt(symbols.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    static public String generateKey() {
        char[] symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();
        StringBuilder sb = new StringBuilder(29);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 29; i++) {
            char c = symbols[random.nextInt(symbols.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}
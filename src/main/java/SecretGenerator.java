import java.security.SecureRandom;
import java.math.BigInteger;

public abstract class SecretGenerator {

    public static String generateNewSecret() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}

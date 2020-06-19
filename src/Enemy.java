import java.security.SecureRandom;

public class Enemy {
    public int getComputerMove(int limit){
        SecureRandom random = new SecureRandom();
        return random.nextInt(limit);
    }
}

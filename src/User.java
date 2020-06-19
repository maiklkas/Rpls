import java.io.InputStream;
import java.util.Scanner;

public class User {
    public int getMove() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

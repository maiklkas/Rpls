import com.sun.source.tree.Tree;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Game {
    private String[] moves;

    public Game(String[] args) throws IncorrectMovesException {
        if (args.length % 2 == 0)
            throw new IncorrectMovesException("The quantity must be odd");

        Set<String> set = new HashSet<>(Arrays.asList(args));
        if(set.size() == args.length)
            moves = args;
        else
            throw new IncorrectMovesException("The moves must be unique");
    }

    private boolean checkMove(int move){
        return move >= 0 && move < moves.length;
    }

    private void printMoves(){
        for (int i = 0; i < moves.length; i++)
            System.out.printf("%s-%s\n", i+1, moves[i]);
        System.out.println("0-exit");
    }
    public void playInf(){
        int userMove;
        int computerMove;
        User user = new User();
        Enemy enemy = new Enemy();
        while (true) {
            String key = HMAC.generateKey();
            String hmac = null;
            computerMove = enemy.getComputerMove(moves.length);
            try {
                hmac = HMAC.calcHmacSha256( moves[computerMove], key);
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
            }
            System.out.printf("HMAC: %s\n", hmac);
            printMoves();
            System.out.print("Your choice: ");
            userMove = user.getMove() - 1;
            if (userMove == -1)
                return;
            if (!(checkMove(userMove) && checkMove(computerMove))) {
                System.out.println("Invalid input! Please, make correct choice");
                continue;
            }
            System.out.printf("Your move: %s\n", moves[userMove]);
            System.out.printf("Enemy move: %s\n", moves[computerMove]);

            Boolean gameResult = fight(userMove, computerMove);
            if(gameResult == null)
                System.out.println("Draw!");
            else if(gameResult)
                System.out.println("Victory!");
            else
                System.out.println("Defeat!");
            System.out.printf("HMAC key: %s\n\n", key);
        }
    }

    public void play(){
        int userMove;
        int computerMove;

        User user = new User();
        Enemy enemy = new Enemy();

        String key = HMAC.generateKey();
        String hmac = null;

        while (true) {
            computerMove = enemy.getComputerMove(moves.length);
            try {
                hmac = HMAC.calcHmacSha256( moves[computerMove], key);
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                e.printStackTrace();
            }
            System.out.printf("HMAC: %s\n", hmac);
            printMoves();
            System.out.print("Your choice: ");
            userMove = user.getMove() - 1;
            if (userMove == -1)
                return;
            if (!(checkMove(userMove) && checkMove(computerMove))) {
                System.out.println("Invalid input! Please, make correct choice");
            }
            else
                break;
        }
        System.out.printf("Your move: %s\n", moves[userMove]);
        System.out.printf("Enemy move: %s\n", moves[computerMove]);

        Boolean gameResult = fight(userMove, computerMove);
        if(gameResult == null)
            System.out.println("Draw!");
        else if(gameResult)
            System.out.println("Victory!");
        else
            System.out.println("Defeat!");
        System.out.printf("HMAC key: %s\n", key);
    }
    private Boolean fight(int userMove, int enemyMove) {
        int difference = userMove - enemyMove;
        if(difference == 0)
            return null;
        else return ((difference > 0 && difference <= (moves.length - 1) / 2) || (difference < 0 && Math.abs(difference) > (moves.length - 1) / 2));
    }
}
//"gun lightning devil dragon water air paper sponge wolf tree human snake scissors fire rock"
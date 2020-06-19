public class Main {
    public static void main(String[] args){
        try {
            Game game = new Game(args);
            game.playInf();
        } catch (IncorrectMovesException e) {
            e.printStackTrace();
        }
    }
}

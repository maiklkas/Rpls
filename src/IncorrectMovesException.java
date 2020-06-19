public class IncorrectMovesException extends Exception {
    public IncorrectMovesException() {
        super("Invalid moves!");
    }
    public IncorrectMovesException(String message) {
        super(message);
    }

}

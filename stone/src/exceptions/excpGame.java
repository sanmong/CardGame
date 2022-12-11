package exceptions;

@SuppressWarnings("serial")
public abstract class excpGame extends Exception{
    public excpGame() {
        super();
    }
    public excpGame(String message) {
        super(message);
    }
}

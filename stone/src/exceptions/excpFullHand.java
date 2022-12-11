package exceptions;

import model.cards.Card;

@SuppressWarnings("serial")
public class excpFullHand extends excpGame {
    private Card burned;
    public excpFullHand(Card burn) {
        super();
    }
    public excpFullHand(String message, Card burn) {
        super(message);
        burned=burn;
    }
    public Card getBurned() {
        return burned;
    }

}
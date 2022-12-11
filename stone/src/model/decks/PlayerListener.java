package model.decks;

import exceptions.excpFullHand;
public interface PlayerListener {
    public void onPlayerDeath();
    public void damageOpponent(int amount);
    public void endTurn() throws excpFullHand, CloneNotSupportedException;
}

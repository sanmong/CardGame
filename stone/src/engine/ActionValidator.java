package engine;

import exceptions.*;
import model.cards.Card;
import model.cards.monster.Monster;
import model.decks.Player;

public interface ActionValidator {
    public void validateTurn(Player user) throws excpNotYourTurn;

    public void validateAttack(Monster aAtk, Monster aTarget)
            throws excpTauntPass, excpInvalidTarget, excpNotSummoned, excpCannotAttack;

    public void validateAttack(Monster aMonster, Player aTarget)
            throws excpTauntPass, excpInvalidTarget, excpNotSummoned, excpCannotAttack;

    public void validateCost(Card aCard) throws excpNotEnoughCost;

    public void validatePlayingMonster(Monster aMonster) throws excpFullField;
}

package model.cards.monster;

import model.cards.Concept;

import exceptions.excpInvalidTarget;
import model.decks.Player;

public class CloverKing extends Monster {
    public CloverKing(){
        super("CloverKing", 10, Concept.POKER, 10, 10, false, false, true);
    }

    public void goAttack(Player target) throws excpInvalidTarget{
        if(getName().equals("CloverKing"))
            throw new excpInvalidTarget("this monster can't attack player");
        else
            super.goAttack(target);
    }
}

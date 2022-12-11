package engine;

import exceptions.*;
import model.cards.Card;
import model.cards.monster.Monster;
import model.decks.Player;
import model.decks.PlayerListener;

public class Game implements ActionValidator, PlayerListener {
    private Player firstPlayer;
    private Player secondPlayer;
    private Player curPlayer;
    private Player opponent;
    private GameListener listener;

    public Game(Player p1, Player p2) throws excpFullHand, CloneNotSupportedException {
        firstPlayer = p1;
        secondPlayer = p2;
        firstPlayer.setListener(this);
        firstPlayer.setValidator(this);
        secondPlayer.setListener(this);
        secondPlayer.setValidator(this);

        int coin = (int) (Math.random() * 2);
        curPlayer = coin == 0 ? firstPlayer : secondPlayer;
        opponent = curPlayer == firstPlayer ? secondPlayer : firstPlayer;
        curPlayer.setCurCost(1);
        curPlayer.setTotalCost(1);

        for (int i = 0; i <5; i++ ) {
            curPlayer.drawCard();
        }
        for (int i = 0; i < 6; i++) {
            opponent.drawCard();
        }
    }

    @Override
    public void validateTurn(Player user) throws excpNotYourTurn{
        if(user == opponent)
            throw new excpNotYourTurn("Not Your Turn");
    }

    public void validateAttack(Monster aAtk, Monster aTarget) throws excpInvalidTarget,excpNotSummoned,excpTauntPass, excpCannotAttack{
        if (aAtk.getAttack() <= 0)
            throw new excpCannotAttack("0 Can not Attack");
        if (aAtk.isSleeping())
            throw new excpCannotAttack("Can not Attack during this turn");
        if(aAtk.isAttacked())
            throw new excpCannotAttack("Can not Attack Twice");
        if(!curPlayer.getField().contains(aAtk))
            throw new excpNotSummoned("Can not Attack in Hand");
        if(curPlayer.getField().contains(aTarget))
            throw new excpInvalidTarget("Can not Attack Ally");
        if(!opponent.getField().contains(aTarget))
            throw new excpCannotAttack("Can not Attack to Hand");
        if(!aTarget.isTaunt()){
            for(int i = 0; i < opponent.getField().size(); i++){
                if(opponent.getField().get(i).isTaunt())
                    throw new excpTauntPass("Onlt Attack Taunt Monster");
            }
        }
    }

    public void validateAttack(Monster aMonster, Player aTarget)
            throws excpInvalidTarget,excpNotSummoned,excpTauntPass, excpCannotAttack {
        if (aMonster.getAttack() <= 0)
            throw new excpCannotAttack("This minion Cannot Attack");
        if (aMonster.isSleeping())
            throw new excpCannotAttack("Give this minion a turn to get ready");
        if (aMonster.isAttacked())
            throw new excpCannotAttack("This minion has already attacked");
        if (!curPlayer.getField().contains(aMonster))
            throw new excpNotSummoned("You can not attack with a minion that has not been summoned yet");
        if (aTarget.getField().contains(aMonster))
            throw new excpInvalidTarget("You can not attack yourself with your minions");
        for (int i = 0; i < opponent.getField().size(); i++) {
            if (opponent.getField().get(i).isTaunt())
                throw new excpTauntPass("A minion with taunt is in the way");
        }
    }

    public void validateCost(Card aCard) throws excpNotEnoughCost{
        if(curPlayer.getCurCost() < aCard.getCost())
            throw new excpNotEnoughCost("Not Enough Cost");
    }

    public void validatePlayingMonster(Monster aMonster) throws excpFullField{
        if (curPlayer.getField().size() >= 7)
            throw new excpFullField("Not Enough Field");
    }

    public Player getCurPlayer(){
        return curPlayer;
    }

    public void setListener(GameListener listener){
        this.listener = listener;
    }

        @Override
    public void onPlayerDeath(){
        listener.onGameOver();
    }

    @Override
    public void damageOpponent(int amount){
        opponent.setCurHP(opponent.getCurHP() - amount);
    }

    @Override
    public void endTurn() throws excpFullHand, CloneNotSupportedException{
        Player temp = curPlayer;
        curPlayer = opponent;
        opponent = temp;
        curPlayer.setTotalCost(curPlayer.getTotalCost() + 1);
        curPlayer.setCurCost(curPlayer.getTotalCost());
        for (Monster aMonster : curPlayer.getField()){
            aMonster.setAttacked(false);
            aMonster.setSleeping(false);
        }
        curPlayer.drawCard();
    }
    public Player getOpponent(){
        return opponent;
    }
}

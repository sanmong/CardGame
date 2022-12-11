package model.cards.monster;

import exceptions.*;
import model.cards.*;
import model.decks.*;

public class Monster extends Card implements  Cloneable {
    private int atk;
    private int maxHP;
    private int curHP;
    private boolean taunt;
    private boolean divine;
    public boolean sleeping;
    private boolean attacked;
    private MonsterListener listener;

    public Monster(String name, int cost, Concept concept, int atk, int maxHP, boolean taunt, boolean divine, boolean charge){
        super(name, cost, concept);
        this.atk = atk;
        this.maxHP = maxHP;
        this.curHP = maxHP;
        this.taunt = taunt;
        this.divine = divine;
        if (!charge)
            this.sleeping = true;
    }

    public void goAttack(Monster target){
        if (divine && target.divine)
        {
            if (target.atk > 0)
                divine = false;
            if(atk > 0)
                target.divine = false;
        } else if (divine) {
            target.setCurHP(target.curHP - atk);
            if (target.getAttack() > 0)
                divine = false;
        } else {
            target.setCurHP(target.curHP - atk);
            setCurHP(curHP - target.atk);
        }
        attacked = true;
    }

    public void goAttack(Player target) throws excpInvalidTarget {
        attacked = true;
        target.setCurHP(target.getCurHP() - atk);
    }

    public boolean isTaunt  () {
        return taunt;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHp) {
        this.maxHP = maxHp;
    }

    public int getCurHP() {
        return curHP;
    }

    public void setCurHP(int curHP){
        this.curHP = curHP;
        if (this.curHP> maxHP)
            curHP = maxHP;
        else if (this.curHP <= 0) {
            this.curHP = 0;
            listener.onMonsterDeath(this);
        }
    }

    public void setListener(MonsterListener listener) {
        this.listener = listener;
    }

    public int getAttack() {
        return atk;
    }

    public void setAttack(int atk) {
        this.atk = atk;
        if (this.atk <= 0)
            this.atk= 0;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public void setTaunt(boolean isTaunt) {
        this.taunt = isTaunt;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public boolean isDivine() {
        return divine;
    }

    public void setDivine(boolean divine) {
        this.divine = divine;
    }


    public Monster clone() throws CloneNotSupportedException {
        return (Monster) super.clone();
    }

    public String toString() {
        String s = "<html><center>" ;
        s+= this.getName() +"<br/>" +"Cost: "+ this.getCost() + "<br/>"+this.getConcept() +"<br/>";
        s+="Atk: " + atk+"<br/>" + "currHP : "+ curHP + "<br/>";
        if (this.isTaunt())
            s+= "Taunt <br/>" ;
        else {
            s+= "NotTaunt <br/>" ;
        }

        if (this.isDivine())
            s+= "Divine <br/>" ;
        else {
            s+= "NotDivine <br/>" ;
        }
        if (this.isSleeping()) {
            s+="Sleeping <br/>" ;
        }
        else {
            s+= "NotSleeping <br/>" ;
        }
        s+= "</center></html>" ;
        return s ;
    }
}

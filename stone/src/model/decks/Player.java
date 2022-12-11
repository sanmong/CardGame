package model.decks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.*;
import engine.ActionValidator;
import model.cards.monster.CloverKing;
import model.cards.monster.Monster;
import model.cards.monster.MonsterListener;
import model.cards.*;

public abstract class Player implements MonsterListener {
    private String name;
    private int curHP;
    private int totalCost;
    private int curCost;
    private ArrayList<Card> deck;
    private ArrayList<Monster> field;
    private ArrayList<Card> hand;
    private int fatigueDamage;
    private PlayerListener listener;
    private ActionValidator validator;

    public Player(String name) throws IOException, CloneNotSupportedException {
        this.name = name;
        curHP = 40;
        field = new ArrayList<Monster>(7);
        hand = new ArrayList<Card>(10);
        buildDeck();
    }

    public abstract void buildDeck() throws IOException, CloneNotSupportedException;

    public static final ArrayList<Monster> getAllMonsters(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        ArrayList<Monster> monsters = new ArrayList<Monster>();
        String current = br.readLine();
        //String current = "CloverKing,0,Neutral,0,0,FALSE,FALSE,FALSE";
        while (current != null) {
            String[] line = current.split(",");
            Monster monster = null;
            String aName = line[0];
            int aCost = Integer.parseInt(line[1]);
            Concept aConcept = null;
            switch ((line[2])) {
                case "POKER":
                    aConcept = Concept.POKER;
                    break;
                case "HUATU":
                    aConcept = Concept.HUATU;
                    break;
                case "Neutral":
                    aConcept = Concept.Neutral;
                    break;
            }
            int aAtk = Integer.parseInt(line[3]);
            int aHP = Integer.parseInt(line[4]);
            boolean aTaunt = line[5].equals("TRUE") ? true : false;
            boolean aDivine = line[6].equals("TRUE") ? true : false;
            boolean aCharge = line[7].equals("TRUE") ? true : false;

            if (!aName.equals("CloverKing"))
            {
                monster = new Monster(aName, aCost, aConcept, aAtk, aHP, aTaunt, aDivine,aCharge);
            }
            else
            {
                monster = new CloverKing();
            }
            monsters.add(monster);
            current = br.readLine();
        }
        br.close();
        return monsters;
    }

    public static final ArrayList<Monster> getMonsters(ArrayList<Monster> monsters, int count) throws CloneNotSupportedException {
        ArrayList<Monster> res = new ArrayList<Monster>();
        int i = 0;
        while (i < count) {

            int index = (int) (Math.random() * monsters.size());
            Monster monster = monsters.get(index);
            int occ = 0;
            for (int j = 0; j < res.size(); j++) {
                if (res.get(j).getName().equals(monster.getName()))
                    occ++;
            }
            if (occ == 0) {
                res.add(monster.clone());
                i++;
            }
        }
        return res;
    }

    public void listenerToMonsters(){
        for (Card aCard : deck){
            if (aCard instanceof Monster)
            {
                ((Monster) aCard).setListener(this);
            }
        }
    }

    public void playMonster(Monster aMonster) throws excpNotYourTurn, excpNotEnoughCost, excpFullField{
        validator.validateTurn(this);
        validator.validateCost(aMonster);
        validator.validatePlayingMonster(aMonster);
        curCost -= aMonster.getCost();
        hand.remove(aMonster);
        field.add(aMonster);
    }

    public void attackWithMonster(Monster attacker, Monster target) throws excpCannotAttack, excpNotYourTurn, excpInvalidTarget, excpTauntPass, excpNotSummoned{
        validator.validateTurn(this);
        validator.validateAttack(attacker, target);
        attacker.goAttack(target);
    }

    public void attackWithMonster(Monster attacker, Player target) throws excpCannotAttack, excpNotYourTurn, excpInvalidTarget, excpTauntPass, excpNotSummoned{
        validator.validateTurn(this);
        validator.validateAttack(attacker, target);
        attacker.goAttack(target);
    }

    public void endTurn() throws excpFullHand, CloneNotSupportedException{
        listener.endTurn();
    }

    public Card drawCard() throws excpFullHand, CloneNotSupportedException {
        if (fatigueDamage > 0){
            setCurHP(curHP - fatigueDamage);
            fatigueDamage++;
            return null;
        }

        Card aCard = deck.remove(0);
        if(deck.size() == 0)
            fatigueDamage = 1;
        if (hand.size() == 10){
            throw new excpFullHand("Hand is Full", aCard);
        }
        hand.add(aCard.clone());
        return aCard;
    }

    public int getCurHP() {
        return curHP;
    }

    public void setCurHP(int hp) {
        this.curHP = hp;
        if(this.curHP <= 0){
            this.curHP = 0;
            listener.onPlayerDeath();
        }
    }

    public int getTotalCost(){
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getCurCost(){
        return curCost;
    }

    public void setCurCost(int curCost){
        this.curCost = curCost;
    }

    public void onMonsterDeath(Monster aMonster){
        field.remove(aMonster);
    }

    public PlayerListener getListener(){
        return listener;
    }

    public ArrayList<Monster> getField(){
        return field;
    }

    public void setListener(PlayerListener listener){
        this.listener = listener;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

    public boolean fieldContain(String aName){
        for (Monster aMonster : field){
            if (aMonster.getName().equals(aName))
                return true;
        }
        return false;
    }

    public String getName(){
        return name;
    }

    public void setValidator(ActionValidator validator){
        this.validator = validator;
    }
}

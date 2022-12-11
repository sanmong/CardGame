package model.decks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.*;
import model.cards.monster.Monster;

public class POKER extends Player {
    public POKER() throws IOException, CloneNotSupportedException {
        super("POKER");
    }

    @Override
    public void buildDeck() throws IOException, CloneNotSupportedException{
        ArrayList<Monster> pokers = getMonsters(getAllMonsters("poker_monsters.csv"), 52);
        getDeck().addAll(pokers);
        listenerToMonsters();
        Collections.shuffle(getDeck());
    }
}


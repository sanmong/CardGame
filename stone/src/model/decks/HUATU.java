package model.decks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.*;
import model.cards.monster.Monster;

public class HUATU extends Player {
    public HUATU() throws IOException, CloneNotSupportedException {
        super("HUATU");
    }

    @Override
    public void buildDeck() throws IOException, CloneNotSupportedException{
        ArrayList<Monster> pokers = getMonsters(getAllMonsters("huatu_monsters.csv"), 48);
        getDeck().addAll(pokers);
        listenerToMonsters();
        Collections.shuffle(getDeck());
    }
}
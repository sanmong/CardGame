package model.decks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.*;
import model.cards.Concept;
import model.cards.monster.Monster;

public class HUATU extends Player {
    public HUATU() throws IOException, CloneNotSupportedException {
        super("HUATU");
    }

    @Override
    public void buildDeck() throws IOException, CloneNotSupportedException{
        ArrayList<Monster> pokers = getMonsters(getAllMonsters("huatu.csv"), 48);
        try{getDeck().addAll(pokers);
        Monster Boom = (new Monster("Boom", 1, Concept.Neutral, 3,3,false,false,true));
        getDeck().add(Boom);
        listenerToMonsters();
        Collections.shuffle(getDeck());
        }catch(NullPointerException e){
            System.out.println("Null pointer exception");
        }
    }
}
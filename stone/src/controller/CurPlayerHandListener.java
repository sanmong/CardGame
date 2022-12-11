package controller;

import model.cards.monster.Monster;
import view.CardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurPlayerHandListener implements ActionListener {
    controller controller;
    public CurPlayerHandListener(controller controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardView aAtk = (CardView)e.getSource();
        controller.setAttacker(aAtk);
        if(aAtk.getACard() instanceof Monster)
            controller.playCard(aAtk.getACard());
    }
}

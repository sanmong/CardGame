package controller;

import model.cards.monster.Monster;
import view.CardView;
import view.OpponentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpponentListener implements ActionListener {
    controller controller;
    public OpponentListener(controller controller){
        this.controller = controller ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OpponentView aAtk = (OpponentView) e.getSource();

        if (controller.getAttacker() != null){
            CardView aCard = (CardView)controller.getAttacker() ;
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"Invalid Target");
        } else if (controller.getFieldMonsterAttacker() != null) {
            CardView aCard = (CardView) controller.getFieldMonsterAttacker();
            controller.attackPlayer((Monster) aCard.getACard(), aAtk.getAPlayer());
        }
    }
}

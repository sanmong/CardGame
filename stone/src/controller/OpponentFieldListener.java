package controller;

import model.cards.monster.Monster;
import view.CardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpponentFieldListener implements ActionListener {
    controller controller;

    public OpponentFieldListener(controller controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardView aAtk = (CardView) e.getSource();
        if (controller.getAttacker() != null) {
            CardView aCard = (CardView) controller.getAttacker();
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j, "Invalid Target");
        } else if (controller.getFieldMonsterAttacker() != null) {
            CardView aCard = (CardView) controller.getFieldMonsterAttacker();
            controller.attackMonster((Monster) aCard.getACard(), (Monster) aAtk.getACard());
        }
    }
}

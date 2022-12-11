package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;

import javax.swing.*;

public class CurPlayerFieldListener implements ActionListener {
    controller controller;
    public CurPlayerFieldListener(controller controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardView aAtk = (CardView) e.getSource();
        controller.setFieldMonsterAttacker((JButton)e.getSource());

        if(controller.getAttacker() != null){
            CardView aCard = (CardView) controller.getAttacker();
            //JFrame j = new JFrame();
            //JOptionPane.showMessageDialog(j, "Invalid Target");
        }
    }
}

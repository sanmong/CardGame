package controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.cards.*;
import view.*;

public class CurPlayerListener implements ActionListener {
    controller controller;

    public CurPlayerListener(controller controller){
        this.controller = controller ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CurPlayerView aAtk = (CurPlayerView) e.getSource();

        if (controller.getAttacker() != null){
            CardView aCard = (CardView)controller.getAttacker() ;
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"Invalid Target");
        }
    }
}

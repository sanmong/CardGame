package view;

import javax.swing.*;
import java.awt.*;


import model.decks.Player;


public class GameView extends JFrame {
    //startPage
    private JButton btnStart;
    private JButton btnExit;
    //chooseDeckPage
    private JLabel lblChooseDeck;
    private JButton btnPOKER;
    private JButton btnHUATU;
    //gamePage
    private JPanel firstPlayerPart;
    private JPanel secondPlayerPart;

    private JPanel firstPlayerField;
    private JPanel secondPlayerField;

    private JPanel firstPlayerImg;
    private JPanel secondPlayerImg;

    private JPanel firstPlayerHand;

    public GameView(){
        super();

        this.setSize(1280, 1024);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startPage(){
        this.btnStart = new JButton("START:GAME!!");
        this.btnExit = new JButton("EXIT:GAME!!");

        this.setLayout(new BorderLayout());

        JLabel lblTop = new JLabel("START:DUEL!");
        JPanel pnlTop = new JPanel();
        pnlTop.add(lblTop, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel();
        pnlBottom.setLayout(new GridLayout(1,0));
        pnlBottom.setPreferredSize(new Dimension(pnlBottom.getWidth(), 200));
        pnlBottom.add(btnStart);
        pnlBottom.add(btnExit);

        this.add(pnlTop,BorderLayout.NORTH);
        this.add(pnlBottom,BorderLayout.SOUTH);

        this.btnPOKER = new JButton("POKER");
        this.btnHUATU = new JButton("HUATU");
        this.btnExit = new JButton("EXIT");

        this.setVisible(true);
    }

    public JPanel getFirstPlayerPart(){
        return firstPlayerPart;
    }

    public JPanel getSecondPlayerPart(){
        return secondPlayerPart;
    }

    public JPanel getFirstPlayerField() {return firstPlayerField;}

    public JPanel getSecondPlayerField() {return secondPlayerField;}


    public JPanel getFitstPlayerImg() {
        return firstPlayerImg;
    }


    public JPanel getSecondPlayerImg() {
        return secondPlayerImg;
    }

    public JPanel getFirstPlayerHand() {
        return firstPlayerHand;
    }

    public void chooseDeckPage(){
        this.setLayout(new GridLayout(0,1));

        lblChooseDeck = new JLabel("Choose Deck");
        JPanel pnlCenter = new JPanel();
        pnlCenter.add(lblChooseDeck,BorderLayout.CENTER);

        this.add(pnlCenter);
        this.add(btnPOKER);
        this.add(btnHUATU);
        this.add(btnExit);

        this.setVisible(true);
    }

    public void gamePage(){
        firstPlayerPart = new JPanel();
        firstPlayerField = new JPanel();
        firstPlayerHand = new JPanel();
        firstPlayerImg = new JPanel();

        secondPlayerPart = new JPanel();
        secondPlayerField = new JPanel();
        secondPlayerImg = new JPanel();

        this.setLayout(new GridLayout(0,1));

        this.secondPlayerPart.setLayout(new GridLayout(0,1));
        this.secondPlayerPart.add(secondPlayerImg);
        this.secondPlayerPart.add(secondPlayerField);

        this.firstPlayerPart.setLayout(new GridLayout(0,1));
        this.firstPlayerPart.add(firstPlayerField);
        this.firstPlayerPart.add(firstPlayerImg);
        this.firstPlayerPart.add(firstPlayerHand);

        this.secondPlayerImg.setLayout(new FlowLayout());
        this.secondPlayerField.setLayout(new FlowLayout());

        this.firstPlayerField.setLayout(new FlowLayout());
        this.firstPlayerImg.setLayout(new FlowLayout());
        this.firstPlayerHand.setLayout(new FlowLayout());

        this.add(secondPlayerPart) ;
        this.add(firstPlayerPart) ;
        this.setVisible(true);
    }

    public void gameOver(Player aPlayer){
        this.getContentPane().removeAll();
        this.repaint();

        JLabel lbl = new JLabel();
        lbl.setText("WINNER : " + aPlayer.getName());
        this.add(lbl);
        this.setVisible(true);
    }

    public JButton getStartGame() {
        return btnStart;
    }

    public JButton getExit (){
        return btnExit;
    }

    public JButton getPOKER(){
        return btnPOKER;
    }

    public JButton getHUATU(){
        return btnHUATU;
    }

    public JLabel getLblChooseDeck(){
        return lblChooseDeck;
    }
}

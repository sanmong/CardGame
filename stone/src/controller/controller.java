package controller;

import engine.Game;
import engine.GameListener;
import exceptions.*;
import model.cards.Card;
import model.cards.monster.Monster;
import model.decks.HUATU;
import model.decks.POKER;
import model.decks.Player;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class controller implements ActionListener, GameListener {
    private Game model;
    private GameView gameView;
    private boolean startedGame = false;
    private boolean chooseDeckPage = false;
    private Player firstPlayer;
    private Player secondPlayer;

    private ArrayList<JButton> firstField;
    private ArrayList<JButton> firstHand;
    private JButton curPlayer;

    private ArrayList<JButton> secondField;
    private JButton opponentPlayer;

    private JButton endTurn;
    private JButton attacker;
    private JButton fieldMonsterAttacker;

    private CurPlayerListener curPlayerListener;
    private CurPlayerFieldListener curPlayerFieldListener;
    private CurPlayerHandListener curPlayerHandListener;

    private OpponentListener opponentListener;
    private OpponentFieldListener opponentFieldListener;

    private EndTurnListener endTurnListener;

    public controller(){

        gameView = new GameView();
        gameView.startPage();
        gameView.getStartGame().addActionListener(this);
        gameView.getExit().addActionListener(this);

        gameView.getPOKER().addActionListener(this);
        gameView.getHUATU().addActionListener(this);

        firstField = new ArrayList<JButton>();
        firstHand = new ArrayList<JButton>();

        secondField = new ArrayList<JButton>();

        curPlayerListener = new CurPlayerListener(this);
        curPlayerFieldListener = new CurPlayerFieldListener(this);
        curPlayerHandListener = new CurPlayerHandListener(this);

        opponentListener = new OpponentListener(this);
        opponentFieldListener = new OpponentFieldListener(this);
        curPlayerHandListener = new CurPlayerHandListener(this);


        endTurnListener = new EndTurnListener(this);

        gameView.revalidate();
        gameView.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        firstPlayer = null;
        secondPlayer = null;

        if(!startedGame){
            if(e.getSource() == gameView.getExit()){
                System.exit(0);
            } else if (e.getSource() == gameView.getStartGame()) {
                this.startedGame = true;
                this.gameView.getContentPane().removeAll();
                this.gameView.repaint();
                this.gameView.chooseDeckPage();
            }
        } else if (!chooseDeckPage) {
            try{
                if(firstPlayer == null){
                    if(e.getSource() == gameView.getExit()) {
                        System.exit(0) ;
                    }else if(e.getSource() == gameView.getPOKER()){
                        firstPlayer = new POKER();
                    }else{
                        firstPlayer = new HUATU();
                    }
                    gameView.getLblChooseDeck().setText("SELECT OPPONENT");
                }
                else {
                    if(e.getSource() == gameView.getExit()) {
                        System.exit(0) ;
                    }else if(e.getSource() == gameView.getPOKER()){
                        secondPlayer = new POKER();
                    } else {
                        secondPlayer = new HUATU();
                    }
                    try{
                        this.model = new Game(firstPlayer, secondPlayer);
                        this.model.setListener(this);
                    }catch (excpFullHand e1){
                        JFrame j = new JFrame();
                        JOptionPane.showMessageDialog(j, "Full Hand");
                    }
                    chooseDeckPage = false;
                    this.render();
                }
            }catch (IOException | CloneNotSupportedException e1){
                e1.printStackTrace();
            }

        }

    }
    public void endTurn(){
        try{
            model.endTurn();
        }catch (excpFullHand | CloneNotSupportedException e1){
            JFrame j =new JFrame();
            JOptionPane.showMessageDialog(j, "Full Hand");
        }
        this.render();
    }

    public void playCard(Card aCard){
        try {
            if(aCard instanceof Monster){
                model.getCurPlayer().playMonster((Monster) aCard);
            }
        }catch (excpNotYourTurn e){
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"Not Your Turn");
        }catch (excpNotEnoughCost e){
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"Not Enough Cost");
        }catch (excpFullField e){
            JFrame j = new JFrame();
            JOptionPane.showMessageDialog(j,"Full Field");
        }
        this.attacker = null;
        this.render();
    }
    public void attackMonster(Monster aCard, Monster aAtk){
        try {
            model.getCurPlayer().attackWithMonster(aCard, aAtk);
        }catch (excpInvalidTarget e) {
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Invalid Target");
        }catch (excpNotSummoned e){
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "This Card Is In Hand");
        }catch (excpCannotAttack e){
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Can not Attack");
        }catch (excpNotYourTurn e){
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Not Your Turn");
        }catch (excpTauntPass e) {
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Only Attack Taunt Monster");
        }
        this.fieldMonsterAttacker = null;
        this.render();
    }

    public void attackPlayer(Monster aCard, Player aPlayer){
        try {
            model.getCurPlayer().attackWithMonster(aCard, aPlayer);
        }catch (excpInvalidTarget e) {
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Invalid Target");
        }catch (excpNotSummoned e){
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "This Card Is In Hand");
        }catch (excpCannotAttack e){
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Can not Attack");
        }catch (excpNotYourTurn e){
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Not Your Turn");
        }catch (excpTauntPass e) {
            JFrame j = new JFrame() ;
            JOptionPane.showMessageDialog(j, "Only Attack Taunt Monster");
        }
        this.fieldMonsterAttacker = null;
        this.render();
    }

    @Override
    public void onGameOver(){
        if(model.getOpponent().getCurHP() == 0)
            gameView.gameOver(model.getCurPlayer());
        else
            gameView.gameOver(model.getOpponent());
    }

    public void render(){
        if(model.getCurPlayer().getCurHP() > 0 && model.getOpponent().getCurHP() > 0){
            gameView.getContentPane().removeAll();
            gameView.repaint();
            gameView.gamePage();

            for(int i = 0; i <model.getCurPlayer().getField().size(); i++){
                CardView a = new CardView(model.getCurPlayer().getField().get(i));
                a.addActionListener(curPlayerFieldListener);
                firstField.add(a);
                this.gameView.getFirstPlayerField().add(a);
            }

            for (int i = 0; i < model.getCurPlayer().getHand().size(); i++) {
                CardView a = new CardView(model.getCurPlayer().getHand().get(i));
                a.addActionListener(curPlayerHandListener);
                firstHand.add(a);
                this.gameView.getFirstPlayerHand().add(a);
            }

            for (int i = 0; i < model.getOpponent().getField().size(); i++){
                CardView a = new CardView(model.getOpponent().getHand().get(i));
                a.addActionListener(opponentFieldListener);
                secondField.add(a);
                this.gameView.getSecondPlayerField().add(a);
            }

            CurPlayerView c = new CurPlayerView(model.getCurPlayer());
            c.addActionListener(curPlayerListener);
            gameView.getFitstPlayerImg().add(c);
            this.curPlayer = c;

            OpponentView o = new OpponentView(model.getOpponent());
            o.addActionListener(opponentListener);
            gameView.getSecondPlayerImg().add(o);
            this.opponentPlayer = o;

            EndTurnBtn e = new EndTurnBtn();
            gameView.getFitstPlayerImg().add(e);
            e.addActionListener(endTurnListener);
            this.endTurn = e;

            gameView.setVisible(true);
        }
    }

    public JButton getCurPlayer(){
        return curPlayer;
    }

    public void setCurPlayer(JButton curPlayer){
        this.curPlayer = curPlayer;
    }

    public JButton getAttacker(){
        return attacker;
    }

    public void setAttacker(JButton attacker) {
        this.attacker = attacker;
    }

    public JButton getFieldMonsterAttacker(){
        return fieldMonsterAttacker;
    }

    public void setFieldMonsterAttacker(JButton fieldMonsterAttacker) {
        this.fieldMonsterAttacker = fieldMonsterAttacker;
    }

    public Game getModel(){
        return this.model;
    }

    public static void main(String[]args) {
        new controller();
    }
}

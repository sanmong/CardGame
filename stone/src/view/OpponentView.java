package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.decks.Player;


public class OpponentView extends JButton{

    private Player aPlayer;

    public OpponentView(Player aPlayer) {

        this.aPlayer = aPlayer ;
        this.setPreferredSize(new Dimension(108,128));
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setBorder(BorderFactory.createLineBorder(Color.red ));
        this.setText(this.toString());
    }

    public Player getAPlayer ()  {
        return aPlayer ;
    }


    public String toString() {
        return "<html><center>" + aPlayer.getName() + "<br/>" + "HP: "+ aPlayer.getCurHP() +"<br/>"+ "Cost: "+aPlayer.getCurCost()+"/"+aPlayer.getTotalCost()+"<br/>"+"Hand Cards: "+aPlayer.getHand().size()+"<br/>"+"Deck Cards: "+aPlayer.getDeck().size()+"<br/>"+"</center></html>" ;
    }

}
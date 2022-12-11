package view;

import model.decks.Player;

import javax.swing.*;
import java.awt.*;

public class CurPlayerView extends JButton {
    private Player aPlayer;

    public CurPlayerView(Player aPlayer){
        this.aPlayer = aPlayer;

        this.setPreferredSize(new Dimension(108, 127));
        this.setFont(new Font("Arial", Font.BOLD, 11));
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setText(this.toString());
    }

    public Player getAPlayer() {
        return aPlayer;
    }

    public String toString(){
        return "<html><center>" + aPlayer.getName() + "<br/>" + "HP: "+ aPlayer.getCurHP() +"<br/>"+ "Cost: "+aPlayer.getCurCost()+"/"+aPlayer.getTotalCost()+"<br/>"+"Deck Cards: "+aPlayer.getDeck().size()+"<br/>"+"</center></html>" ;
    }
}

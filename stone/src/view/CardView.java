package view;

import model.cards.Card;

import javax.swing.*;
import java.awt.*;

public class CardView extends JButton {
    public Card aCard;

    public Card getACard(){
        return aCard;
    }

    public CardView (Card aCard){
        this.aCard = aCard;
        this.setPreferredSize(new Dimension(108, 128));
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setText(this.toString());
    }

    public String toString() {
        return aCard.toString();
    }
}

package model.cards;

public abstract class Card implements Cloneable {
    private String name;
    private int cost;
    private Concept concept;

    public Card(String name, int cost, Concept concept){
        this.name = name;
        this.cost = cost;
        this.concept = concept;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public Concept getConcept(){
        return concept;
    }

    public Card clone() throws CloneNotSupportedException{
        return (Card) super.clone();
    }

    public  String toString() {
        return "<html><center>"+name +"<br/>"+ "Concept: "+concept +"<br/>" +"Cost"+cost +"</center></html>";
    }
}

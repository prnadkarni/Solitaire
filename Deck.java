

import java.util.ArrayList;
import java.util.Random;

/**
 * Pooja Nadkarni
 * Period 1
 * 11/30/17
 * 
 * This lab took me around an hour.
 * 
 * This program went well for me. I was able to select an ArrayList to use
 * as a Deck, and I could complete the necessary methods for each class. I 
 * was also able to understand how Solitaire is played so that I would have
 * some idea of how to code it in the future.
 * 
 */

public class Deck
{
    /** ArrayList value that holds the cards in this Deck */
    private ArrayList <Card> deck;
    
    /**
     * Creates a new <code>Deck</code> instance.
     *
     * */
    public Deck(){
        deck = new ArrayList();
    }
    
    public Deck(String deckString){
        int value = 0;
        String symbol = "";
        deck = new ArrayList();
        for(int i = 0; i < deckString.length(); i++){
            symbol = "" + deckString.charAt(i);
            if(symbol.equals("A")){
                value = 1;
            } else if(symbol.equals("Q")){
                value = 12;
            } else if(symbol.equals("K")){
                value = 13;
            } else if(symbol.equals("J")){
                value = 11;
            } else if(symbol.equals("T")){
                value = 10;
            } else {
                value = Integer.parseInt(symbol);
            }
            Card card = new Card(symbol, value);
            if(deckString.charAt(i + 2) == 'T'){
                card.setFaceUp(true);
            } else {
                card.setFaceUp(false);
            }
            deck.add(card);
            i += 4;
        }
    }
    
    /**
     * Randomly shuffles the contents of this Deck.
     */ 
    public void shuffle(){
        Random rand = new Random();
        boolean repeat = false;
        int index = 0;
        for(int i = 0; i < deck.size(); i++){
            index = rand.nextInt(deck.size() - i) + i;
            Card card = (Card) deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, card);
        }
    }
    
    /**
     * Adds a new <code>Card</code> value to the Deck.
     *
     * @param add, a <code>Card</code> value to add to the Deck
     * 
     * */
    public void add(Card card){
        deck.add(card);
    }
    
    /**
     * Returns the ArrayList that represents the Deck of Cards
     *
     * @return an <code>ArrayList</code> that represents the Deck of Cards
     */
    public Card getCard(int index){
        return deck.get(index);
    }
    
    public String deckSaveToString(){
        String ans = "";
        for(int i = 0; i < deck.size(); i++){
            String cardInfo = deck.get(i).getSymbol() + " ";
            if(deck.get(i).isFaceUp()){
                cardInfo += "T";
            } else {
                cardInfo += "F";
            }        
            if(i != deck.size() - 1){
                cardInfo += ", ";
            }
            ans += cardInfo;
        }
        return ans;
    }
    
    public void removeCard(int index){
        deck.remove(index);
    }
    
    public int size(){
        return deck.size();
    }
    
    /**
     * Returns this Deck as a String, with each Card represented in an 
     * ArrayList.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of each card in the Deck.
     */
    @Override
    public String toString(){
        return deck.toString();
    }
    
    
}

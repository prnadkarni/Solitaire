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


public class DeckTester
{
    public static void main(String[] args) {
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        Deck deck1 = new Deck();
        for(int i = 0; i < values.length; i++){
            Card card = new Card(symbols[i], values[i]);
            card.setFaceUp(true);
            deck1.add(card);
        }
        System.out.println(deck1.toString());
        deck1.shuffle();
        System.out.println(deck1.toString());
        System.out.println(deck1.deckSaveToString());
        Deck deck2 = new Deck(deck1.deckSaveToString());
        System.out.println(deck2.toString());
    }
}

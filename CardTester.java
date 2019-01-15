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

public class CardTester
{
    public static void main(String[] args) {
        Card card1 = new Card("A", 1);
        System.out.println(card1.getSymbol());
        System.out.println(card1.getValue());
        System.out.println(card1.toString());
        Card card2 = new Card("2", 2);
        System.out.println(card2.isFaceUp());
        card2.setFaceUp(true);
        System.out.println(card2.isFaceUp());
        Card card3 = new Card("2", 2);
        System.out.println(card2.equals(card3));
        Card card4 = new Card("4", 4);
        card4.setFaceUp(true);
        System.out.println(card4.toString());
    }
}

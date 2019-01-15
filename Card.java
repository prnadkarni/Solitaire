/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;
    
    public int compareTo(Card other){
        return this.getValue() - other.getValue();
    }
    
    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
         this.symbol = symbol;
         this.value = value;
         isFaceUp = false;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Returns value <code>int</code> of a card
     *  
     *  @return value <code>int</code> of card
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Sets <code>boolean</code> isFaceUp
     *  
     *  @return value <code>boolean</code> stating whether or not the card 
     *  is facing up
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }
    
     /**
     * Sets <code>boolean</code> isFaceUp
     *  
     *  @param value <code>boolean</code> stating whether or not the card 
     *  is facing up
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        if(this.value == other.getValue()){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        if(!isFaceUp){
            return "X";
        } else {
            return symbol;
        }
     }
}

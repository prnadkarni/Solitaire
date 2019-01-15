import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    private ArrayList <Deck> stacks;
    private Deck deck;
    private int NUM_STACKS = 0;
    private int NUM_DECKS = 0;
    private Deck drawPile;
    
    public static void main(){

    }
    
    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    
    public Board(int numStacks, int numDecks) {
        deck = new Deck();
        stacks = new ArrayList <Deck> (numStacks);
        NUM_STACKS = numStacks;
        NUM_DECKS = numDecks; 
        drawPile = new Deck();
        
        for(int i = 0; i < NUM_DECKS; i++){
            String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
            int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
            for(int n = 0; n < values.length; n++){
                Card card = new Card(symbols[n], values[n]);
                deck.add(card);
            }
        }
        deck.shuffle();
        
        int numStackCards = (deck.size()) / 2;
        int cardsPerStack = numStackCards / NUM_STACKS;
        
        for(int x = 0; x < NUM_STACKS; x++){
            Deck deck1 = new Deck();
            for(int y = 0; y < cardsPerStack; y++){                
                deck1.add(deck.getCard(y + (cardsPerStack * x)));
            }
            stacks.add(deck1);
        }        

        for(int o = 0; o <= (deck.size() - numStackCards); o++){
            drawPile.add(deck.getCard(numStackCards + o - 1));
        }       
    }
        
    public void restoreDeck(){
        // Create a JFileChooser that points to the current directory
        JFileChooser chooser = new JFileChooser(".");
        
        // Tell the JFileChooser to show a Open dialog window
        chooser.showOpenDialog(null);
        
        // Ask the JFileChooser for the File the user typed in or selected
        File savedGame = chooser.getSelectedFile();
        
        if(savedGame == null){
            System.out.println("Please try again and select a file");
        } else {
            // Create a FileWriter that can write to the selected File
            Scanner scan = null;
            try{
                scan = new Scanner(savedGame);
            } catch(FileNotFoundException e){
                System.out.println("Error: " + e.getMessage());
            }
            int count = 0;             
            try{
                while(scan.hasNext()){
                    String line = scan.nextLine();
                    Deck deck = new Deck(line);
                    if(count < NUM_STACKS){
                        stacks.set(count, deck);
                        count++;
                    } else {
                        drawPile = deck;
                    }                    
                }
            } catch(IllegalStateException e){
                System.out.println("Error: " + e.getMessage());
            }
        }        
    }
    
    public void saveDeck(){
        // Create a JFileChooser that points to the current directory
        JFileChooser chooser = new JFileChooser(".");
        
        // Tell the JFileChooser to show a Save dialog window
        chooser.showSaveDialog(null);
        
        // Ask the JFileChooser for the File the user typed in or selected
        File savedGame = chooser.getSelectedFile();
        
        if(savedGame == null){
            System.out.println("Please try again and select a file");
        } else {
            // Create a FileWriter that can write to the selected File
            FileWriter writer = null;
            try{
                writer = new FileWriter(savedGame);
            } catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
            
            String newLine = "\r\n";
            
            for(int i = 0; i < NUM_STACKS; i++){
                String line = stacks.get(i).deckSaveToString();
                try{
                    writer.write(line + newLine);
                } catch(IOException e){
                    System.out.println("Error: " + e.getMessage());
                }
            }
            String line = drawPile.deckSaveToString();
            try{
                writer.write(line);
            } catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
            try{
                    writer.close();
                } catch(IOException e){
                    System.out.println("Error: " + e.getMessage());
                }
        }        
    }
    
    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest) {
        if(src >= stacks.size() || src < 0 || 
        dest >= stacks.size() || dest < 0){
            System.out.println("Please enter an integer between 1 and "
            + (NUM_STACKS) + " to represent the stack");
        } else {
            int indexRun = stacks.get(src).size();
            for(int i = stacks.get(src).size() - 1; i >= 0; i--){
                if(stacks.get(src).getCard(i).getSymbol().equals(symbol)){
                    indexRun = i;
                    break;
                }
            } 
            if(indexRun == stacks.get(src).size()){
                System.out.println("Symbol Card Not Found In Stack");
            }
            if(indexRun == stacks.get(src).size() || !isValidMove(symbol, src, 
            dest, indexRun)){
                System.out.println("Move Is Invalid");
            } else {
                for(int n = indexRun; n < stacks.get(src).size(); n++){
                    stacks.get(dest).add(stacks.get(src).getCard(n));
                }
                int size = stacks.get(src).size() - indexRun;
                for(int x = 0; x < size; x++){
                    stacks.get(src).removeCard(indexRun);
                }
                if(stacks.get(src).size() > 0){
                    stacks.get(src).getCard(stacks.get(src).size() - 1).setFaceUp(true);
                }
            }       
        }        
    }
    
    private boolean isValidMove(String symbol, int src, int dest, int index){
        Deck deck = stacks.get(src);
        int symbolValue = deck.getCard(index).getValue();
        int origRunLength = deck.size() - index;
        
        for(int n = index; n < deck.size(); n++){
            if(!deck.getCard(n).isFaceUp()){
                System.out.println("Symbol Card Not Found In Stack");
                return false;
            }
        }
        
        int count = 0;
        for(int i = index; i < deck.size(); i++){
            if(deck.getCard(i).getValue() != symbolValue - count){
                return false;               
            }
            count++;
        }
        
        if(stacks.get(dest).size() > 0 && stacks.get(dest).getCard
        (stacks.get(dest).size() - 1).getValue() != symbolValue + 1){
            return false;
        }
        
        return true;
    }
    
    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        if(!isEmpty()){
            if(drawPile.size() >= NUM_STACKS){
                for(int i = 0; i < NUM_STACKS; i++){
                    drawPile.getCard(0).setFaceUp(true);
                    stacks.get(i).add(drawPile.getCard(0));
                    drawPile.removeCard(0);
                }
            } else {
                for(int i = 0; i < drawPile.size(); i++){
                    drawPile.getCard(0).setFaceUp(true);
                    stacks.get(i).add(drawPile.getCard(0));
                    drawPile.removeCard(0);
                }
            }
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        boolean empty = true;
        for(int i = 0; i < stacks.size(); i++){
            if((stacks.get(i)).size() != 0){
                empty = false;
            }
        }
        if(drawPile.size() == 0 && empty){
            return true;
        } else {
            return false;
        }
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        if(sourceStack >= stacks.size() || sourceStack < 0){
            System.out.println("Please enter an integer between 1 and "
            + (NUM_STACKS) + " to represent the stack");
        } else {
            int start = stacks.get(sourceStack).size();
            int end = stacks.get(sourceStack).size() - 13;
            boolean size = true;
            if(start < 13){
                size = false;
            }
            if(size && isCompleteRun(end, start, stacks.get(sourceStack))){
                for(int i = end; i < start; i++){
                    stacks.get(sourceStack).removeCard(end);
                }
            } else {
                System.out.println("Invalid Move");
            }
        }       
    }
    
    private boolean isCompleteRun(int start, int end, Deck deck){
        if(end - start != 13){
            return false;
        } 
        for(int i = 12; i >= 0; i--){
            if(deck.getCard(start).getValue() != (i + 1)){
                return false;               
            }
            start++;
        }
        return true;
    }
    
    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        System.out.println("Stacks: ");
        for(int i = 0; i < NUM_STACKS; i++){
            for(int x = 0; x < (stacks.get(i)).size(); x++){
                if(x == (stacks.get(i)).size() - 1){
                    stacks.get(i).getCard(x).setFaceUp(true);
                }
            }
            System.out.println((i + 1) + ": " + (stacks.get(i)).toString());
        }
        System.out.println("Draw: ");
        System.out.println(drawPile.toString());
    }
}
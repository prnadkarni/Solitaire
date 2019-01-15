import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Pooja Nadkarni
 * Period 1
 * 12/16/17
 * 
 * This lab took me around an hour.
 * 
 * This program went well for me. Implementing the save and load methods was
 * fairly straightforward, and it was a good review of File reading and 
 * writing. Also, I was able to review how to use try-catch methods while
 * File reading and writing.
 * 
 */
public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();
            if (command.equals("move")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                boolean valid = true;
                String symbol = "";
                try{
                    symbol = input.next();
                } catch(InputMismatchException e){
                    System.out.println("Please enter a string of: 1-10 or"
                    + " A, J, Q, K");
                    valid = false;
                }
                int sourceStack = 0;
                try{
                    sourceStack = input.nextInt();
                } catch(InputMismatchException e){
                    System.out.println("Please enter an integer between 1 and "
                    + (NUM_STACKS) + " to represent the stack");
                    valid = false;
                }
                int destinationStack = 0;
                if(valid){
                    try{
                        destinationStack = input.nextInt();
                    } catch(InputMismatchException e){
                        System.out.println("Please enter an integer between 1 and "
                        + (NUM_STACKS) + " to represent the stack");
                        valid = false;
                    }
                }             
                if(valid){
                    board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                } else {
                    input.close();
                    input = new Scanner(System.in);
                }                     
            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                int sourceStack = 0;
                boolean valid = true;
                try{
                    sourceStack = input.nextInt();
                } catch(InputMismatchException e){
                    System.out.println("Please enter an integer between 1 and "
                    + (NUM_STACKS) + " to represent the stack");
                    valid = false;
                }
                if(valid){
                    board.clear(sourceStack - 1);
                } else {
                    input.close();
                    input = new Scanner(System.in);
                }
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            else if (command.equals("save")) {
                board.saveDeck();
            }
            else if (command.equals("load")) {
                board.restoreDeck();
            }
            else {
                System.out.println("Invalid command.");
            }
            /*
            while(input.hasNext()){
                input.next();
                System.out.println("working");
            } 
            */
            board.printBoard();
            
            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}


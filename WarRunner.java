import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class WarRunner {

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS = { "♠", "♥", "♦", "♣" };

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0 };

    public static void main(String[] args) {
        // beginningDeck is the Deck we start with. When we deal, it gets split into two
        // Decks for each player
        Deck beginningDeck = new Deck(RANKS, SUITS, POINT_VALUES);
        //beginningDeck.shuffle();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> computerHand = new ArrayList<>();

        int atmCards =  beginningDeck.getSize() / 2;
        for (int i = 0; i < atmCards; i++) {
            playerHand.add(beginningDeck.deal());
            computerHand.add(beginningDeck.deal());
        }

        // Press 'ENTER' to fight another battle or 'S' to shuffle your deck! [The user presses ENTER]
        // You drew a jack of diamonds
        // The computer drew a 2 of diamonds
        // You won 2 cards! Deck sizes: 27 (yours) vs. 25 (computer's)

        Scanner scn = new Scanner(System.in);

        // Action Loop
        while (true) {
            System.out.println("\nPress 'ENTER' to fight another battle or 'S' to shuffle your deck!");
            String userInput = scn.next();

            if (userInput == "S") {
                shuffleHand(playerHand);
                System.out.println("Your deck has been shuffled");
            } else if (userInput != "") {
                continue;
            }

            // Computer Turn
            shuffleHand(computerHand);

            //Compare
            if (playerHand.get(0).pointValue() > computerHand.get(0).pointValue()) {
                
            } else if (playerHand.get(0).pointValue() < computerHand.get(0).pointValue()) {
                
            } else {
                // 1, 2, 3, 4; I declare war

            }

            // Check if dead
            if (playerHand.size() == 0) {
                
            } else if (computerHand.size() == 0) {
                
            }
        }

        
    }

    public static void shuffleHand(ArrayList<Card> hand) {
        Random randInt = new Random();
        for (int i = hand.size() - 1; i > 0; i--) {
            int currInt = randInt.nextInt(i + 1);
            Collections.swap(hand, i, currInt);
        }
    }

    public static boolean canDeclareWar(ArrayList<Card> hand, int recursion) {
        return hand.size() >= 4*recursion;
    }

    /**
     * Assumes both hands have >= 4 cards
     * 
     * @param hand1
     * @param hand2
     * @param recursion - Must be 1
     */
    public static void declareWar(ArrayList<Card> hand1, ArrayList<Card> hand2, int recursion) {
        if (hand1.get(3).pointValue() > hand2.get(3).pointValue()) { // TODO: Change the literal value 
            for(int i = 0; i < 4*recursion; i++) hand1.add(hand2.remove(0));
        } else if (hand1.get(3).pointValue() < hand2.get(3).pointValue()) {
            for(int i = 0; i < 4*recursion; i++) hand2.add(hand1.remove(0));
        } else {
            if (!(canDeclareWar(hand1, recursion + 1))) 
            if (!(canDeclareWar(hand2, recursion + 1)))

            // Recursion AAAAAAAAAAAAAAAAAAAAAA
            declareWar(hand1, hand2, recursion + 1);
        }
    }
}
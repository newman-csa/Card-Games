import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class WarRunner {

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen",
            "King" };

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS = { "Spades", "Heart", "Diamonds", "Clubs" };

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0 };

    public static void main(String[] args) {
        // beginningDeck is the Deck we start with. When we deal, it gets split into two
        // Decks for each player
        Deck beginningDeck = new Deck(RANKS, SUITS, POINT_VALUES);
        // beginningDeck.shuffle();

        // for (int i = 0; i < beginningDeck.getCards().size(); i++) {
        // System.out.println(beginningDeck.getCards().get(i));
        // }
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> computerHand = new ArrayList<>();

        int atmCards = beginningDeck.getSize() / 2;
        for (int i = 0; i < atmCards; i++) {
            playerHand.add(beginningDeck.deal());
            computerHand.add(beginningDeck.deal());
        }

        // Press 'ENTER' to fight another battle or 'S' to shuffle your deck! [The user
        // presses ENTER]
        // You drew a jack of diamonds
        // The computer drew a 2 of diamonds
        // You won 2 cards! Deck sizes: 27 (yours) vs. 25 (computer's)

        Scanner scn = new Scanner(System.in);

        // Action Loop
        while (playerHand.size() != 0 && computerHand.size() != 0) {
            System.out.println("\nPress 'E' to fight another battle or 'S' to shuffle your deck!");
            String userInput = scn.nextLine();

            if (userInput.equals("S")) {
                shuffleHand(playerHand);
                System.out.println("Your deck has been shuffled");
            } else if (!(userInput.equals("E"))) {
                continue;
            }

            // Computer Turn
            shuffleHand(computerHand);

            // Compare
            System.out.println("\nYou drew a " + playerHand.get(0).rank() + " of " + playerHand.get(0).suit());
            System.out
                    .println("The computer drew a " + computerHand.get(0).rank() + " of " + computerHand.get(0).suit());
            if (playerHand.get(0).pointValue() > computerHand.get(0).pointValue()) {
                playerHand.add(playerHand.remove(0));
                playerHand.add(computerHand.remove(0));
                System.out.println("You won 2 cards! Deck sizes: " + playerHand.size() + " (yours) vs. "
                        + computerHand.size() + " (computer's)");
            } else if (playerHand.get(0).pointValue() < computerHand.get(0).pointValue()) {
                computerHand.add(computerHand.remove(0));
                computerHand.add(playerHand.remove(0));
                System.out.println("The computer won 2 cards! Deck sizes: " + playerHand.size() + " (yours) vs. "
                        + computerHand.size() + " (computer's)");
            } else {
                if (!(canDeclareWar(playerHand, 1))) {
                    System.out.println("The player only has " + playerHand.size() + " cards left");
                    System.out.println("Not enough to declare war, so the computer wins.");
                    break;
                } else if (!(canDeclareWar(computerHand, 1))) {
                    System.out.println("The computer only has " + computerHand.size() + " cards left");
                    System.out.println("Not enough to declare war, so the player wins.");
                    break;
                } else {
                    System.out.println("It's a tie! Battle Again!\n" + //
                            "You and the computer each lay down 3 cards.");
                    declareWar(playerHand, computerHand, 1);
                }
            }

            // Check if dead
            if (playerHand.size() == 0) {
                System.out.println("The player has no more cards,");
                System.out.println("The computer wins");
            } else if (computerHand.size() == 0) {
                System.out.println("The computer has no more cards,");
                System.out.println("The player wins");
            }
            System.out.println("-----------------------------------------------------------------");
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
        return hand.size() >= 4 * recursion;
    }

    /**
     * Assumes both hands have >= 4 cards
     * 
     * @param playerHand
     * @param computerHand
     * @param recursion    - Must be 1
     */
    public static void declareWar(ArrayList<Card> playerHand, ArrayList<Card> computerHand, int recursion) {
        int top = (recursion - 1 * 4) + 3;
        System.out.println("\nYou drew a " + playerHand.get(top).rank() + " of " + playerHand.get(top).suit());
        System.out
                .println("The computer drew a " + computerHand.get(top).rank() + " of " + computerHand.get(top).suit());
        if (playerHand.get(top).pointValue() > computerHand.get(top).pointValue()) {
            for (int i = 0; i < 4 * recursion; i++)
                playerHand.add(computerHand.remove(0));
            System.out.println(
                    "The player won " + recursion * 8 + " cards! Deck sizes: " + playerHand.size() + " (yours) vs. "
                            + computerHand.size() + " (computer's)");
        } else if (playerHand.get(top).pointValue() < computerHand.get(top).pointValue()) {
            for (int i = 0; i < 4 * recursion; i++)
                computerHand.add(playerHand.remove(0));
            System.out.println(
                    "The computer won " + recursion * 8 + " cards! Deck sizes: " + playerHand.size() + " (yours) vs. "
                            + computerHand.size() + " (computer's)");
        } else {
            if (!(canDeclareWar(playerHand, recursion + 1))) {
                System.out.println("The player only has " + playerHand.size() + " cards left");
                System.out.println("Not enough to declare war, so the computer wins.");
            } else if (!(canDeclareWar(computerHand, recursion + 1))) {
                System.out.println("The computer only has " + computerHand.size() + " cards left");
                System.out.println("Not enough to declare war, so the player wins.");
            } else { // HOW TO Recursion.exe
                System.out.println("It's a tie! Battle Again!\n" + //
                        "You and the computer each lay down 3 cards.");
                declareWar(playerHand, computerHand, recursion + 1);
            }
        }
    }
}
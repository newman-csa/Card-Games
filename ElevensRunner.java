import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// TODO: Fix Null Pointer Exceptions
public class ElevensRunner {

    static ElevensBoard game;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        game = new ElevensBoard();

        // Set New Playable Board
        game.newGame();
        while (!(game.anotherPlayIsPossible())) {
            game.newGame();
        }

        ArrayList<Integer> input = new ArrayList<>();
        while (!(game.gameIsWon()) && game.anotherPlayIsPossible()) {
            // Parse Input
            while (true) {
                printHand();
                System.out.print("Enter two or three cards to remove: ");
                input = parseInput(scn.nextLine());
                System.out.println();

                if (game.isLegal(input)) {
                    break;
                }
            }

            game.replaceSelectedCards(input);

            if (game.gameIsWon()) {
                System.out.println("Congratulations, You Win!");
                System.out.println("\\(￣ ￣*\\))");
            } else if (!(game.anotherPlayIsPossible())) {
                printHand();
                System.out.println("The board is no longer solvable");
                System.out.println("You Lose T-T");
            }
        }
    }

    public static void printHand() {
        System.out.println("There are currently " + game.deckSize() + " cards left.");
        System.out.println("  1     2     3     4     5     6     7     8     9    ");
        System.out.println(game.cardAt(0) + "   " + game.cardAt(1) + "   " + game.cardAt(2) + "   "
                + game.cardAt(3) + "   " + game.cardAt(4) + "   " + game.cardAt(5) + "   " + game.cardAt(6) + "   "
                + game.cardAt(7) + "   " + game.cardAt(8) + "   ");
        
    }

    public static ArrayList<Integer> parseInput(String input) {
        String[] strings = input.split(" ");
        int[] ints = Arrays.stream(strings)
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayList<Integer> out = new ArrayList<>(Arrays.stream(ints).map(n -> n - 1).boxed().toList());
        return out;
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ElevensRunner {
    public static void main(String[] args) {
        // chcp 65001
        Scanner scn = new Scanner(System.in);
        ElevensBoard game = new ElevensBoard();

        // Set New Playable Board
        game.newGame();
        while (!(game.anotherPlayIsPossible())) {
            game.newGame();
        }

        ArrayList<Integer> input = new ArrayList<>();
        while (!(game.gameIsWon()) && game.anotherPlayIsPossible()) {
            // Parse Input
            while (true) {
                System.out.println(" 1\t 2\t 3\t 4\t 5\t 6\t 7\t 8\t 9\t");
                System.out.println(game.cardAt(0) + "\t" + game.cardAt(1) + "\t" + game.cardAt(2) + "\t"
                        + game.cardAt(3) + "\t" + game.cardAt(4) + "\t" + game.cardAt(5) + "\t" + game.cardAt(6) + "\t"
                        + game.cardAt(7) + "\t" + game.cardAt(8) + "\t");
                System.out.print("Enter two or three cards to remove: ");
                input = parseInput(scn.nextLine());
                System.out.println();

                if (game.isLegal(input)) {
                    System.out.println("Input Broken");
                    break;
                }
            }

            game.replaceSelectedCards(input);

            if (game.gameIsWon()) {
                System.out.println("Congratulations, You Win!");
                System.out.println("\\(￣︶￣*\\))");
            } else if (!(game.anotherPlayIsPossible())) {
                System.out.println("The board if no longer solvable");
                System.out.println("You Lose T-T");
            }
        }
    }

    public static ArrayList<Integer> parseInput(String input) {
        String[] strings = input.split(" ");
        int[] ints = Arrays.stream(strings)
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.stream(ints).min().getAsInt();
        ArrayList<Integer> out = new ArrayList<>(Arrays.stream(ints).boxed().toList());
        return out;
    }
}
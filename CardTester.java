/**
 * This is a class that tests the Card class.
 */
public class CardTester {

    /**
     * The main method in this class checks the Card operations for consistency.
     * 
     * @param args is not used.
     */
    public static void main(String[] args) {
        Card aceOfSpades = new Card("Ace", "Spade", 0);
        Card queenOfHearts = new Card("Queen", "Heart", 12);
        Card fiveOfDiamonds = new Card("Five", "Diamond", 5);
        Card aceOfSpadesClone = new Card("Ace", "Spade", 0);


        System.out.println(aceOfSpades);
        System.out.println(queenOfHearts);
        System.out.println(fiveOfDiamonds);
        System.out.println(aceOfSpades.matches(aceOfSpadesClone));
        System.out.println(aceOfSpades.matches(fiveOfDiamonds));

    }
}

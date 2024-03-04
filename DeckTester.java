public class DeckTester {
    public static void main(String[] args) {
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        String[] ranks = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
                "Queen", "King", "Ace" };
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 11 };
        Deck standardDeckOf52Cards = new Deck(suits, ranks, values);
        String[] suits2 = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        String[] ranks2 = { "Kasu", "Tanzaku", "Tane", "Hikari" };
        int[] values2 = { 1, 5, 10, 20 };
        Deck sudoHanafudaDeck = new Deck(suits2, ranks2, values2);

        for (Card card : standardDeckOf52Cards.getCards()) {
            System.out.println(card);
        }

        sudoHanafudaDeck.shuffle();
        System.out.println();
        for (Card card : sudoHanafudaDeck.getCards()) {
            System.out.println(card);
        }

        
        System.out.println(sudoHanafudaDeck.getSize());
        sudoHanafudaDeck.deal();
        System.out.println(sudoHanafudaDeck.getSize());
        sudoHanafudaDeck.deal();
        System.out.println(sudoHanafudaDeck.getSize());
    }
}

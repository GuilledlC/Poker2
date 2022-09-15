import java.util.ArrayList;

public class Player {
    private String name;
    private int chips;
    private ArrayList<Card> holeCards;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        holeCards = new ArrayList<>();
    }

    public void giveChips(int chips) {
        this.chips += chips;
    }

    public int bet(int bet) {
        chips -= bet;
        return bet;
    }

    public void deal(Card holeCard1, Card holeCard2) {
        holeCards.add(holeCard1);
        holeCards.add(holeCard2);
    }

    public ArrayList<Card> showCards() {
        return new ArrayList<>(holeCards);
    }

    public ArrayList<Card> returnCards() {
        ArrayList<Card> aux = new ArrayList<>(holeCards);
        holeCards.clear();
        return aux;
    }
}

import java.util.ArrayList;

public class DeckOfCards {
	private ArrayList<Card> deck;

	DeckOfCards() {
		this.deck = new ArrayList<Card>();
		for (int suit = 0; suit < 4; suit++) {
			for (int rank = 0; rank < 13; rank++)
				deck.add(new Card(suit, rank));
		}
	}

	public Card drawFromDeck() {
		return deck.remove(0);
	}

	public int getTotalCards() {
		return deck.size();
	}

	public ArrayList<Card> returnDeck() {
		return deck;
	}

	@Override
	public String toString() {
		for (Card card : deck)
			System.out.println(card);
		return null;
	}
}

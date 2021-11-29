
public class Card {
	private int rank, suit;

	private static String[] suits = { "hearts", "spades", "clubs", "diamonds" };
	private static String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

	public static String rankAsString(int __rank) {
		return ranks[__rank];
	}

	Card(int suit, int rank) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRankValue() {
		return rank + 1;
	}

	public String getRank() {
		return ranks[rank];
	}

	public int getSuitValue() {
		return suit;
	}

	public String getSuit() {
		return suits[suit];
	}

	@Override
	public String toString() {
		return getRank() + " of " + getSuit();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card))
			return false;
		Card newCard = (Card) obj;
		return newCard.rank == rank && newCard.suit == suit;
	}
}

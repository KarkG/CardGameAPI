import java.util.ArrayList;

public class Player implements Comparable<Player> {

	private ArrayList<Card> hand;
	private int playerNumber;
	private int total;

	public Player(int playerNumber) {
		this.hand = new ArrayList<Card>();
		this.playerNumber = playerNumber;
		this.total = 0;
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public int getPlayerNumber() {
		return this.playerNumber;
	}

	public void clear() {
		this.hand.clear();
	}

	public void addCard(Card card) {
		if (card == null)
			throw new NullPointerException("Can't add a null card to a hand.");
		this.hand.add(card);
		addValueToTotal(card.getRankValue());
	}

	public void removeCard(Card card) {
		this.hand.remove(card);
		substractValueFromTotal(card.getRankValue());
	}

	public void removeCard(int position) {
		if (position < 0 || position >= this.hand.size())
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		substractValueFromTotal(this.hand.get(position).getRankValue());
		this.hand.remove(position);

	}

	private void addValueToTotal(int value) {
		this.total += value;
	}

	private void substractValueFromTotal(int value) {
		this.total -= value;
	}

	public int getCardCount() {
		return this.hand.size();
	}

	public Card getCard(int position) {
		if (position < 0 || position >= this.hand.size())
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		return this.hand.get(position);
	}

	public int getTotalValue() {
		return this.total;
	}

	public String toString() {
		System.out.println("Player" + this.playerNumber);
		if (!this.hand.isEmpty())
			for (Card card : this.hand)
				System.out.println(card);
		else
			System.out.println("No cards in hand");
		System.out.println("Total score: " + this.total);
		return "";
	}

	@Override
	public int compareTo(Player player) {
		return player.getTotalValue() - this.total;
	}

}
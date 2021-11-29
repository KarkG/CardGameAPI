import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameDeck extends DeckOfCards {
	private ArrayList<Card> shoe;
	private int numberOfDecks;

	GameDeck() {
		ArrayList<Card> shoe = new DeckOfCards().returnDeck();
		this.shoe = shoe;
		shuffle();
	}

	GameDeck(int numberOfDecks) {
		ArrayList<DeckOfCards> decks = new ArrayList<DeckOfCards>(numberOfDecks);
		for (int i = 0; i < numberOfDecks; i++) {
			decks.add(new DeckOfCards());
		}
		ArrayList<Card> shoe = new ArrayList<Card>();
		for (int i = 0; i < numberOfDecks; i++)
			shoe.addAll(decks.get(i).returnDeck());
		this.shoe = shoe;
		this.numberOfDecks = numberOfDecks;
		shuffle();
	}

	public void shuffle() {
		int index_1, index_2;
		Random generator = new Random();
		for (int i = 0; i < shoe.size(); i++) {
			index_1 = generator.nextInt(shoe.size());
			index_2 = generator.nextInt(shoe.size());

			Card card = shoe.get(index_2);
			shoe.set(index_2, shoe.get(index_1));
			shoe.set(index_1, card);
		}
	}

	public void restartGame() {
		shoe.clear();
		ArrayList<DeckOfCards> decks = new ArrayList<DeckOfCards>(numberOfDecks);
		for (int i = 0; i < numberOfDecks; i++) {
			decks.add(new DeckOfCards());
		}
		ArrayList<Card> shoe = new ArrayList<Card>();
		for (int i = 0; i < numberOfDecks; i++)
			shoe.addAll(decks.get(i).returnDeck());
		shuffle();

	}

	public void addDeck() {
		shoe.addAll(new DeckOfCards().returnDeck());
		shuffle();
	}

	public Card drawFromDeck() {
		if (!this.shoe.isEmpty())
			return shoe.remove(0);
		else {
			System.out.println("There are no more cards in the deck");
			return null;
		}
	}

	public int getTotalCards() {
		return shoe.size();
	}

	public int getRemainingCardsOfSuit(String suit) {
		int amount = 0;
		for (Card card : shoe) {
			if (card.getSuit().equals(suit))
				amount++;
		}
		return amount;
	}

	public HashMap<String, Integer> getRemainingCardsOfEverySuit() {
		String[] suits = { "hearts", "spades", "clubs", "diamonds" };
		HashMap<String, Integer> remainingCardsMap = new HashMap<String, Integer>();
		for (String suit : suits) {
			int amount = getRemainingCardsOfSuit(suit);
			remainingCardsMap.put(suit, amount);
		}
		return remainingCardsMap;
	}

	public ArrayList<Map.Entry<Card, Integer>> getCountOfAllRemainingCardsInDeck() {
		ArrayList<Map.Entry<Card, Integer>> sortedRemainingCardArrayList = new ArrayList<Map.Entry<Card, Integer>>(52);

		for (int suit = 0; suit < 4; suit++) {
			for (int rank = 12; rank >= 0; rank--) {
				Card card = new Card(suit, rank);
				int count = getRemainingCopiesOfCard(card);
				Map.Entry<Card, Integer> CardCountPair = new AbstractMap.SimpleEntry<Card, Integer>(card, count);
				sortedRemainingCardArrayList.add(CardCountPair);
			}
		}
		return sortedRemainingCardArrayList;
	}

	public int getRemainingCopiesOfCard(Card card) {
		int amount = 0;
		for (Card card2 : shoe) {
			if (card.equals(card2))
				amount++;
		}
		return amount;
	}

}

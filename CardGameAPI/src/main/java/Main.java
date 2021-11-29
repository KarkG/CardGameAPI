import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Game game = new Game(3, 3);
		Player player0 = game.getPlayer(0);
		Player player1 = game.getPlayer(1);
		Player player2 = game.getPlayer(2);
		game.dealCards(player0, 20);
		game.dealCards(player1, 20);
		game.dealCards(player2, 20);
		HashMap<String, Integer> remainingCardsMap = game.getGameDeck().getRemainingCardsOfEverySuit();
		System.out.println(remainingCardsMap);
		ArrayList<Map.Entry<Card, Integer>> sortedRemainingCardArrayList = game.getGameDeck()
				.getCountOfAllRemainingCardsInDeck();
		System.out.print(sortedRemainingCardArrayList);

		for (Map.Entry<Card, Integer> pair : sortedRemainingCardArrayList) {
			System.out.print(pair.getKey() + ": ");
			System.out.println(pair.getValue());
		}
	}
}

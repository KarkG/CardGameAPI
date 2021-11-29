import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Path;

@Path("/Game")
public class Game {
	private GameDeck gameDeck;
	private ArrayList<Player> players;

//	@GET
//	@Produces(MediaType.TEXT_XML)
//	@Produces(MediaType.APPLICATION_JSON)

	public Game(int numberOfPlayers, int numberOfDecks) {
		this.gameDeck = new GameDeck(numberOfDecks);
		ArrayList<Player> players = new ArrayList<Player>(numberOfPlayers);
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player(i));
		}
		this.players = players;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public GameDeck getGameDeck() {
		return this.gameDeck;
	}

	public void restartGame() {
		gameDeck.restartGame();
		for (Player player : players)
			player.clear();
	}

	public void dealCards(Player player, int numberOfCards) {
		for (int i = 0; i < numberOfCards; i++) {
			Card card = gameDeck.drawFromDeck();
			if (card != null)
				player.addCard(card);

		}
	}

	public Player getPlayer(int playerNumber) {
		return players.get(playerNumber);
	}

	public void addPlayer(int playerNumber) {
		this.players.add(new Player(playerNumber));
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	public void removePlayer(int num) {
		this.players.remove(num);
	}

	public void sortPlayers() {
		Collections.sort(this.players);
	}

	public ArrayList<Map.Entry<Player, Integer>> getPlayersInOrder() {
		sortPlayers();
		ArrayList<Map.Entry<Player, Integer>> sortedPlayersArrayList = new ArrayList<Map.Entry<Player, Integer>>(
				this.players.size());
		for (Player player : this.players) {
			Map.Entry<Player, Integer> playerScorePair = new AbstractMap.SimpleEntry<Player, Integer>(player,
					player.getTotalValue());
			sortedPlayersArrayList.add(playerScorePair);
		}
		return sortedPlayersArrayList;
	}

	@Override
	public String toString() {
		sortPlayers();
		for (Player player : players) {
			System.out.println("Player" + player.getPlayerNumber() + " scored " + player.getTotalValue() + " points");
		}
		return "";
	}
}
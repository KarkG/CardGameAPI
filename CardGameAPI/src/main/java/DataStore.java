import java.util.HashMap;
import java.util.Map;

public class DataStore {
	private Map<Integer, Game> games = new HashMap<Integer, Game>();
	private static DataStore instance = new DataStore();

	public static DataStore getInstance() {
		return instance;
	}

	private DataStore() {
		putGame(this.games.size());
	}

	public Game getGame(int gameNum) {
		return games.get(gameNum);
	}

	public void putGame() {
		this.games.put(this.games.size(), new Game(2, 2));
	}

	public void putGame(int gameNum) {

		this.games.put(gameNum, new Game(2, 2));
	}

	public void putGame(int gameNum, int numberOfPlayers, int numberOfDecks) {
		this.games.put(gameNum, new Game(numberOfPlayers, numberOfDecks));
	}

	public void deleteGame(int gameNum) {
		games.remove(gameNum);
	}
}

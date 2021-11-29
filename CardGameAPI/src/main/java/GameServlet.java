import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class GameServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String requestUrl = request.getRequestURI();
		System.out.println(requestUrl);

		String[] requestUrlParts = requestUrl.split("/");

		int gameNumber = Integer.parseInt(requestUrlParts[2]);
		System.out.println("gameNumber " + gameNumber);

		Game game = DataStore.getInstance().getGame(gameNumber);

		if (game == null) {
			if (requestUrlParts[3] != null && requestUrlParts[4] != null) {
				DataStore.getInstance().putGame(gameNumber, Integer.parseInt(requestUrlParts[3]),
						Integer.parseInt(requestUrlParts[4]));
			} else
				DataStore.getInstance().putGame(gameNumber);
		} else if (game != null && requestUrlParts[3] != null) {
			if (requestUrlParts[3].equals("dealCards")) {
				int numberOfCards = Integer.parseInt(requestUrlParts[4]);
				if (requestUrlParts[5].equals("allPlayers")) {
					for (Player player : game.getPlayers())
						game.dealCards(player, numberOfCards);
				} else {
					int playerNumber = Integer.parseInt(requestUrlParts[5]);
					game.dealCards(game.getPlayer(playerNumber), numberOfCards);
				}
			} else if (requestUrlParts[3].equals("getListOfCards")) {
				int playerNumber = Integer.parseInt(requestUrlParts[4]);
				ArrayList<Card> hand = game.getPlayer(playerNumber).getHand();
				String json = "{\n";
				for (Card card : hand) {
					json += card + ",\n";
				}
				json += "}";
				response.getOutputStream().println(json);

			} else if (requestUrlParts[3].equals("getPlayersInOrder")) {
				ArrayList<Map.Entry<Player, Integer>> sortedPlayersArrayList = game.getPlayersInOrder();
				String json = "{\n";
				for (Map.Entry<Player, Integer> playerScore : sortedPlayersArrayList) {
					json += "\"Player" + playerScore.getKey().getPlayerNumber() + "\":"
							+ JSONObject.quote(String.valueOf(playerScore.getValue())) + ",\n";
				}
				json += "}";
				response.getOutputStream().println(json);
			} else if (requestUrlParts[3].equals("getRemainingCardsOfEverySuit")) {
				HashMap<String, Integer> remainingCardsMap = game.getGameDeck().getRemainingCardsOfEverySuit();
				String json = "{\n";
				for (String suit : remainingCardsMap.keySet()) {
					json += "\"" + suit + "\":" + JSONObject.quote(remainingCardsMap.get(suit).toString()) + ",\n";
				}
				json += "}";
				response.getOutputStream().println(json);

			} else if (requestUrlParts[3].equals("getCountOfAllRemainingCardsInDeck")) {
				ArrayList<Map.Entry<Card, Integer>> sortedRemainingCardArrayList = game.getGameDeck()
						.getCountOfAllRemainingCardsInDeck();
				String json = "{\n";
				for (Map.Entry<Card, Integer> pair : sortedRemainingCardArrayList) {
					json += "\"" + pair.getKey() + "\":" + JSONObject.quote(String.valueOf(pair.getValue())) + ",\n";
				}
				json += "}";
				response.getOutputStream().println(json);

			} else if (requestUrlParts[3].equals("shuffle"))
				game.getGameDeck().shuffle();
			else if (requestUrlParts[3].equals("restart"))
				game.restartGame();
			else if (requestUrlParts[3].equals("addDeck"))
				game.getGameDeck().addDeck();
			else if (requestUrlParts[3].equals("addPlayer"))
				game.addPlayer(game.getPlayers().size());
			else if (requestUrlParts[3].equals("removePlayer"))
				game.removePlayer(Integer.parseInt(requestUrlParts[4]));
			else if (requestUrlParts[3].equals("endGame"))
				DataStore.getInstance().deleteGame(gameNumber);
		}

	}

}

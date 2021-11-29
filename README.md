# CardGameAPI

This server runs on http://localhost:8080/CardGameAPI/

To create a new game, put an integer after CardGameAPI/
http://localhost:8080/CardGameAPI/{gameNumber}/
ex:
http://localhost:8080/CardGameAPI/0/

To create a new game with a specific number of players and decks, put an integer seperated by "/" after CardGameAPI/
http://localhost:8080/CardGameAPI/{gameNumber}/{numberOfPlayers}/{numberOfDecks}
ex: 
http://localhost:8080/CardGameAPI/0/3/4

To deal a sepecif amount of cards to all players of a game:
http://localhost:8080/CardGameAPI/{gameNumber}/dealCards/{numberOfcards}/allPlayers

To deal a sepecif amount of cards to a specific player:
http://localhost:8080/CardGameAPI/{gameNumber}/dealCards/{numberOfcards}/{playerNumber}

To get all the cards from a specific player:
http://localhost:8080/CardGameAPI/{gameNumber}/getListOfCards/{playerNumber}

To get all players in order of their score:
http://localhost:8080/CardGameAPI/{gameNumber}/getPlayersInOrder

To get the count of remaining cards from a every suit:
http://localhost:8080/CardGameAPI/{gameNumber}/getRemainingCardsOfEverySuit

To get the count of every card left in deck:
http://localhost:8080/CardGameAPI/{gameNumber}/getCountOfAllRemainingCardsInDeck

To shuffle deck:
http://localhost:8080/CardGameAPI/{gameNumber}/shuffle

To restart game:
http://localhost:8080/CardGameAPI/{gameNumber}/restart

To add a deck to shoe:
http://localhost:8080/CardGameAPI/{gameNumber}/addDeck

To add a player to game:
http://localhost:8080/CardGameAPI/{gameNumber}/addPlayer

To remove a player to game:
http://localhost:8080/CardGameAPI/{gameNumber}/removePlayer

To end game:
http://localhost:8080/CardGameAPI/{gameNumber}/endGame

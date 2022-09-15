import network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final String PATH;
    private final Server server;
    private int pot;

    private final Deck deck;
    private ArrayList<Card> community;

    //Maybe De-Acoplate this?
    private final int MAX_PLAYERS;
    private HashMap<String, Player> players;
    private ArrayList<String> playing;
    private ArrayList<String> queue;

    //FIX THE IOEXCEPTION
    public Game(String path, int port, int max_players) throws IOException {
        this.PATH = path;

        //Throws IOException
        server = new Server(new ServerSocket(port));

        this.MAX_PLAYERS = max_players;
        pot = 0;
        deck = new Deck      ();
        queue = new ArrayList<>();
        players = new HashMap<>  ();
        community = new ArrayList<>();
    }

    public void gameLoop() {
        //Add the waiting players
        addWating();

        //All players must say they are ready
        //(There's a time limit, if it expires they leave)

        //Rework this
        while(true) {
            //Initialize the pot
            pot = 0;

            //Shuffle the deck
            deck.shuffle();

            //Deal two cards to each player IN ORDER
            for(String p : playing) //Change order?
                players.get(p).deal(deck.give(), deck.give());

            //Clockwise Pre-Flop round
            //Players must fold, match, or go all in

            //Flop: 3 community cards
            community.add(deck.give());
            community.add(deck.give());
            community.add(deck.give());

            //Second betting round
            //Turn: 1 community card
            community.add(deck.give());

            //Third betting round (double stakes)
            //River: Final community card
            community.add(deck.give());

            //Fourth betting round (double stakes)
            //Showdown
            ArrayList<String> winners = new ArrayList<>();

            //Split the pot
            int amount = pot/winners.size();
            for(String w : winners)
                players.get(w).giveChips(amount);

            //Extra chips go to the first players after the button in clockwise order
            players.get(winners.get(0)).giveChips(pot%winners.size());
        }

        //Ask the players if they want to leave or stay
        //(There's a time limit, if it expires they leave)
    }

    public static void verify() {

    }

    public Player addPlayer(String id, String name, int chips) {
        Player player = new Player(name, chips);
        players.put(id, player);
        queue.add(id);
        return player;
    }

    /**
     * Adds the players in the
     * waiting {@link Game#queue} to
     * the {@link Game#playing} list
     */
    public void addWating() {
        while(playing.size() < MAX_PLAYERS) {
            playing.add(queue.remove(0));
        }
    }
}

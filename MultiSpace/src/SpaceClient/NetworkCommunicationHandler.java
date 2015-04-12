package SpaceClient;

import game.GamePlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import SpaceClient.Board.GameState;

/**
 * This class controls the communication to the server from the client.
 * 
 * @author Kyle Kornetzke
 * 
 */
public class NetworkCommunicationHandler extends Thread {
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private String serverAddress;
	private int port;
	private boolean gameReady = false;

	public NetworkCommunicationHandler(String string) {
		String[] input = string.split(":");
		if (input[0].compareTo("Server IP") == 0) {
			serverAddress = "localhost";
		} else
			serverAddress = input[0];
		if (input.length > 1)
			port = Integer.parseInt(input[1]);
		else
			port = 9001;
	}

	public NetworkCommunicationHandler() {

	}

	/**
	 * Services this thread's client by repeatedly requesting a screen name
	 * until a unique one has been submitted, then acknowledges the name and
	 * registers the output stream for the client in a global set, then
	 * repeatedly gets inputs and broadcasts them.
	 */

	public void sendToServer(String out) {
		if (gameReady)
			this.out.println(out);
	}

	public void run() {

		// Make connection and initialize streams
		socket = null;
		try {
			socket = new Socket(serverAddress, port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

		} catch (UnknownHostException e) {
			System.out.println("UnknownHost");

			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO----Exception");
			Board.gameStatus = Board.GameState.STARTMENU;
			return;
		}

		// Process all messages from server, according to the protocol.
		while (true) { // Connection to the server while loop
			String input = null;
			try {
				input = in.readLine();
			} catch (IOException e) {
				System.out.println("Connection to server lost");
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			} catch (NullPointerException e) {
				System.out.println("NullPointer");
			}

			if (input == null) {
				System.out.println("You have been disconnected.. line==null");
				break;
			}
			if (input.startsWith("SUBMITNAME")) {
				out.println(GamePlay.getPlayer().getName());

			} else if (input.startsWith("NEEDSHIP")) {
				out.println(GamePlay.getPlayer().x + ":"
						+ GamePlay.getPlayer().y + ":"
						+ GamePlay.getPlayer().getDirection() + ":"
						+ GamePlay.getPlayer().getImageNumber());

			} else if (input.startsWith("PLANET")) {
				String[] inArray = input.split(":");
				GamePlay.getSpaceList().add(
						new SpaceJunk(Integer.parseInt(inArray[3]), Double
								.parseDouble(inArray[1]), Double
								.parseDouble(inArray[2])));

			} else if (input.startsWith("STARTGAME")) {
				gameReady = true;
				Board.gameStatus = GameState.GAME;
				break; // breaks out of the while loop once connection has
						// been successful
			} else if (input.startsWith("NAMEREJECTED")) {
				Board.modifyMenu.nameReject();
			}

		} // end of while loop for connection information

		while (true) { // while loop for the rest of the communication with
						// the server
			String input;
			try {
				input = in.readLine();
			} catch (IOException e) {
				System.out.println("Connection to server lost");
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}

			if (input == null) {
				System.out.println("You have been disconnected.. line==null");
				break;
			}
			if (input.startsWith("DISCONNECTED")) {
				gameReady = false;
				System.out.println("You have been disconnected");
				Board.gameStatus = Board.GameState.STARTMENU;
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			} else if (input.startsWith("AddShip")) {
				String[] inArray = input.split(":");

				GamePlay.getPlayerHashTable().put(
						inArray[1],
						new SpaceShip(Integer.parseInt(inArray[5]), inArray[1],
								Double.parseDouble(inArray[2]), Double
										.parseDouble(inArray[3]), Integer
										.parseInt(inArray[4])));

				System.out.println("Ship added! " + inArray[1] + " "
						+ inArray[2] + " " + inArray[3] + " " + inArray[4]);

			} // end of Add:Player
			
			else if (input.startsWith("RemovePlayer")) {
				String[] inArray = input.split(":");

				synchronized(GamePlay.getPlayerHashTable()){
				GamePlay.getPlayerHashTable().remove(inArray[1]);
				}

				System.out.println("Ship Removed! " + inArray[1]);

			} // End of Remove:PlayerName	
			
			else // end of "Add"

			if (input.startsWith("Change")) {
				String[] inArray = input.split(":");
				if (inArray[1].compareTo(GamePlay.getPlayer().getName()) != 0) {
					synchronized (GamePlay.getPlayerHashTable()) {
						GamePlay.getPlayerHashTable()
								.get(inArray[1])
								.setPlacement(Double.parseDouble(inArray[2]),
										Double.parseDouble(inArray[3]),
										Integer.parseInt(inArray[4]));
					}
				}
			}// end of "Change"

			else if (input.startsWith("AddMissile")) {
				String[] inArray = input.split(":");
				if (inArray[1].compareTo(GamePlay.getPlayer().getName()) != 0) {
					synchronized (GamePlay.getPlayerHashTable()) {
						GamePlay.getPlayerHashTable()
								.get(inArray[1])
								.fire(Double.parseDouble(inArray[2]),
										Double.parseDouble(inArray[3]),
										Integer.parseInt(inArray[4]),
										inArray[5]);
					}
				}
			}// end of "AddMissile"
			else if (input.startsWith("RemoveMissile")) {
				String[] inArray = input.split(":");
				String[] subArray = inArray[1].split("_");
				synchronized (GamePlay.getPlayerHashTable()) {
				
					if (GamePlay.getPlayer().getName().compareTo(subArray[0]) == 0) {
						GamePlay.getPlayer().removeMissile(inArray[1]);
					} else
						GamePlay.getPlayerHashTable().get(subArray[0])
								.removeMissile(inArray[1]);
				}

			}// end of "RemoveMissile"
			else if (input.startsWith("Gamestate")) {
				String[] inArray = input.split(":");
				GamePlay.getPlayerHashTable().get(inArray[1])
						.setClientState(inArray[2]);
			} else if (input.startsWith("Dead")) {
				String[] inArray = input.split(":");

				if (inArray[1].compareTo(GamePlay.getPlayer().getName()) != 0) {
					synchronized (GamePlay.getPlayerHashTable()) {
						GamePlay.getPlayerHashTable().get(inArray[1])
								.setShipStatus("Dead");
					}
				}
			} else if (input.startsWith("Alive")) {
				String[] inArray = input.split(":");

				if (inArray[1].compareTo(GamePlay.getPlayer().getName()) != 0) {
					synchronized (GamePlay.getPlayerHashTable()) {
						GamePlay.getPlayerHashTable().get(inArray[1])
								.setShipStatus("Alive");
					}
				}
			}

		} // end of while loop for server communication
	} // end of run();

	/**
	 * This returns true when the handler object has completed the basic
	 * communication with the server
	 * 
	 * @return true if ready for other communiction
	 * @return fals
	 */
	public boolean isGameReady() {
		return gameReady;
	}

} // end of Handler class;

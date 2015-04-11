package SpaceServer;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import SpaceClient.SpaceShip;
import SpaceClient.SpaceJunk;

/**
 * 
 * @author Tom Zachary and Kyle Kornetzke
 * 
 */

public class SpaceServer {

	/**
	 * The port that the server listens on.
	 */
	private static final int PORT = 9001;

	/**
	 * The set of all names of clients connected to the server. Maintained so
	 * that we can check that new clients are not registering name already in
	 * use.
	 */
	private static Hashtable<String, SpaceShip> shipHash = new Hashtable<String, SpaceShip>();

	private static LinkedList<SpaceJunk> planets = new LinkedList<SpaceJunk>();
	/**
	 * The set of all the print writers for all the clients. This set is kept so
	 * we can easily broadcast messages.
	 */
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	private static Vector<Handler> HandlerVector = new Vector<Handler>();

	/**
	 * The application main method, which just listens on a port and spawns
	 * handler threads.
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("The Space server is running.");
		ServerSocket listener = new ServerSocket(PORT);

		new GUI();
		createLevel();

		try {
			while (true) {
				HandlerVector.add(new Handler(listener.accept()));
				HandlerVector.lastElement().start();
			}
		} finally {
			listener.close();
		}
	}

	/**
	 * The create level method reads in the level from a xml file. This file
	 * consists of planets and their location/picture.
	 * 
	 **/
	private static void createLevel() {
		try {
			File levelsXML = new File("src/SpaceServer/level0.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(levelsXML);
			doc.getDocumentElement().normalize();
			System.out.println("ROOT: "
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Planet");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					int xLocation = Integer.parseInt(eElement
							.getElementsByTagName("xLocation").item(0)
							.getTextContent());
					int yLocation = Integer.parseInt(eElement
							.getElementsByTagName("yLocation").item(0)
							.getTextContent());
					int picture = Integer.parseInt(eElement
							.getElementsByTagName("picture").item(0)
							.getTextContent());
					planets.add(new SpaceJunk(picture, xLocation, yLocation));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method sets up the graphical user interface used by the server.
	 * 
	 * 
	 */
	private static class GUI {
		private JFrame frame;
		private JTextField textField;
		private JTextArea leftMessageArea;
		private JTextArea rightMessageArea;
		private Timer timer;

		public GUI() {
			try {
				frame = new JFrame("Space Server - "
						+ InetAddress.getLocalHost());
			} catch (HeadlessException e1) {

				e1.printStackTrace();
			} catch (UnknownHostException e1) {

				e1.printStackTrace();
			}
			frame.setLocation(500, 500);
			textField = new JTextField(40);
			leftMessageArea = new JTextArea(8, 20);
			rightMessageArea = new JTextArea(8, 30);

			textField.setEditable(true);
			leftMessageArea.setEditable(false);
			frame.getContentPane().add(textField, "North");
			frame.getContentPane()
					.add(new JScrollPane(leftMessageArea), "West");
			frame.getContentPane().add(new JScrollPane(rightMessageArea),
					"East");

			textField.addActionListener(new ActionListener() {
				/**
				 * Responds to pressing the enter key in the textfield by
				 * sending the contents of the text field to the server. Then
				 * clear the text area in preparation for the next message.
				 */

				public void actionPerformed(ActionEvent e) {
					String in = textField.getText();
					if (in.compareToIgnoreCase("change level") == 0) {

					}
					if (in.compareTo("kill all") == 0) {

						textField.setText("");
						if (HandlerVector.isEmpty() == false) {

							for (int i = 0; i < HandlerVector.size(); i++) {
								HandlerVector.get(i).out
										.println("DISCONNECTED");

								HandlerVector.get(i).CloseConnection();

								message("Client Closed: "
										+ HandlerVector.get(i).getHandlerName());
							}
							HandlerVector.clear();
							if (HandlerVector.isEmpty())
								message("Removed all Clients");

						} else
							message("No clients to kill");
					} // end in.compareTo

					else if (in.compareTo("clear") == 0) {
						textField.setText("");
						leftMessageArea.setText("");
					}

				} // end actionPerformed
			});
			timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateClients();
				}
			});

			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			timer.start();
		}

		/**
		 * This method updates the GUI to present the current action of the
		 * related client
		 * 
		 */
		public void updateClients() {
			String names = "";
			String left = "";
			for (Handler x : HandlerVector) {
				names = names + x.toString() + "\n";
				left = left + x.getMessage() + "\n";
			}
			rightMessageArea.setText(names);
			leftMessageArea.setText(left);
		}

		public void message(String message) {
			leftMessageArea.setText(message + "\n" + leftMessageArea.getText());
		}

	}

	/**
	 * A handler thread class. Handlers are spawned from the listening loop and
	 * are responsible for a dealing with a single client and broadcasting its
	 * messages.
	 * 
	 */
	private static class Handler extends Thread {

		private SpaceShip player;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private String leftMessage = "";

		/**
		 * Constructs a handler thread for each client.
		 * 
		 */
		public Handler(Socket socket) {
			this.socket = socket;
			this.player = new SpaceShip(0, 0, 0);
		}

		/**
		 * Receives the messages from the handler and returns the message saved
		 * in the clients.
		 * 
		 * @return the message
		 */
		public String getMessage() {
			String temp = leftMessage;
			leftMessage = "Blank";
			return temp;
		}

		/**
		 * Closes the client's socket.
		 * 
		 */
		public void CloseConnection() {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Closing did not work");
			}
		}

		/**
		 * Returns the client's name
		 * 
		 * @return client's name
		 */
		public String getHandlerName() {
			return player.getName();
		}

		/**
		 * Returns the handler is the form of a string.
		 * 
		 * @return string of handler
		 */
		public String toString() {

			if (socket.isClosed())
				return player.toString() + "	Disconnected!";
			else
				return player.toString() + "	Connected"
						+ socket.getInetAddress();

		}

		/**
		 * Communicates with the client in order to get information of the
		 * client status and maintains communications.
		 * 
		 */
		public void run() {
			try {

				// Create character streams for the socket.
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// Request a name from this client. Keep requesting until
				// a name is submitted that is not already used. Note that
				// checking for the existence of a name and adding the name
				// must be done while locking the set of names.
				while (true) {

					out.println("SUBMITNAME");
					String name = in.readLine();
					if (shipHash.containsKey(name)) {
						out.println("NAMEREJECTED");

					} else {
						player.setName(name);
						shipHash.put(player.getName(), player);
						out.println("NAMEACCEPTED");
						break;

					}
				}

				// Now that a successful name has been chosen, add the
				// socket's print writer to the set of all writers so
				// this client can receive broadcast messages.

				while (true) {
					out.println("NEEDSHIP");
					String coords = in.readLine();
					if (coords == null)
						return;

					String[] inArray = coords.split(":");

					player.setPlacement(Double.parseDouble(inArray[0]),
							Double.parseDouble(inArray[1]),
							Integer.parseInt(inArray[2]));
					player.setImage(Integer.parseInt(inArray[3]));
					break;

				}
				out.println("COORDSACCEPTED");
				for (int i = 0; i < planets.size(); i++) {
					out.println("PLANET:" + planets.get(i).getX() + ":"
							+ planets.get(i).getY() + ":"
							+ planets.get(i).getImageNumber());
				}
				out.println("STARTGAME");

				for (PrintWriter writer : writers) {
					writer.println("AddShip:" + player.getName() + ":"
							+ player.getX() + ":" + player.getY() + ":"
							+ player.getDirection() + ":"
							+ player.getImageNumber());
					System.out.println("Adding new player");
				}

				for (SpaceShip e : shipHash.values()) {
					if (e.getName().compareTo(player.getName()) != 0)
						out.println("AddShip:" + e.getName() + ":" + e.getX()
								+ ":" + e.getY() + ":" + e.getDirection() + ":"
								+ e.getImageNumber());
				}

				writers.add(out);
				// Accept messages from this client and broadcast them.
				// Ignore other clients that cannot be broadcasted to.
				while (true) {
					int count = 0;
					String input = in.readLine();
					if (input == null) {
						return;
					}

					/*
					 * Does change here depending on what the input string
					 * contains
					 */

					if (input.startsWith("Move")) {

						if (leftMessage.contains("Move") == false)
							leftMessage = leftMessage + "Move " + count;
						count++;
						String[] inArray = input.split(":");

						shipHash.get(inArray[1]).setX(
								(int) Double.parseDouble(inArray[2]));
						shipHash.get(inArray[1]).setY(
								(int) Double.parseDouble(inArray[3]));
						shipHash.get(inArray[1]).setDirection(
								Integer.parseInt(inArray[4]));

						for (PrintWriter writer : writers) {
							writer.println("Change:" + inArray[1] + ":"
									+ inArray[2] + ":" + inArray[3] + ":"
									+ inArray[4]);
						}

					}
					if (input.startsWith("Gamestate")) {
						for (PrintWriter writer : writers)
							writer.println(input);
					}
					if (input.startsWith("Dead")) {
						String[] inArray = input.split(":");
						shipHash.get(inArray[1]).setShipStatus("Dead");
						for (PrintWriter writer : writers) {
							writer.println(input);
						}
					}
					if (input.startsWith("Alive")) {
						String[] inArray = input.split(":");
						shipHash.get(inArray[1]).setShipStatus("Alive");
						for (PrintWriter writer : writers) {
							writer.println(input);
						}
					}
					if (input.startsWith("Dead")) {
						String[] inArray = input.split(":");
						shipHash.get(inArray[1]).setShipStatus("Dead");
					}
					if (input.startsWith("AddMissile")) {
						if (leftMessage.contains("Fire") == false)
							leftMessage += "Fire ";
						for (PrintWriter writer : writers) {
							writer.println(input);
						}
					}
					if (input.startsWith("RemoveMissile")) {
						if (leftMessage.contains("Hit") == false)
							leftMessage += "Hit ";
						for (PrintWriter writer : writers) {
							writer.println(input);
						}
					}

					if (input.startsWith("Dead")) {

						for (PrintWriter writer : writers) {
							writer.println("Death:" + player.getName());
						}

					}

					/*
					 * after changing were discovered, send update to other
					 * players (writers)
					 */
				}
			} catch (IOException e) {
				System.out.println(e);
			} finally {
				// This client is going down! Remove its name and its print
				// writer from the sets, and close its socket.
				if (player != null) {
					shipHash.remove(player.getName());
				}
				if (out != null) {
					writers.remove(out);
				}
				try {
					socket.close();
				} catch (IOException e) {
				} finally {
				}
			}
		}
	}
}
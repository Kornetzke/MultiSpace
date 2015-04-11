package generator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class is used to generate the levels used by the server. It generates
 * the levels and saves them to an xml file.
 * 
 * @author Tom Zachary
 * 
 */
public class Generator {
	/**
	 * This uses a DOM structure to create a new xml file that has each planet
	 * in the game along with a randomly generated: xLocation - x coordinate on
	 * the game board yLocation - y coordinate on the game board picture -
	 * random number from 3-7. When sent to the clients, this gets read and the
	 * picture is picked through a list.
	 */

	public static void main(String[] args) {
		int level = 0;
		while (level < 5) {
			try {
				// These commands set up the XML writer.
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				// This sets the root element of the XML file to Level
				Element rootElement = doc.createElement("Level");
				// This adds the Level element to the document
				doc.appendChild(rootElement);
				// Loops through 1000 times for 1000 planets.
				for (int i = 0; i < 1000; i++) {
					// This creates the element Planet and adds it to the
					Element planet = doc.createElement("Planet");
					rootElement.appendChild(planet);
					// Planet ID: This is the ID of the planets, starting at 1.
					Attr attr = doc.createAttribute("id");
					attr.setValue("" + (i + 1));
					planet.setAttributeNode(attr);
					// xLocation: This randomly generates the x coordinate of
					// the planet. The if statement is used to randomly pick a
					// negative or positive number.
					if ((Math.random() * 300) % 10 <= 5) {
						Element xLocation = doc.createElement("xLocation");
						xLocation.appendChild(doc.createTextNode(""
								+ (int) (Math.random() * 50000)));
						planet.appendChild(xLocation);
					} else {
						Element xLocation = doc.createElement("xLocation");
						xLocation.appendChild(doc.createTextNode(""
								+ (int) -(Math.random() * 50000)));
						planet.appendChild(xLocation);
					}

					// yLocation: This randomly generates the y coordinate of
					// the planet. The if statement is used to randomly pick a
					// negative or positive number.
					if ((Math.random() * 300) % 10 > 6) {
						Element yLocation = doc.createElement("yLocation");
						yLocation.appendChild(doc.createTextNode(""
								+ (int) (Math.random() * 50000)));
						planet.appendChild(yLocation);
					} else {
						Element yLocation = doc.createElement("yLocation");
						yLocation.appendChild(doc.createTextNode(""
								+ (int) -(Math.random() * 50000)));
						planet.appendChild(yLocation);
					}
					// PlanetPicture: This generates a random number from 3 to
					// 7, which when sent to the client has a picture meaning.
					Element picture = doc.createElement("picture");
					picture.appendChild(doc.createTextNode(""
							+ (int) ((Math.random() * 5) + 3)));
					planet.appendChild(picture);
				}

				// These commands write the xml to the SpaceServer package,
				// which there the server can use.
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(
						"src\\SpaceServer\\level" + level++ + ".xml"));
				transformer.transform(source, result);
				System.out.println("LEVELS DONE!");
			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}

		}
	}
}

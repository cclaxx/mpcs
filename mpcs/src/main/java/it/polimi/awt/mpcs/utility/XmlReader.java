package it.polimi.awt.mpcs.utility;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import org.w3c.dom.*;

//metodo per aprire un file xml
public class XmlReader {
	public Document readFromXml(String percorso) {
		try {
			// prova ad aprire il file

			File file = new File(percorso);
			DocumentBuilderFactory costruttore = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder creadocumento = costruttore.newDocumentBuilder();
			Document doc = creadocumento.parse(file);

			return doc;
		}

		catch (Exception e) {
			// Errore in caso di fallimento nell'apertura
			System.out.printf("impossibile aprire il file");
		}
		return null;
	}

	public void getQueriesValues() {

		Document doc = readFromXml(getPathRisorsa("Alps.xml"));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Photo");
		 
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
	 
				System.out.println( eElement.getElementsByTagName("Lat_dec").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Long_dec").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Elevation").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Name").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Prom").item(0).getTextContent());
	 
			}
		}
	}

	public static String getPathRisorsa(String nomeRisorsa) {
		File risorsa = new File("src\\main\\resources\\" + nomeRisorsa);
		return risorsa.getPath();
	}
}

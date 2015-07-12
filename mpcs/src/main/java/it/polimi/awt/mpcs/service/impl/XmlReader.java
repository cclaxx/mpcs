package it.polimi.awt.mpcs.service.impl;

import it.polimi.awt.mpcs.domain.SeedQuery;
import it.polimi.awt.mpcs.repository.SeedQueryRepository;
import it.polimi.awt.mpcs.service.XMLReaderService;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

@Service
//metodo per aprire un file xml
public class XmlReader implements XMLReaderService {
	
	@Autowired
	ServletContext servletContext;
	
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

	public List<SeedQuery> readSeedQueries() {

		List<SeedQuery> queries = new ArrayList<SeedQuery>();
		
		Document doc = readFromXml(getPathRisorsa("Alps.xml"));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Photo");
		 
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
			SeedQuery query = new SeedQuery();
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				
				query.setLat(eElement.getElementsByTagName("Lat_dec").item(0).getTextContent());
				query.setLng(eElement.getElementsByTagName("Long_dec").item(0).getTextContent());
				query.setElev(Integer.parseInt(eElement.getElementsByTagName("Elevation").item(0).getTextContent()));
				query.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
				query.setProm(Integer.parseInt(eElement.getElementsByTagName("Prom").item(0).getTextContent()));
				
				queries.add(query);
				
				System.out.println( eElement.getElementsByTagName("Lat_dec").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Long_dec").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Elevation").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Name").item(0).getTextContent());
				System.out.println( eElement.getElementsByTagName("Prom").item(0).getTextContent());
	 
			}
		}
		return queries;
	}

	public String getPathRisorsa(String nomeRisorsa) {
		
		File risorsa = new File( servletContext.getRealPath("/resources/") + nomeRisorsa );
		return risorsa.getPath();
	}
}

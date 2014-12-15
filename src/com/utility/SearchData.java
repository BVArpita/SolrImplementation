package com.utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SearchData {
	public static List<com.bean.Document> getData(String url) {
		List<com.bean.Document> docList = new ArrayList<com.bean.Document>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc = db.parse(new URL(url).openStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			doc.getDocumentElement().normalize();

			

			NodeList nList = doc.getElementsByTagName("doc");

			

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					com.bean.Document document = new com.bean.Document();
					document.setId(Integer.parseInt(eElement
							.getElementsByTagName("int").item(0)
							.getTextContent()));

					// System.out.println(eElement.getElementsByTagName("int")
					// .item(0).getTextContent());
					NodeList nListIn = eElement.getElementsByTagName("str");
					// System.out.println("l : " + nListIn.getLength());
					for (int tempa = 0; tempa < nListIn.getLength(); tempa++) {
						Node nNodea = nListIn.item(tempa);
						Element eElementa = (Element) nNodea;

						// System.out.println(eElementa.getAttribute("name"));

						if (eElementa.getAttribute("name").equals(
								"bodyleadparagraph")) {
							document.setBodyLeadParagraph(eElementa
									.getTextContent());
						}

						if (eElementa.getAttribute("name").equals(
								"bodyfulltext")) {
							document.setBodyFullText(eElementa.getTextContent());
						}

						if (eElementa.getAttribute("name").equals("title")) {
							document.setTitle(eElementa.getTextContent());
						}
						
					}

					docList.add(document);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docList;
	}

}

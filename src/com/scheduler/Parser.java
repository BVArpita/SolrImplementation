package com.scheduler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.bean.Document;
import com.exception.ParserException;

public class Parser {

	public static ArrayList<Document> docParser(String ipDir) {
		Document document = null;
		ArrayList<Document> list = new ArrayList<Document>();
		File ipDirectory = new File(ipDir);
		String[] catDirectories = ipDirectory.list();
		try {
			for (String file : catDirectories) {
				String filePath = ipDirectory.getAbsolutePath()
						+ File.separator + file;
				document = parse(filePath);
				if (document != null)
				 list.add(document);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}

	public static Document parse(String filePath) throws ParserException,
			IOException {
		Document document = new Document();
		String fileContent = "";
		String line = null;
		if (filePath == null)
			throw new ParserException();
		File f = new File(filePath);
		if (f.isFile()) {
			FileInputStream in = new FileInputStream(f);
			DataInputStream data = new DataInputStream(in);
			BufferedReader br = new BufferedReader(new InputStreamReader(data));
			line = br.readLine();
			try {
				while (line != null) {
					fileContent = fileContent + line;
					if (line.contains("<title>") && line.contains("</title>")) {
						line = line.replace("<title>", "")
								.replace("</title>", "").trim();
						document.setTitle(line);

					} else if (line.contains("<person")
							&& line.contains("</person>")) {
						line = line.replace("</person>", "").trim();
						int i = line.indexOf(">");
						line = line.substring(i + 1, line.length()).trim();
						document.setDocIndexingService(line);
					} else if (line.contains("<classifier")
							&& line.contains("</classifier>")
							&& line.contains("types_of_material")) {
						line = line.replace("</classifier>", "").trim();
						int i = line.indexOf(">");
						line = line.substring(i + 1, line.length()).trim();

						document.setTypesOFMaterial(line);
					} else if (line.contains("<classifier")
							&& line.contains("</classifier>")
							&& line.contains("taxonomic_classifier")) {
						line = line.replace("</classifier>", "").trim();
						int i = line.indexOf(">");
						line = line.substring(i + 1, line.length()).trim();

						document.setTaxonomicClassifier(line);
					} else if (line.contains("<hl1>")
							&& line.contains("</hl1>")) {
						line = line.replace("<hl1>", "").replace("</hl1>", "")
								.trim();
						document.setBodyHedLine(line);

					} else if (line.contains("lead_paragraph")) {
						line = br.readLine();
						if (line.contains("<p>") && line.contains("</p>")) {
							line = line.replace("<p>", "").replace("</p>", "")
									.trim();
							document.setBodyLeadParagraph(line);
						}
					} else if (line.contains("full_text")) {
						line = br.readLine();
						if (line.contains("<p>") && line.contains("</p>")) {
							line = line.replace("<p>", "").replace("</p>", "")
									.trim();
							document.setBodyFullText(line);
						}
					}
					line = br.readLine();
				}
				document.setFileContent(fileContent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return document;
	}

}

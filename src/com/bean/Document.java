package com.bean;

public class Document extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4261050749978570537L;
	private String title;//
	private String docIndexingService;
	private String typesOFMaterial;
	private String taxonomicClassifier;
	private String bodyHedLine;
	private String bodyLeadParagraph;//
	private String bodyFullText;//
	private String fileContent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getTypesOFMaterial() {
		return typesOFMaterial;
	}

	public void setTypesOFMaterial(String typesOFMaterial) {
		this.typesOFMaterial = typesOFMaterial;
	}

	public String getTaxonomicClassifier() {
		return taxonomicClassifier;
	}

	public void setTaxonomicClassifier(String taxonomicClassifier) {
		this.taxonomicClassifier = taxonomicClassifier;
	}

	public String getBodyHedLine() {
		return bodyHedLine;
	}

	public void setBodyHedLine(String bodyHedLine) {
		this.bodyHedLine = bodyHedLine;
	}

	public String getBodyLeadParagraph() {
		return bodyLeadParagraph;
	}

	public void setBodyLeadParagraph(String bodyLeadParagraph) {
		this.bodyLeadParagraph = bodyLeadParagraph;
	}

	public String getBodyFullText() {
		return bodyFullText;
	}

	public void setBodyFullText(String bodyFullText) {
		this.bodyFullText = bodyFullText;
	}

	public String getDocIndexingService() {
		return docIndexingService;
	}

	public void setDocIndexingService(String docIndexingService) {
		this.docIndexingService = docIndexingService;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	
}

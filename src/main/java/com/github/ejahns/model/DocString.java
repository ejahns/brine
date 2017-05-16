package com.github.ejahns.model;

import com.github.ejahns.model.interfaces.GherkinElement;

public class DocString implements GherkinElement {

	private String content;
	private String contentType;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}

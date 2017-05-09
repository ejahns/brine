package com.github.ejahns.model.interfaces.haselement;

import java.util.List;

import com.github.ejahns.model.interfaces.GherkinElement;

public interface HasTags extends GherkinElement {

	public List<String> getTags();

	public void setTags(List<String> tags);
}

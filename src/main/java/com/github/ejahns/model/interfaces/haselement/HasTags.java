package com.github.ejahns.model.interfaces.haselement;

import java.util.List;

import com.github.ejahns.model.interfaces.GherkinElement;

public interface HasTags extends GherkinElement {

	List<String> getTags();

	void setTags(List<String> tags);
}

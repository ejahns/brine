package com.github.ejahns.model.interfaces.hastoken;

import java.util.List;

import com.github.ejahns.model.interfaces.GherkinElement;

public interface HasDescription extends GherkinElement {

	List<String> getDescription();

	void setDescription(List<String> description);
}

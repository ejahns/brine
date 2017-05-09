package com.github.ejahns.model.interfaces.haselement;

import java.util.List;

import com.github.ejahns.model.Step;
import com.github.ejahns.model.interfaces.GherkinElement;

public interface HasSteps extends GherkinElement {

	List<Step> getSteps();

	void setSteps(List<Step> steps);
}

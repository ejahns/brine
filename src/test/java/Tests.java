import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.github.ejahns.ParserException;
import com.github.ejahns.PickleJar;
import com.github.ejahns.model.Feature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class Tests {

	@Test
	public void fileFeature() throws IOException {
		File file = new File("src/test/resources/sample_feature.feature");
		Feature ferment = PickleJar.cure(file);
		JsonReader reader = new JsonReader(new FileReader("src/test/resources/sample_feature.json"));
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement parse1 = jsonParser.parse(reader);
		Assert.assertEquals(parse1, gson.toJsonTree(ferment));
	}

	@Test
	public void badFeature() throws IOException {
		File file = new File("src/test/resources/sample_feature_bad.feature");
		try {
			Feature ferment = PickleJar.cure(file);
		}
		catch (ParserException.UnexpectedTokenException e) {
			Assert.assertEquals(e.getMessage(), "Expected one of EOF, StepLine, TagLine, ScenarioLine, ScenarioOutlineLine, Other but got TagLine: @This doesn't go here!!! at line 12");
			return;
		}
		throw new IllegalStateException();
	}
}

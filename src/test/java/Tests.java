import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.github.ejahns.ParserException;
import com.github.ejahns.PickleJar;
import com.github.ejahns.model.Feature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import static org.junit.Assert.*;

public class Tests {

	@Test
	public void fileFeature() throws IOException {
		assertFeatureMatchesJson(
			"src/test/resources/sample_feature.feature",
			"src/test/resources/sample_feature.json"
		);
	}

	@Test
	public void badFeature() throws IOException {
		File file = new File("src/test/resources/sample_feature_bad.feature");
		try {
			PickleJar.cure(file);
		}
		catch (ParserException.UnexpectedTokenException e) {
			assertEquals(e.getMessage(), "Expected one of EOF, StepLine, TagLine, ScenarioLine, ScenarioOutlineLine, Other but got TagLine: @This doesn't go here!!! at line 12");
			return;
		}
		throw new IllegalStateException();
	}

	@Test
	public void dataTable() throws FileNotFoundException {
		assertFeatureMatchesJson(
			"src/test/resources/data_table.feature",
			"src/test/resources/data_table.json"
		);
	}

	private void assertFeatureMatchesJson(String feature, String json) throws FileNotFoundException {
		Feature cure = PickleJar.cure(new File(feature));
		JsonReader jsonReader = new JsonReader(new FileReader(json));
		JsonElement jsonString = new JsonParser().parse(jsonReader);
		assertEquals(new Gson().toJsonTree(cure), jsonString);
	}
}

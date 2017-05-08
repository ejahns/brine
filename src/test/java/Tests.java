import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

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
	public void multipleUnexpectedTokens() throws IOException {
		assertFeatureGiversErrors(
			"src/test/resources/multiple_unexpected_tokens.feature",
			"src/test/resources/multiple_unexpected_tokens.errors"
			);
	}

	@Test
	public void badToken() throws IOException {
		assertFeatureGiversErrors(
			"src/test/resources/bad_token.feature",
			"src/test/resources/bad_token.errors"
		);
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
		cure.setAbsoluteLocation(null);
		JsonReader jsonReader = new JsonReader(new FileReader(json));
		JsonElement jsonString = new JsonParser().parse(jsonReader);
		assertEquals(new Gson().toJsonTree(cure), jsonString);
	}

	private void assertFeatureGiversErrors(String feature, String errors) throws FileNotFoundException {
		ArrayList<String> actualErrorList = new ArrayList<>();
		PickleJar.cureCollectErrors(new File(feature), actualErrorList);
		FileReader fileReader = new FileReader(errors);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> expectedErrorList = bufferedReader.lines().collect(Collectors.toList());
		assertEquals(expectedErrorList, actualErrorList);
	}
}

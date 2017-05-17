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
	public void validFeatures() throws IOException {
		File[] files = new File("src/test/resources/good").listFiles((File f) -> f.getName().endsWith(".feature"));
		for (File f : files) {
			File json = new File(f.getAbsolutePath().replaceAll("\\.feature", "\\.json"));
			assertFeatureMatchesJson(f, json);
		}
	}

	@Test
	public void invalidFeatures() throws IOException {
		File[] files = new File("src/test/resources/bad").listFiles((f, name) -> name.endsWith(".feature"));
		for (File f : files) {
			File errors = new File(f.getAbsolutePath().replaceAll("\\.feature", "\\.errors"));
			assertFeatureGivesErrors(f, errors);
		}
	}

//	@Test
//	public void fileFeature() throws IOException {
//		assertFeatureMatchesJson(
//			"src/test/resources/sample_feature.feature",
//			"src/test/resources/sample_feature.json"
//		);
//	}
//
//	@Test
//	public void multipleUnexpectedTokens() throws IOException {
//		assertFeatureGiversErrors(
//			"src/test/resources/multiple_unexpected_tokens.feature",
//			"src/test/resources/multiple_unexpected_tokens.errors"
//		);
//	}
//
//	@Test
//	public void badToken() throws IOException {
//		assertFeatureGiversErrors(
//			"src/test/resources/bad_token.feature",
//			"src/test/resources/bad_token.errors"
//		);
//	}
//
//	@Test
//	public void badTableRow() throws IOException {
//		assertFeatureGiversErrors(
//			"src/test/resources/bad_table_row.feature",
//			"src/test/resources/bad_table_row.errors"
//		);
//	}
//
//	@Test
//	public void dataTable() throws FileNotFoundException {
//		assertFeatureMatchesJson(
//			"src/test/resources/data_table.feature",
//			"src/test/resources/data_table.json"
//		);
//	}

	private void assertFeatureMatchesJson(File feature, File json) throws FileNotFoundException {
		Feature cure = PickleJar.cure(feature);
		cure.setAbsoluteLocation(null);
		JsonReader jsonReader = new JsonReader(new FileReader(json));
		JsonElement jsonString = new JsonParser().parse(jsonReader);
		assertEquals(new Gson().toJsonTree(cure), jsonString);
	}

	private void assertFeatureGivesErrors(File feature, File errors) throws FileNotFoundException {
		ArrayList<String> actualErrorList = new ArrayList<>();
		PickleJar.cureCollectErrors(feature, actualErrorList);
		FileReader fileReader = new FileReader(errors);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> expectedErrorList = bufferedReader.lines().collect(Collectors.toList());
		assertEquals(expectedErrorList, actualErrorList);
	}
}

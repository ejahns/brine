import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.github.ejahns.Parser;
import com.github.ejahns.TokenQueue;
import com.github.ejahns.model.Feature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class Tests {

	@Test
	public void fileFeature() throws IOException {
		File file = new File("src/test/resources/sample_feature.feature");
		FileReader fileReader = new FileReader(file);
		TokenQueue tokenQueue = new TokenQueue(fileReader);
		Feature parse = new Parser().parse(tokenQueue);
		JsonReader reader = new JsonReader(new FileReader("src/test/resources/sample_feature.json"));
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement parse1 = jsonParser.parse(reader);
		Assert.assertEquals(parse1, gson.toJsonTree(parse));
	}
}

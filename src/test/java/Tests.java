import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import com.github.ejahns.Parser;
import com.github.ejahns.Token;
import com.github.ejahns.TokenQueue;
import com.github.ejahns.model.GherkinDocument;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import static com.github.ejahns.Parser.TokenType.*;

public class Tests {

	@Test
	public void sampleFeature() throws FileNotFoundException {
		Queue<Token> queue = new ArrayDeque<>();
		queue.add(new Token("@This is a tag", TagLineToken));
		queue.add(new Token("@This is another tag", TagLineToken));
		queue.add(new Token("@Function(\"This tag has an argument\")", TagLineToken));
		queue.add(new Token("Feature: some feature", FeatureLineToken));
		queue.add(new Token("Here is some description", OtherToken));
		queue.add(new Token("Here is more description", OtherToken));
		queue.add(new Token("Background:", BackgroundLineToken));
		queue.add(new Token("Given some common condition", StepLineToken));
		queue.add(new Token("Scenario: some scenario", ScenarioLineToken));
		queue.add(new Token("When something happens", StepLineToken));
		queue.add(new Token("Then there is some result", StepLineToken));
		queue.add(new Token("Scenario: some other scenario", ScenarioLineToken));
		queue.add(new Token("This one needs a description", OtherToken));
		queue.add(new Token("When something else happens", StepLineToken));
		queue.add(new Token("Then there is some other result", StepLineToken));
		queue.add(new Token(null, EOFToken));
		TokenQueue tokenQueue = new TokenQueue();
		tokenQueue.add(queue);
		GherkinDocument parse = new Parser().parse(tokenQueue);
		JsonReader reader = new JsonReader(new FileReader("src/test/resources/sample_feature.json"));
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement parse1 = jsonParser.parse(reader);
		Assert.assertEquals(parse1, gson.toJsonTree(parse));
	}
}

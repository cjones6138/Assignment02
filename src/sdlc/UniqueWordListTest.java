package sdlc;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class UniqueWordListTest {

	@Test
	public void test() {
		List<String> wordList = Arrays.asList("the", "I", "am", "the", "am", "list");
		Map<String, Integer> compareWords = new TreeMap<String, Integer>();
		compareWords.put("I", 1);
		compareWords.put("am", 2);
		compareWords.put("the", 2);
		compareWords.put("list", 1);
		
		Map<String, Integer> uniqueWords = Main.uniqueWords(wordList);	
		assertSame(compareWords.size(), uniqueWords.size());
	}

}

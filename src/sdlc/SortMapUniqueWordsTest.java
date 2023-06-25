package sdlc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

public class SortMapUniqueWordsTest {

	@Test
	public void test() {
		Map<String, Integer> testMap = new TreeMap<String, Integer>();
		testMap.put("I", 2);
		testMap.put("am", 3);
		testMap.put("the", 4);
		testMap.put("list", 1);
		
		List<Entry<String, Integer>> testList = Main.sortMapUniqueWords(testMap);
		System.out.println(testList.get(3).getValue());
		assertSame(1, testList.get(3).getValue());
		assertSame(2, testList.get(2).getValue());
		assertSame(3, testList.get(1).getValue());
		assertSame(4, testList.get(0).getValue());		
	}

}

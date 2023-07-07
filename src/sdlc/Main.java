package sdlc;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	/**
	 * Main method.
	 * Instantiate File object with file name.
	 * Instantiate List object using readPoem method to parse poem from file.
	 * 
	 * Instantiate Map object using uniqueWords method to parse Key of unique words with Value of number unique words occur in poem.
	 * 
	 * Instantiate List object using sortMapUniqueWords method to sort unique words descending from most frequent.
	 * 
	 * Ask user what size list to console log list at that size.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// read poem from file to list
		File file = new File("TheRavenPoemWithHTMLTags.txt");
		List<String> poemList = readPoem(file);
		
		// create Map to store words and count of how many times the words appear
		Map<String, Integer> mapUniqueWordsAndCount = uniqueWords(poemList);
				
		// establish sorted List of unique words with count
		List<Entry<String, Integer>> listSortedUniqueWordsAndCount = sortMapUniqueWords(mapUniqueWordsAndCount);
		
		// user input to find n top words
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.printf("What size list? ");
			int topWords = scanner.nextInt();
			
			// print n top words by line
			System.out.println("\nList of top " + topWords + " most reoccuring words in the poem:\n");
			listSortedUniqueWordsAndCount.subList(0, topWords).forEach(System.out::println);			
		}
		

	}
	
	/**
	 * Read poem method.
	 * 
	 * Takes in File object and returns List object.
	 * 	List will contain only the poem in all caps and no punctuation.
	 * 
	 * @param File
	 * @return List
	 * @throws IOException
	 */
	public static List<String> readPoem(File file) throws IOException {
		
		// read in txt document
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		// create variable to read line
		String line = null;
		
		// create variable to parse lines by word and store words in List
		List<String> words = new LinkedList<>();
		List<String> wordList = new ArrayList<>();
				
		// loop through BufferedReader by reading each line to a string
		while((line = br.readLine()) != null) {
			// if line read from BufferedReader is not an empty line,
			// parse line into words and add to List of words
			if(!(line.isBlank())) {
				// remove html tags and punctuation from txt document
								
				words = Arrays.asList(line.toUpperCase().split("\\s+"));
				for(int i = 0; i < words.size(); i++) {
					if(words.get(i) !=  "") {
						wordList.add(words.get(i));
					}					
				}
			}
		}
		
		br.close();
		
		// create variable to parse poem from document
		String poem = "";
		String search1 = "<P>", search2 = "</P>";
		int sub1 = 0, sub2 = 0;

		// search wordList for p tags and concat to poem as string
		for(int i = 0; i < wordList.size(); i++) {
			
			// search for open p tags and find index for subList start
			if(wordList.get(i).equals(search1)) {
				sub1 = i;
			}
			
			//search for close p tags and find index for subList end
			if(wordList.get(i).equals(search2)) {
				sub2 = i;
			}
			
			//concat poem to variable from wordList
			if(sub1 > 0 && sub2 > 0) {
				poem += wordList.subList(sub1 + 1, sub2 - 1).toString().replaceAll("\\p{IsPunctuation}", "").replaceAll("<.*?>", "");
				sub1 = 0;
				sub2 = 0;
			}
		}
		
		// establish poemList to take poem in as List
		List<String> poemList = Arrays.asList(poem.toUpperCase().split("\\s+"));
		return poemList;
		
	}
	
	/**
	 * Unique words method.
	 * 
	 * Takes in List object and returns Map object.
	 * 	Key : Unique Words.
	 * 	Value : Unique Word Occurrence.
	 * 
	 * @param List
	 * @return Map
	 */
	
	public static Map<String, Integer> uniqueWords(List<String> poemList) {
		
		// create List to store unique words
		List<String> uniqueWordList = new ArrayList<String>();
		
		// loop through wordList to search for unique words
		// store unique words in uniqueWordList
		for(int i = 0; i < poemList.size(); i++) {
			String temp;
			if(!uniqueWordList.contains(poemList.get(i))) {
				temp = poemList.get(i);
				uniqueWordList.add(temp);
			}
		}

		// create Map to store words and count of how many times the words appear
		Map<String, Integer> mapUniqueWordsAndCount = new TreeMap<>(Collections.reverseOrder());
		
		// loop through uniqueWordList
		for(int i = 0; i < uniqueWordList.size(); i++) {
			int count = 0;
			String uniqueWord = uniqueWordList.get(i);
			
			// loop through wordList
			for(int j = 0; j < poemList.size(); j++) {
				String word = poemList.get(j);
				
				// compare each uniqueWordList to each word in wordList
				// count each time the unique word appears in the document
				if(word.equals(uniqueWord)) {
					count++;
				}
			}
			
			// store unique words and how many times they appear in the document as key and value
			mapUniqueWordsAndCount.put(uniqueWord, count);			
		}
		return mapUniqueWordsAndCount;	
	}
	
	/**
	 * Sort Map by Unique Words method.
	 * 
	 * Take in Map object and return List object.
	 * 	List will contain Key and Value in order by Value.
	 * 	Key: Unique Words.
	 * 	Value : Unique Words Occurrence.
	 * 
	 * @param Map
	 * @return List
	 */
	
	public static List<Entry<String, Integer>> sortMapUniqueWords(Map<String, Integer> mapUniqueWordsAndCount)	{
		
		// establish sorted List of unique words with count
		List<Entry<String, Integer>> listSortedUniqueWordsAndCount = new ArrayList<>(mapUniqueWordsAndCount.entrySet());
		listSortedUniqueWordsAndCount.sort(Entry.comparingByValue(Comparator.reverseOrder()));
		return listSortedUniqueWordsAndCount;
		
	}

}

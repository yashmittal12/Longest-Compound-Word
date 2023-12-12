import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// this program calculate the longest and the second longest words in a file
// that can be constructed by concatenating copies of shorter words 
// also found in the file. 
// total amount of compound words can also be calculated

public class LongestCompoundWord {

	public static void main(String[] args) throws FileNotFoundException {
		

		File file = new File("words for problem.txt");


		Trie trie = new Trie();
		LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
		
		
		HashSet<String> compoundWords = new HashSet<String>();
		

		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);
		
	
		while (s.hasNext()) {
			word = s.next();		
			sufIndices = trie.getSuffixesStartIndices(word);
		
			for (int i : sufIndices) {
				if (i >= word.length())		
				queue.add(new Pair<String>(word, word.substring(i)));
			}
	
			trie.insert(word);
		}
		
		Pair<String> p;				
		int maxLength = 0;			
		//int sec_maxLength = 0;			
		String longest = "";		
		String sec_longest = "";	

		while (!queue.isEmpty()) {
			p = queue.removeFirst();
			word = p.second();
			
			sufIndices = trie.getSuffixesStartIndices(word);
			
			// if no suffixes found, which means no prefixes found
			// discard the pair and check the next pair
			if (sufIndices.isEmpty()) {
				continue;
			}
			
			//System.out.println(word);
			for (int i : sufIndices) {
				if (i > word.length()) { // sanity check 
					break;
				}
				
				if (i == word.length()) { // no suffix, means it is a compound word
					// check if the compound word is the longest
					// if it is update both longest and second longest
					// words records
					if (p.first().length() > maxLength) {
						//sec_maxLength = maxLength;
						sec_longest = longest;
						maxLength = p.first().length();
						longest = p.first();
					}
			
					compoundWords.add(p.first());	// the word is compound word
					
				} else {
					queue.add(new Pair<String>(p.first(), word.substring(i)));
				}
			}
		}
		
		// print out the results
		System.out.println("Longest Compound Word: " + longest);
		System.out.println("Second Longest Compound Word: " + sec_longest);
		System.out.println("Total Number of Compound Words: " + compoundWords.size());
	}
}

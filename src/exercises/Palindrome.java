package exercises;

import java.util.ArrayList;
import java.util.List;
import examples.FileHelper;

public class Palindrome {

	List<String> words = new ArrayList<String>();

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}

	public boolean wordExists(String s) {
		Palindrome p = new Palindrome();
		words = p.loadWords();
		return words.contains(s);

	}

	public boolean wordIsPalindrome(String s) {
		return wordExists(new StringBuilder(s).reverse().toString()) && wordExists(s); 
			
		
}}

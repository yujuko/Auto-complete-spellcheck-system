import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserInterface implements IUserInterface {
	
	/**
	 * Build WordTrie using the list of string (words)
	 */
	@Override
	public WordTrie buildWordTrie(List<String> list) {
		WordTrie wordTrie = new WordTrie();
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String thisOne = it.next();
			wordTrie.add(thisOne.toLowerCase());
		}
		return wordTrie;
	}

	/**
	 * Build SentenceTrie using the list of string (sentences)
	 */
	@Override
	public SentenceTrie buildSentenceTrie(List<String> list) {
		SentenceTrie sentenceTrie = new SentenceTrie();
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String thisOne = it.next();
			sentenceTrie.add(thisOne);
		}
		return sentenceTrie;
	}
	
	/**
	 * Print instructions
	 */
	@Override
	public void printInstructions() {
		System.out.println("**********************************");
		System.out.println("**********************************");
		System.out.println("Welcome to the autoComplete System");
		System.out.println("Input a string every time");
		System.out.println("And we will autofill it according to the frequency");
		System.out.println("Input # when finished");
		System.out.println("After you have finished the input, we will check the spelling");
		System.out.println("**********************************");
		System.out.println("**********************************");
		System.out.println("");
		System.out.println("");
	}
	
	/**
	 * check if the string contains only [a-z][A-Z][0-9] or whitespace
	 * or ends with #
	 * @param String that will be validified
	 * @return true if the String does not contain invalid chars
	 */
	public static boolean validChar(String s) {
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if((c>=65 && c<=90) || (c>=97 && c<=122) || c == 32) continue;
			else if(c == '#' && i == s.length()-1) return true;
			else return false;
		}
		return true;
	}
	
	/**
	 * Main method for executing the program
	 * @param args
	 */
	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		List<String> list;
		FileParser FileParser = new FileParser();
		try {
			list = FileParser.readTxtFile(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		WordTrie wordTrie = userInterface.buildWordTrie(list);
		try {
			list = FileParser.readCsvFile(args[1]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		SentenceTrie sentenceTrie = userInterface.buildSentenceTrie(list);
		for(String s: list) {
			String[] temp = s.split(" ");
			for(int i = 0; i < temp.length; i++) {
				wordTrie.add(temp[i]);
			}
		}
		//finish populating database and print the instructions
		userInterface.printInstructions();
		//sentence is used to save all the user input
		String sentence = "";
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		    System.out.println("Please input a string/continue your input");
		    String inputString = scanner.nextLine();
		    //if the use inputs nothing
		    if(inputString.length()<=0) continue;
		    //if the user input a invalid char
		    if(!validChar(inputString)) {
		    	System.out.println("invalid input! Please try again");
		    	continue;
		    }
		    //if the input is valid
		    //change to lower case
		    inputString = inputString.toLowerCase();
		    sentence += inputString;
		    //if the use finishes, we do not need to autofill
		    //we will do the spell check for the user
		    if(inputString.charAt(inputString.length()-1) == '#') {
		    	//delete the '#'
		    	sentence = sentence.substring(0, sentence.length()-1);
		    	break;
		    }
		    //search first in the sentenceTrie. if not found, search in the wordTrie then
		    System.out.println("current String is: "+ sentence);
		    List<String> result = sentenceTrie.search(sentence);
		    if(result.size()==0 && !sentence.contains(" ")) result = wordTrie.search(sentence);
		    //if we got sth from the two tries
		    if(result.size()>0) {
		    	System.out.println("suggestions:");
		    	for(int i = 0; i < result.size(); i++) {
		    		System.out.println(i + ". " + result.get(i));
		    	}
		    	System.out.println("choose the number of autocompletions you want to use, or input 9 if they are not what you want");
		    	String choice = scanner.nextLine();
		    	//the auto-completion is not what the user want
		    	if(choice.charAt(0) == '9') continue;
		    	else {
		    		//the user chooses the first one
		    		if(choice.charAt(0) == '0') sentence = result.get(0);
		    		//the user chooses the second one
		    		else if(choice.charAt(0) == '1') sentence = result.get(1);
		    		//the user chooses the third one
		    		else if(choice.charAt(0) == '2') sentence = result.get(2);
		    		//the user chooses the third one
		    		else if(choice.charAt(0) == '3') sentence = result.get(3);
		    		//the user chooses the third one
		    		else if(choice.charAt(0) == '4') sentence = result.get(4);
		    		else continue;
		    	}
		    }
		    //we did not get anything from either of the tries
		    else {
		    	System.out.println("We did not find any suggestions from our datanbase, Please continue the input");
		    	continue;
		    }
		}
		
		System.out.println("The string you input is: "+sentence);
		
		//spell check
		boolean spellCheck = true;
		String[] word = sentence.split(" ");
		for(int i = 0; i < word.length; i++) {
			if(word[i].length()==0) continue;
			boolean correct = wordTrie.isWord(word[i]);
			if(!correct) {
				System.out.println("spell of \'" + word[i] + "\' might be incorrect");
				spellCheck = false;
			}
		}
		if(spellCheck) {
			System.out.println("the spell is correct");
		}
		scanner.close();
	}
}
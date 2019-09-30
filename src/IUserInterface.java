import java.util.List;

/**
 * Interface for user interface
 *
 */
public interface IUserInterface {
	
	/**
	 * Read word training file and call the methods 
	 * in the WordTrie class to generate the trie
	 * @param a list of words used to build the WordTrie
	 */
	public WordTrie buildWordTrie(List<String> list);
	
	/**
	 * Read sentence file and call the methods 
	 * in the SentenceTrie class to generate the trie
	 * @param a list of sentences used to build the SentenceTrie
	 */
	public SentenceTrie buildSentenceTrie(List<String> list);

	/**
	 * Print the instructions
	 */
	public void printInstructions();
	
}

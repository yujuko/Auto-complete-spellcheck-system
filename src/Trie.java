import java.util.ArrayList;

/**
 * Interface for Trie data structure
 *
 */
public interface Trie {

	/**
	 * Add a string read from the training file into the trie
	 * @param string to add
	 * @return 
	 */
	public void add(String s);

	/**
	 * Find the top words that start with s
	 * @param string to search for
	 * @return top 5 strings that start with s
	 */
	public ArrayList<String> search(String s);
}

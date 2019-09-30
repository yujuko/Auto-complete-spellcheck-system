
/**
 * TrieNodes are the units that consist of the Trie data structure.
 * Each trie node stores either a character or word with its frequency.
 *
 */
public interface TrieNode {
	
	/**
	 * Check if the sentence/word end with this node is valid or not
	 * @return true if the sentence/word end with this node is valid
	 */
	public boolean valid();
}

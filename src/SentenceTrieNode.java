import java.util.ArrayList;

/**
 * A node in SentenceTrie, consists of a word and its frequency.
 *
 */
public class SentenceTrieNode implements TrieNode{

	private ArrayList<SentenceTrieNode> children;
	private int frequency;
	private String word;
	private boolean visited;
	private SentenceTrieNode parent;


	/**
	 * Construct a sentence trie node with the word parameter
	 * @param word
	 */
	public SentenceTrieNode(String word) {
		children = new ArrayList<>();
		frequency = 0;
		this.word = word;
		visited = false;
	}

	/**
	 * If the frequency is not 0 the sentence is valid
	 * @return true if valid
	 */
	@Override
	public boolean valid() {
		return true;
	}
	
	/**
	 * Get children nodes
	 * @return children nodes
	 */
	public ArrayList<SentenceTrieNode> getChildren() {
		return children;
	}

	/**
	 * Set children nodes
	 * @return children nodes
	 */
	public void setChildren(ArrayList<SentenceTrieNode> children) {
		this.children = children;
	}
	
	/**
	 * Get the frequency of the string formed on the path from this node to the root
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Set the frequency of the string formed on the path from this node to the root
	 * @return frequency
	 */
	public void setFrequency(int f) {
		frequency = f;
	}

	/**
	 * Get the word in this node
	 * @return the word as a string
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Set the word in this node
	 * @return the word as a string
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Check if this node's children contains a string
	 * @return the node that contains this string
	 */
	public SentenceTrieNode containString(String s) {
		for (SentenceTrieNode stn : children) {
			if (stn.getWord().equals(s)) {
				return stn;
			}
		}
		return null;
	}
	
	/**
	 * If this node was visited in DFS
	 * @return true if so
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Set visited
	 * @param boolean
	 */
	public void setVisited(boolean b) {
		visited = b;
	}
	
	/**
	 * Get parent of this node
	 * @return parent
	 */
	public SentenceTrieNode getParent() {
		return parent;
	}
	
	/**
	 * Set parent for the node
	 * @param parent
	 */
	public void setParent(SentenceTrieNode p) {
		parent = p;
	}



}

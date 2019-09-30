import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Trie data structure to store words, each node is a WordTrieNode.
 *
 */
public class WordTrie implements Trie {

	class Pair {
        private String s;
        private int c;

        public Pair(String s, int c) {
            this.s = s; 
            this.c = c;
        }
    }
	
	private WordTrieNode root;	
	public String prefix = "";	

	public WordTrie() {
		root = new WordTrieNode();		
	}
	
	/**
	 * Add a string read from the training file into the WordTrie
	 * @param string to add
	 */
	@Override
	public void add(String s) {
		WordTrieNode curr = root;
        for (char c : s.toCharArray()) {
        	WordTrieNode next = curr.getChildren()[c - 'a'];
            if (next == null) {
            	next = new WordTrieNode(c);
                curr.getChildren()[c - 'a'] = next;
            }
            curr = next;
            curr.counts.put(s, curr.counts.getOrDefault(s, 0) + 1);
        }
        
        curr.addFreq();
	}

	/**
	 * Find the top words that start with c
	 * @param character to search for
	 * @return top 5 strings that start with c
	 */
	public ArrayList<String> search(char c) {
		
        this.prefix = this.prefix + c;
        WordTrieNode curr = root;
        for (char cc : prefix.toCharArray()) {
        	
        	WordTrieNode next = curr.getChildren()[cc -'a'];
            if (next == null) {
                return new ArrayList<String>();
            }           
            curr = next;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (b.c - a.c));
        for (String s : curr.counts.keySet()) {
            pq.add(new Pair(s, curr.counts.get(s)));
        }
        
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < 5 && !pq.isEmpty(); i++) {
        	Pair p = pq.poll();
        	String ss = p.s;
            res.add(ss);           
        }
        return res;

	}

	/**
	 * Find suggestions in the database based on the input string
	 * @param string
	 * @return list of suggestions
	 */
	public ArrayList<String> search(String s) {
		if(s == null || s.equals("")) {
			return new ArrayList<String>();
		}
		ArrayList<String> res = new ArrayList<String>();
		char[] letters = s.toCharArray();
		for(char c :letters) {
			res = this.search(c);
		}
		return res;
	}
	/**
	 * Get the root of the WordTrie
	 * @return root
	 */
	public WordTrieNode getRoot() {
		return root;
	}
	
	/**
	 * If the input string is a valid word
	 * @param string
	 * @return true if valid
	 */
	public boolean isWord(String s) {
		//string is empty
		if(s == null || s.equals("")) {
			return false;
		}
		if(root.isLeaf()) {
			return false;
		}
		WordTrieNode curr = root;
        for (char c : s.toCharArray()) {        	
        	WordTrieNode next = curr.getChildren()[c -'a'];
            if (next == null) {
                return false;
            }            
            curr = next;
        }
        if(curr.getFreq() == 0) {
        	return false;
        }
        return true;		
	}

}

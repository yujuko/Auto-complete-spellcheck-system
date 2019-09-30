import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Trie data structure to store sentences, each node is a SentenceTrieNode.
 *
 */
public class SentenceTrie implements Trie{

	private SentenceTrieNode root;
	private PriorityQueue<Pair> pq;
	
	public SentenceTrie() {
		root = new SentenceTrieNode("root");
		pq = new PriorityQueue<Pair>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
	}
	
	/**
	 * String and frequency saved in Pair (PQ)
	 */
	class Pair {
        private String s;
        private int c;
        public Pair(String s, int c) {
            this.s = s; 
            this.c = c;
        }
    }
	
	/**
	 * Add a sentence read from the training file into the trie
	 * @param string to add
	 */
	@Override
	public void add(String s) {
		//Parse the string
		String[] parsedWords = s.split(" ");
		
		//If the tree is empty
		if (root.getChildren().isEmpty()) {
			int i = 0;
			SentenceTrieNode first = new SentenceTrieNode(parsedWords[i]);
			root.getChildren().add(first);
			first.setParent(root);
			i++;
			while (i < parsedWords.length) {
				SentenceTrieNode child = new SentenceTrieNode(parsedWords[i]);
				first.getChildren().add(child);
				child.setParent(first);
				first = child;
				i++;
			}
			first.setFrequency(first.getFrequency()+1);
		}
		else {
			//Find the first word
			SentenceTrieNode first = root.containString(parsedWords[0]);
			if (first != null) {
				//Search each children if contains this word
				int i = 1;
				while (i < parsedWords.length) {
					SentenceTrieNode child = first.containString(parsedWords[i]);
					if (child != null) {
						first = child;
					}else {
						child = new SentenceTrieNode(parsedWords[i]);
						first.getChildren().add(child);
						child.setParent(first);
						first = child;
					}
					i++;
				}
				first.setFrequency(first.getFrequency() + 1);
			}
			else {
				//Not in the tree, start building the whole line of children
				int i = 0;
				SentenceTrieNode parent = new SentenceTrieNode(parsedWords[i]); 
				root.getChildren().add(parent);
				parent.setParent(root);
				i++;
				while(i < parsedWords.length) {
					SentenceTrieNode child = new SentenceTrieNode(parsedWords[i]);
					parent.getChildren().add(child);
					child.setParent(parent);
					parent = child;
					i++;
				}
				parent.setFrequency(parent.getFrequency() + 1);
			}
		}	
	}

	/**
	 * Helper method to run DFS to find highest frequency words
	 * @param node
	 */
    public void DFSHelper(SentenceTrieNode node) {    	
    	node.setVisited(true);
    	if (!node.getChildren().isEmpty()) {
        	for (SentenceTrieNode n: node.getChildren()) {
        		if (!n.isVisited()) {
        			n.setVisited(true);
        			DFSHelper(n);
        		}
        	}
    	}
    	if (node.getFrequency() > 0) {
    		//Back track to find all its parents
    		pq.add(new Pair(findPath(node), node.getFrequency()));
    	}
    }
    
    /**
     * Reset visited data field to prepare for future search
     * @param node
     */
    public void resetVisited(SentenceTrieNode node) {    	
    	node.setVisited(false);
    	if (!node.getChildren().isEmpty()) {
        	for (SentenceTrieNode n: node.getChildren()) {
    			n.setVisited(false);
    			resetVisited(n);
        	}
    	}
    }
    
    /**
     * Back track all the words above this word to form the string
     * @param node
     * @return string
     */
    public String findPath(SentenceTrieNode node) {
    	SentenceTrieNode current = node;
    	StringBuilder s = new StringBuilder();
    	while (!current.getWord().equals("root")) {
    		s.insert(0, " ");
    		s.insert(0, current.getWord());
    		current = current.getParent();
    	}
    	return s.toString().trim();   	
    }
    
    /**
     * Find all children of this node that start with s
     * @param parent node
     * @param string to match with
     */
    public void findChildrenStartWith(SentenceTrieNode node, String s) {
    	for (SentenceTrieNode child: node.getChildren()) {
    		if (child.getWord().length() >= s.length()) {
    			if (child.getWord().substring(0, s.length()).equals(s)) {
    				DFSHelper(child);
    				resetVisited(child);
    			}
    		}
    	}
    }
    
	/**
	 * Find the top sentences that start with the top words that start with c found in WordTrie
	 * @param character to search in WordTrie
	 * @return top 5 sentences
	 */
	@Override
	public ArrayList<String> search(String s) {
		ArrayList<String> ret = new ArrayList<>();
		pq.clear();
		if (root.getChildren().isEmpty()) return ret;
		boolean space = false;
		if (s.charAt(s.length() - 1) == ' ') space = true;
		String[] parts = s.split(" "); //All words in the input
				
		//If more than 1 word
		if (parts.length > 1) {
			//Find the first word in sentenceTrie
			int i = 0;
			SentenceTrieNode parent = root;
			SentenceTrieNode oldParent = root;
			boolean run = true;
			while (i < parts.length) {
				oldParent = parent;
				parent = parent.containString(parts[i]);
				if (parent != null) {
					//found this word, keep going to find children
					i++;
				}
				else {
					//If not the last word
					if (i < parts.length - 1 || ((i == parts.length - 1) && space)) {
						return ret;
					}
					else {
						//Last word, find all words that start with the chars
						findChildrenStartWith(oldParent, parts[i]);
						run = false;
						i++;
					}
				}
			}
			//found all words, now find children starting from this node use DFS
			if (run) {
				DFSHelper(parent);	
				resetVisited(parent);
			}
		}

		//Only 1 word
		else {
			//Search root.children if contains string match all chars. if so, return
			for (SentenceTrieNode n: root.getChildren()) {
				if (n.getWord().length() >= parts[0].length()) {
					if (n.getWord().substring(0, parts[0].length()).equals(parts[0]) && !space) {
						DFSHelper(n);
						resetVisited(n);
					}
					else if (space) {
						if (n.getWord().substring(0, parts[0].length()).equals(parts[0]) && n.getWord().length() == parts[0].length()) {
							DFSHelper(n);
							resetVisited(n);
						}
					}
				}					
			}				
		}
		
		int j = 0;
		int count = pq.size();
		if (count > 5) count = 5;
		while (j < count) {
			ret.add(pq.poll().s);
			j++;
		}
		return ret;
	}
	
	
	/**
	 * Get root of the trie
	 * @return root
	 */
	public SentenceTrieNode getRoot() {
		return root;
	}


}

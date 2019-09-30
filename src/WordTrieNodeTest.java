import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordTrieNodeTest {

	WordTrieNode wordTrieNode1;
	WordTrieNode wordTrieNode2;
	WordTrieNode wordTrieNode3;
	WordTrieNode wordTrieNode4;
	
	@Before
	public void setUp() throws Exception {
		wordTrieNode1 = new WordTrieNode('a');
		wordTrieNode2 = new WordTrieNode('b');	
		wordTrieNode3 = new WordTrieNode('d');
		wordTrieNode4 = new WordTrieNode();
	}
	
	/**
	 * used to test get and set method in the wordTrieNode
	 */
	@Test
	public void testGetSetC() {
		wordTrieNode3.setC('c');
		assertEquals(wordTrieNode3.getC(),'c');	
	}
	
	/**
	 * used to test addFreq() in the wordTrieNode
	 */
	@Test
	public void testAddFreq() {
		assertEquals(wordTrieNode1.getFreq(),0);
		wordTrieNode1.addFreq();
		assertEquals(wordTrieNode1.getFreq(),1);
	}

	/**
	 * used to test isWord() in the wordTrieNode
	 */
	@Test
	public void testIsWord() {
		assertFalse(wordTrieNode1.valid());
		wordTrieNode1.addFreq();
		assertTrue(wordTrieNode1.valid());		
	}
	
	/**
	 * used to test children() in the wordTrieNode
	 */
	@Test
	public void testChildren() {
		WordTrieNode n1 = new WordTrieNode('a');
		WordTrieNode n2 = new WordTrieNode('b');
		WordTrieNode n3 = new WordTrieNode('c');
		
		WordTrieNode[] children = {n1, n2, n3};
		wordTrieNode1.setChildren(children);
		assertEquals('a', wordTrieNode1.getChildren()[0].getC());		
	}
	
	/**
	 * used to test isLeaf() in the wordTrieNode
	 */
	@Test
	public void testIsLeaf() {
		assertTrue(wordTrieNode1.isLeaf());
		WordTrieNode[] childNodes = new WordTrieNode[26];
		childNodes[1] = wordTrieNode2; 
		wordTrieNode1.setChildren(childNodes);
		assertFalse(wordTrieNode1.isLeaf());
		assertTrue(wordTrieNode2.isLeaf());
	}

}

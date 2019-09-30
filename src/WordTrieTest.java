import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class WordTrieTest {

	WordTrie wordTrie;
	String s1 = "a";
	String s2 = "ab";
	String s3 = "abc";
	String s4 = "abcd";
	
	@Before
	public void setUp() throws Exception {
		wordTrie = new WordTrie();
	}

	/**
	 * used to test add() in the wordTrie class
	 */
	@Test
	public void testAdd() {
		wordTrie.add(s1);
		wordTrie.add(s2);
		wordTrie.add(s3);
		wordTrie.add(s4);
		
		WordTrieNode[] children1 = wordTrie.getRoot().getChildren();
		assertEquals('a', children1[0].getC());
		assertEquals(1, children1[0].getFreq());
		
		WordTrieNode[] children2 = children1[0].getChildren();
		assertEquals('b', children2[1].getC());
		assertEquals(1, children2[1].getFreq());
		
		wordTrie.add(s2);
		WordTrieNode[] children3 = wordTrie.getRoot().getChildren();
		WordTrieNode[] children4 = children3[0].getChildren();
		assertEquals('b', children4[1].getC());
		assertEquals(2, children4[1].getFreq());		
	}
	
	/**
	 * used to test search() in the wordTrie class
	 */
	@Test
	public void testSearch() {
		
		assertTrue(wordTrie.search('a').isEmpty());
		
		WordTrie wordTrie2 = new WordTrie();
				
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		
		wordTrie2.add(s2);
		wordTrie2.add(s2);
		wordTrie2.add(s2);
		wordTrie2.add(s2);
		wordTrie2.add(s2);
		
		wordTrie2.add(s3);
		wordTrie2.add(s3);
		wordTrie2.add(s3);
		wordTrie2.add(s3);
		
		wordTrie2.add(s4);
		wordTrie2.add(s4);
		wordTrie2.add(s4);
		
		ArrayList<String> list = wordTrie2.search('a');
		assertEquals(4, list.size());
		assertEquals("a",list.get(0));
		assertEquals("ab",list.get(1));
		assertEquals("abc",list.get(2));
		assertEquals("abcd",list.get(3));
		assertEquals(3, wordTrie2.search('b').size());
	
		wordTrie2.add("abcde");
		wordTrie2.add("abcde");
		
		wordTrie2.add("abcdef");
		ArrayList<String> list2 = wordTrie2.search('c'); 
		
		assertEquals(4, list2.size());		
	}
	
	/**
	 * used to test search() in the wordTrie class
	 */
	@Test
	public void testSearch2() {
		
		WordTrie wordTrie2 = new WordTrie();
		assertEquals(0,wordTrie2.search("").size());
		
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		wordTrie2.add(s1);
		
		wordTrie2.add(s2);
		wordTrie2.add(s2);
		wordTrie2.add(s2); 
		wordTrie2.add(s2);
		wordTrie2.add(s2);
		
		wordTrie2.add(s3);
		wordTrie2.add(s3);
		wordTrie2.add(s3);
		wordTrie2.add(s3);
		
		wordTrie2.add(s4);
		wordTrie2.add(s4);
		wordTrie2.add(s4);
		
		ArrayList<String> list = wordTrie2.search("ab");
		assertEquals(3, list.size());
		assertEquals("ab",list.get(0));
		assertEquals("abc",list.get(1));
		assertEquals("abcd",list.get(2));
		ArrayList<String> list2 = wordTrie2.search("c");
		assertEquals("abc",list2.get(0));
		assertEquals("abcd",list2.get(1));
		
	}
	
	/**
	 * used to test isWord() in the wordTrie class
	 */
	@Test
	public void testIsWord() {
		
		assertFalse(wordTrie.isWord(s1));
		
		wordTrie.add(s1);
		wordTrie.add(s2);
		wordTrie.add(s3);
		wordTrie.add(s4);
		
		assertTrue(wordTrie.isWord(s1));
		assertTrue(wordTrie.isWord(s2));
		assertTrue(wordTrie.isWord(s3));
		assertTrue(wordTrie.isWord(s4));
		
		assertFalse(wordTrie.isWord("abd"));
		
		assertFalse(wordTrie.isWord("abcdef"));
		wordTrie.add("abcdef");
		assertFalse(wordTrie.isWord("abcde"));
		assertFalse(wordTrie.isWord(""));
		
				
	}
		
}


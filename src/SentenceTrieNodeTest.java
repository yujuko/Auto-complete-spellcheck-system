import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class SentenceTrieNodeTest {
	
	/**
	 * used to test methods in the sentenceTrieNode class
	 */
	@Test
	public void test() {
		SentenceTrieNode stn = new SentenceTrieNode("University");
		ArrayList<SentenceTrieNode> children = new ArrayList<>();
		SentenceTrieNode child1 = new SentenceTrieNode("of");
		SentenceTrieNode child2 = new SentenceTrieNode("City");
		SentenceTrieNode child3 = new SentenceTrieNode("District");
		children.add(child1);
		children.add(child2);
		children.add(child3);
		child1.setParent(stn);
		child2.setParent(stn);
		child3.setParent(stn);
		assertTrue(stn.valid());
		stn.setChildren(children);
		assertEquals("of", stn.getChildren().get(0).getWord());
		assertEquals(0, stn.getChildren().get(0).getFrequency());
		stn.getChildren().get(0).setFrequency(1);
		assertEquals(1, stn.getChildren().get(0).getFrequency());
		stn.getChildren().get(0).setWord("Of");
		assertEquals("Of", stn.getChildren().get(0).getWord());		
		
		assertEquals("Of", stn.containString("Of").getWord());
		assertNull(stn.containString("believe"));
		assertFalse(stn.containString("Of").isVisited());
		stn.containString("Of").setVisited(true);
		assertTrue(stn.containString("Of").isVisited());
		assertEquals("University", stn.containString("Of").getParent().getWord());
		stn.containString("Of").setParent(child2);
		assertEquals("City", stn.containString("Of").getParent().getWord());
		
	}

}

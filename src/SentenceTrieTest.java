import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class SentenceTrieTest {

	/**
	 * used to test add() in the sentenceTrie class
	 */
	@Test
	public void testAdd() {
		SentenceTrie st = new SentenceTrie();
		ArrayList<String> list  = new ArrayList<>();
		list.add("blood feast");
		list.add("habitat");
		list.add("double agent");
		list.add("little italy");
		list.add("little prince");
		list.add("little italy store");
		
		for (String s : list) {
			st.add(s);
		}
		assertEquals("root", st.getRoot().getWord());
		assertEquals("blood", st.getRoot().containString("blood").getWord());
		assertEquals(0, st.getRoot().containString("blood").getFrequency());
		assertEquals("italy", st.getRoot().containString("little").containString("italy").getWord());
		assertEquals(1, st.getRoot().containString("little").containString("italy").getFrequency());
		assertEquals(1, st.getRoot().containString("little").containString("italy").containString("store").getFrequency());
		
	}
	

	/**
	 * used to test search() in the sentenceTrie class
	 */
	@Test
	public void testSearch() {
		SentenceTrie st = new SentenceTrie();
		ArrayList<String> list  = new ArrayList<>();
		list.add("blood feast");
		list.add("habitat");
		list.add("double agent");
		list.add("little italy");
		list.add("little prince");
		list.add("little italy store");
		
		for (String s : list) {
			st.add(s);
		}
		
		ArrayList<String> ret = new ArrayList<>();
		ret = st.search("little italy");
		
		assertEquals(2, ret.size());
		ret = st.search("little");
		assertEquals(3, ret.size());
		
		ret = st.search("habitat");
		assertEquals(1, ret.size());
		
		ret = st.search("hello world");
		assertEquals(0, ret.size());
		
		ret = st.search("ruv");
		assertEquals(0, ret.size());
		
		ret = st.search("double agent");
		assertEquals(1, ret.size());
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("little france");
		list2.add("little king");
		list2.add("little france store");
		for (String s : list2) {
			st.add(s);
		}
		
		ret = st.search("little");
		assertEquals(5, ret.size());
		
		SentenceTrie st2 = new SentenceTrie();
		assertEquals(0, st2.search("haha").size());
		
		ret = st.search("library");
		assertEquals(0, ret.size());
		
		ret = st.search("dou");
		assertEquals(1, ret.size());
		
		ret = st.search("little it");
		assertEquals(2, ret.size());
		
		ret = st.search("dou ");
		assertEquals(0, ret.size());
		
		ret = st.search("lil ");
		assertEquals(0, ret.size());
		
		ret = st.search("little ");
		assertEquals(5, ret.size());
		
		ret = st.search("little ita ");
		assertEquals(0, ret.size());
		
		ret = st.search("double agentagent");
		assertEquals(0, ret.size());
	}
	

}

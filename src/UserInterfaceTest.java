import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class UserInterfaceTest {

	/**
	 * used to tset printInstructions() in the userInterface
	 */
	@Test
	public void testPrintInstructions() {
		UserInterface u = new UserInterface();
		u.printInstructions();
		//pass as long as no exceptions thrown
		return;
	}
	
	/**
	 * used to test buildWordTrie() in the userInterface
	 */
	@Test
	public void testBuildWordTrie() {
		List<String> l1= new ArrayList<>();
		l1.add("hello");
		l1.add("world");
		UserInterface u = new UserInterface();
		WordTrie t = u.buildWordTrie(l1);
		assertEquals('h',t.getRoot().getChildren()[7].getC());
	}
	
	/**
	 * used to test buildWordTrie() in the userInterface
	 */
	@Test
	public void testBuildSentenceTrie() {
		List<String> l1= new ArrayList<>();
		l1.add("University of Pennsylvania");
		l1.add("cit five ninety four");
		UserInterface u = new UserInterface();
		SentenceTrie s = u.buildSentenceTrie(l1);
		assertEquals("University of Pennsylvania", s.search("U").get(0));
	}
	
	/**
	 * used to test validChar() in the userInterface
	 */
	@Test
	public void testValidChar() {
		assertTrue(UserInterface.validChar("cak zs"));
		assertTrue(UserInterface.validChar("Aiw jKzZ#"));
		assertTrue(UserInterface.validChar("AISJ"));
		assertFalse(UserInterface.validChar("0sowj2"));
		assertFalse(UserInterface.validChar("skw m91Q"));
		assertTrue(UserInterface.validChar(" "));
		assertFalse(UserInterface.validChar(" ."));
		assertFalse(UserInterface.validChar("#w"));
		assertFalse(UserInterface.validChar(";:{}"));
	}

}

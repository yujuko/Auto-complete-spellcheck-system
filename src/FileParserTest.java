import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;

public class FileParserTest {

	/**
	 * used to test readTxtFile() in the FileParser class
	 */
	@Test
	public void testReadTxtFile() throws FileNotFoundException {
		FileParser fp = new FileParser();
		ArrayList<String> parsedLines = fp.readTxtFile("test.txt");
		assertEquals("This is a test file", parsedLines.get(0));
		assertEquals("University of Virginia", parsedLines.get(1));

		ArrayList<String> parsedWords = fp.readTxtFile("test1.txt");
		assertEquals("test", parsedWords.get(0));
		assertEquals("file", parsedWords.get(1));
	}
	
	/**
	 * used to test readCsvFile() in the FileParser class
	 */
	@Test
	public void testReadCsvFile() throws FileNotFoundException {
		FileParser fp = new FileParser();
		ArrayList<String> parsedLines = fp.readCsvFile("movies.csv");
		assertEquals("toy story", parsedLines.get(0));
	}

}

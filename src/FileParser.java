import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used for parsing training files to create the initial Tries
 *
 */
public class FileParser {

	/**
	 * Read a csv file and stores each sentence into an ArrayList
	 * 
	 * @param fileName
	 * @return a list of strings as each line read in
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> readCsvFile(String fileName) throws FileNotFoundException {
		File f = new File(fileName);
		ArrayList<String> lines = new ArrayList<>();

		Scanner scan = new Scanner(f);

		String s = scan.nextLine();
		while (scan.hasNext()) {
			s = scan.nextLine();
			String[] ss = s.split(",");
			int flag = 1;
			String newString = "";
			for (char c : ss[1].toCharArray()) {
				if (c == '(') {
					break;
				}
				newString += c;
			}
			for (char c : newString.toCharArray()) {
				if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {				
				s = newString.toLowerCase();
				if(!s.isEmpty()) {
					lines.add(s.trim());
				}				
			}
		}
		scan.close();
		return lines;
	}
	
	/**
	 * Read a txt file and stores each word into an ArrayList
	 * @param fileName
	 * @return a list of strings as each word read in
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> readTxtFile(String fileName) throws FileNotFoundException {
		File f = new File(fileName);
		ArrayList<String> lines = new ArrayList<>();
		
		Scanner scan= new Scanner(f);
		scan.useDelimiter("\n|\r");
	    
	    while (scan.hasNext()) {
	    	lines.add(scan.nextLine());
	    }
	    scan.close();
	    return lines;
	}
	
}

/*
Utilized HashSet and parsing using IO to create a SpellChecker
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class SpellChecker {
	
	private HashSet<String> words;
	private ArrayList<String> parsedFile;
	
	SpellChecker() {
		words = new HashSet<String>();
		parsedFile = new ArrayList<String>();
	}
	
/*
 * populates dictionary with dictionary words
 * @param text file containing dictionary words to populate the hashSet with
 */
	public void createDict(String inputFile) throws IOException {
		FileReader input = new FileReader(inputFile);
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		
		while ((myLine = bufRead.readLine()) != null)
		{   
			//Wrapper my_line = new Wrapper(myLine);
			words.add(myLine);
		}
		bufRead.close();
	}
	
/*
 * parses text file
 * @param text file to be checked for misspelled words
 * @return arrayList w/ each element a word of the text file
 */
	public ArrayList<String> readFile(String inputFile) throws IOException {
		FileReader input = new FileReader(inputFile);
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		//Wrapper[] array_file;
		
		while ((myLine = bufRead.readLine()) != null)
		{   
			String[] arrayFile = myLine.split(" ");
			for (int i = 0; i < arrayFile.length; i++) {
				//array_file = new Wrapper[arrayFile.length];
				arrayFile[i] = arrayFile[i].toLowerCase().replaceAll("\\s+"," ");
				
				//Wrapper my_line = new Wrapper(arrayFile[i]);
				//array_file[i] = my_line;
				parsedFile.add(arrayFile[i]);
			}
		}
		
		bufRead.close();
		return parsedFile;
	}
	
	public ArrayList<String> getParsedFile(){
		return parsedFile;
	}
	
	
/* sees if words in text file are in dictionary, populates an arrayList with misspelled words
 * @return arrayList of misspelled strings
 */
	public ArrayList<String> checkMisspellings() {
		ArrayList<String> misspelled = new ArrayList<String>();
		
		for (int i = 0; i<parsedFile.size(); i++) {
			if (!words.contains(parsedFile.get(i))) {
				misspelled.add(parsedFile.get(i));
			}
		}
		return misspelled;
	}
}

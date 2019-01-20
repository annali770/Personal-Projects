/*
 * Created by Anna Li on 1/18/19
 * 
 * SpellChecker using HashSet, parsing using IO; 
 * tested a Wrapper class in comments (assuming hashMap, arraylist of type Wrapper)
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
  @post creates a HashSet of words 
  @param name of dictionary text file
*/
    public void createDict(String inputFile) throws IOException {
        
    	//reads in dictionary file
    	FileReader input = new FileReader(inputFile);
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        
        //loops through file, adding each word to hashSet
        while ((myLine = bufRead.readLine()) != null)
        {   
            //Wrapper my_line = new Wrapper(myLine);
            words.add(myLine.toLowerCase().replaceAll("\\s+",""));
        }
        
        bufRead.close();
    }

 /*
 	@post reads in text file to be checked
 	@param name of text file to be checked
 */
    public ArrayList<String> readFile(String inputFile) throws IOException {
        
    	//reads in text file
    	FileReader input = new FileReader(inputFile);
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        //Wrapper[] array_file;

        //loops through input file
        while ((myLine = bufRead.readLine()) != null)
        {   
        	//seperates each word into a different element of the array
            String[] arrayFile = myLine.split(" ");
            for (int i = 0; i < arrayFile.length; i++) {
                //array_file = new Wrapper[arrayFile.length];
                //Wrapper my_line = new Wrapper(arrayFile[i]);
                //array_file[i] = my_line;
                parsedFile.add(arrayFile[i].toLowerCase().replaceAll("\\s+","").replaceAll("\\p{P}", ""));
            }
        }

        bufRead.close();
        return parsedFile;
    }
   
/*
   @return inputted file with each word as a different element of an arrayList
*/    
    public ArrayList<String> getParsedFile(){
        return parsedFile;
    }
    
/*
   @pre dictionary must be created and file must be read in
   @return arrayList of misspelled words in input file
 */
    public ArrayList<String> checkMisspellings() {
        ArrayList<String> misspelled = new ArrayList<String>();
        
        //checks if word from input file is in dictionary file; if not, add to list of misspelled words
        for (int i = 0; i<parsedFile.size(); i++) {
        	System.out.println(parsedFile.get(i));
            if (!words.contains(parsedFile.get(i))) {
                misspelled.add(parsedFile.get(i));
            }
        }
        return misspelled;
    }
}
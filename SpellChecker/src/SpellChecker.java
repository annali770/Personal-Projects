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

    public void createDict(String inputFile) throws IOException {
        FileReader input = new FileReader(inputFile);
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
    
        while ((myLine = bufRead.readLine()) != null)
        {   
            //Wrapper my_line = new Wrapper(myLine);
            words.add(myLine.toLowerCase().replaceAll("\\s+",""));
        }
        bufRead.close();
    }

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

                //Wrapper my_line = new Wrapper(arrayFile[i]);
               // array_file[i] = my_line;
                parsedFile.add(arrayFile[i].toLowerCase().replaceAll("\\s+",""));
            }
        }

        bufRead.close();
        return parsedFile;
    }

    public ArrayList<String> getParsedFile(){
        return parsedFile;
    }

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
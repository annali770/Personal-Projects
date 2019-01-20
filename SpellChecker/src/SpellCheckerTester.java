import java.io.IOException;

public class SpellCheckerTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		SpellChecker sp = new SpellChecker(); 
		sp.createDict("words"); 
		sp.readFile("checkSpelling"); 
		System.out.println(sp.checkMisspellings());
		
	}

}
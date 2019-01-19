import java.io.IOException;

public class HashTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		SpellChecker sc = new SpellChecker();
		sc.createDict("words.txt");
		sc.readFile("checkSpelling.txt");
		System.out.println(sc.checkMisspellings());
		
		
		
	}

}

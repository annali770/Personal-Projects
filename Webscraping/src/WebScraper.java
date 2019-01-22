
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScraper {
	
	private HashSet<String> safeIngredients;
	private HashSet<String> unclearIngredients;
	private ArrayList<String> companies;
	private Document doc;

    WebScraper() {
        safeIngredients = new HashSet<String>();
        unclearIngredients = new HashSet<String>();
        companies = new ArrayList<String>();
        createCompanies();
    }
    
    private void createCompanies() {
    	companies.add("innisfree");
    	companies.add("cerave");
    	companies.add("nyx");
    }
    
    private void connectWebsite(String websiteUrl) throws IOException {
    	doc = Jsoup.connect(websiteUrl).get();
    }
    
    public HashSet<String> insertIngredients(String websiteUrl, String company, Boolean isSafe) throws IOException {
    	connectWebsite(websiteUrl);
    	System.out.println("Product: " + getProduct(websiteUrl));
		
    	String ingredientsList = "";
    	
    	if (company.equals("innisfree")) {
    		ingredientsList = getInnisfree();
    		System.out.println("Ingredients: " + getInnisfree());
    	}
    	
    	if (company.equals("cerave")) {
    		ingredientsList = getCerave();
    		System.out.println("Ingredients: " + getCerave());
    	}
    	
		String[] arrayFile = ingredientsList.split(", ");
		for (int i = 0; i < arrayFile.length; i++) {
			if (isSafe) {
				safeIngredients.add(arrayFile[i]);
			}
			else {
				if (!safeIngredients.contains(arrayFile[i])) {
					unclearIngredients.add(arrayFile[i]);
				}
			}
		}
		
		return safeIngredients;
    }
    
    public HashSet<String> getSafeIngredients() {
    	return safeIngredients;
    }
    
    public HashSet<String> getUnclearIngredients() {
    	return unclearIngredients;
    }
    
    private String getProduct(String website) throws IOException {
		String title = doc.title();
		return title;
    }
    
    public void refresh() {
    	Iterator<String> unclearIterator = unclearIngredients.iterator();
    	
    	while (unclearIterator.hasNext()) {
    		if (safeIngredients.contains(unclearIterator.next())) {
    			unclearIterator.remove();
    		}
    	}
    }
    
    private String getInnisfree() {
    	Elements content = doc.getElementsByClass("more-content");
		String linkText = content.text().toLowerCase();
		return linkText;
    }
    
    private String getCerave() {
    	Elements content = doc.getElementsByClass("col-md-12");
    	String linkText = content.text().toLowerCase().replaceAll("\\.", ""); ;
    	int beg = linkText.indexOf("purified water");
    	int end = linkText.indexOf(" product features");
		return linkText.substring(beg, end);
    }
    
}

/*
 * Created by Anna Li 1/21/19
 * 
 * Java application that utilizes a webscraper to read ingredients to a skincare
 * product, used to determine what ingredients the user might be allergic to
 * 
 * 
 * TO DO: update cerave for non-water based/ointment products, add more company websites
 */

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
    
    //helper function; populates arraylist w/ company websites can webscrape from
    //ADD MORE
    private void createCompanies() {
    	companies.add("innisfree");
    	companies.add("cerave");
    }
    
    //@return companies currently added
    public ArrayList<String> getCompanies() {
    	return companies;
    }
    
    /*
     * @post connects program to website webscraping from
     */
    private void connectWebsite(String websiteUrl) throws IOException {
    	doc = Jsoup.connect(websiteUrl).get();
    }
    
    /*
     *  @post populates hashset of safe ingredients (based off of if user is allergic or not) 
     *  	 and unclear ingredients (something in it caused the user to break out, but unclear)
     * 		 prints out product name and ingredients of inserted product 
     */
    public void insertIngredients(String websiteUrl, String company, Boolean isSafe) throws IOException {
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
    }
    
    //@return hashset of safe ingredients
    public HashSet<String> getSafeIngredients() {
    	return safeIngredients;
    }
    
    //@return hashset of unclear ingredients
    public HashSet<String> getUnclearIngredients() {
    	return unclearIngredients;
    }
    
    /*
     * helper function
     * @return product name
     */
    private String getProduct(String website) throws IOException {
		String title = doc.title();
		return title;
    }
    
    //@post refreshes the list of safe/unclear ingredients to reflect newly added products
    public void refresh() {
    	Iterator<String> unclearIterator = unclearIngredients.iterator();
    	
    	while (unclearIterator.hasNext()) {
    		if (safeIngredients.contains(unclearIterator.next())) {
    			unclearIterator.remove();
    		}
    	}
    }
    
    //@return string with list of ingredients from Innisfree website
    private String getInnisfree() {
    	Elements content = doc.getElementsByClass("more-content");
		String linkText = content.text().toLowerCase();
		return linkText;
    }
    
    //@return string with list of ingredients from Cerave website
    //UPDATE WITH NON-WATER PRODUCTS
    private String getCerave() {
    	Elements content = doc.getElementsByClass("col-md-12");
    	String linkText = content.text().toLowerCase().replaceAll("\\.", ""); ;
    	int beg = linkText.indexOf("purified water");
    	int end = linkText.indexOf(" product features");
		return linkText.substring(beg, end);
    }
    
}

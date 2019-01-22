import java.io.IOException;

public class WebScrapingTester {

	public static void main(String[] args) throws IOException {
		
		WebScraper ws = new WebScraper();
		ws.insertIngredients("https://us.innisfree.com/hydrating-sleeping-mask-with-green-tea/270670034.html", "innisfree", true);
		ws.insertIngredients("https://www.cerave.com/our-products/moisturizers/daily-moisturizing-lotion", "cerave", false);
		ws.insertIngredients("https://www.cerave.com/our-products/cleansers/foaming-facial-cleanser", "cerave", true);
		ws.insertIngredients("https://us.innisfree.com/nourishing-cream-with-canola-honey/270670187.html?cgid=sc-canola-1#start=2", "innisfree", false);
		//ws.insertIngredients("https://www.cerave.com/our-products/healing-ointment/healing-lip-balm", "cerave", false);
		ws.insertIngredients("https://us.innisfree.com/matte-blur-primer/270670405.html?cgid=mk-primer#start=1", "innisfree", true);
		ws.refresh();
		System.out.println(ws.getSafeIngredients());
		System.out.println(ws.getUnclearIngredients());
	}

}

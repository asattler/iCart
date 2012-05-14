package de.bht.mme2.icart.ocp.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.bht.mme2.icart.ocp.recipe.Ingredient;
import de.bht.mme2.icart.ocp.recipe.Recipe;
import de.bht.mme2.icart.ocp.recipe.RecipeDao;

public class TestParser {

	private static final String TEST_PATH = "http://www.chefkoch.de/rezepte/2082671336381721/Pide-mit-Hack.html";

	public static void mytest() throws IOException{
		Recipe recipe = new Recipe();
		RecipeDao recipeDao = new RecipeDao();
		
		Document doc = Jsoup.connect(TEST_PATH).get();
		recipe.setUrl(TEST_PATH);
		recipe.setName(doc.select(".hrecipe .big").text());
		Elements zutaten = doc.select(".zutaten .ingredient");
		Element img = doc.select("#ss_img_link img").first();
		String url = img.absUrl("src");
		recipe.setImgURL(url);
		
		ArrayList<Ingredient> ig = recipe.getIngredients();
		if(ig == null){
			ig = new ArrayList<Ingredient>();
		}
		for(Element e : zutaten){
			Ingredient z = new Ingredient();
			z.setName(e.select("td").get(1).text());
			z.setAmount(e.select("td").get(0).text());
			ig.add(z);
		}
		
		recipeDao.save(recipe);
		System.out.println("Rezept: "+doc.select(".hrecipe .big").text());
		System.out.println(ig.toString());
		
	}
	
}

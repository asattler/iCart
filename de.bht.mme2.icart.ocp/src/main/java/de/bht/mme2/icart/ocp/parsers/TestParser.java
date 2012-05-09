package de.bht.mme2.icart.ocp.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.bht.mme2.icart.ocp.recipe.Ingredient;
import de.bht.mme2.icart.ocp.recipe.Recipe;

public class TestParser {

	public static void mytest() throws IOException{
		Recipe recipe = new Recipe();
		
		Document doc = Jsoup.connect("http://www.chefkoch.de/rezepte/2082671336381721/Pide-mit-Hack.html").get();
		recipe.setName(doc.select(".hrecipe .big").text());
		Elements zutaten = doc.select(".zutaten .ingredient");
		
		
		ArrayList<Ingredient> ig = recipe.getIngredients();
		for(Element e : zutaten){
			Ingredient z = new Ingredient();
			z.setName(e.select("td").get(1).text());
			z.setAmount(e.select("td").get(0).text());
			ig.add(z);
		}
		
		System.out.println("Rezept: "+doc.select(".hrecipe .big").text());
		System.out.println(ig.toString());
		
	}
	
}

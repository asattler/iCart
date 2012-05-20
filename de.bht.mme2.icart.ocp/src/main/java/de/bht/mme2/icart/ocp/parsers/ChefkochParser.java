package de.bht.mme2.icart.ocp.parsers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.bht.mme2.icart.ocp.recipe.Ingredient;
import de.bht.mme2.icart.ocp.recipe.Recipe;
import de.bht.mme2.icart.ocp.user.User;

public class ChefkochParser implements IParser{

	@Override
	public Recipe parseFromURL(String url, User user) throws IOException {
		Recipe recipe = new Recipe();
		
		Document doc = Jsoup.connect(url).get();
		
		recipe.setUrl(url);
		recipe.setName(doc.select(".hrecipe .big").text());
		recipe.setAmountPortion(Integer.valueOf(doc.select("#rezept-zutaten input[name=divisor]").val()));
		
		Elements zutaten = doc.select(".zutaten .ingredient");
		Element img = doc.select("#ss_img_link img").first();
		String imgurl = img.absUrl("src");
		recipe.setImgURL(imgurl);
		
		Set<User> userList = new HashSet<User>();
		userList.add(user);
		recipe.setUsers(userList);
		
		Set<Ingredient> ig = new HashSet<Ingredient>();
		Ingredient ingr;
		for(Element e : zutaten){
			ingr = new Ingredient();
			ingr.setName(e.select("td").get(1).text());
			ingr.setAmount(e.select("td").get(0).text());
			ig.add(ingr);
		}
		recipe.setIngredients(ig);
		
		return recipe;
	}

}

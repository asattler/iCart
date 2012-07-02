package de.bht.mme2.icart.ocp.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.bht.mme2.icart.ocp.recipe.Ingredient;
import de.bht.mme2.icart.ocp.recipe.Recipe;
import de.bht.mme2.icart.ocp.user.User;

public class DasKochRezeptParser  implements IParser{

	@Override
	public Recipe parseFromURL(String url, User user) throws IOException {
		Recipe recipe = new Recipe();
		
		Document doc = Jsoup.connect(url).get();
		
		recipe.setUrl(url);
		recipe.setName(doc.select("h2[itemprop=name]").text());
		recipe.setAmountPortion(Integer.valueOf(doc.select("input[name=param_personen]").val()));
		
		Elements zutaten = doc.select("table.rezept tr[itemprop=ingredient]");
		Element img = doc.select("img[itemprop=photo]").first();
		String imgurl = img.absUrl("src");
		recipe.setImgURL(imgurl);
		
		List<User> userList = new ArrayList <User>();
		userList.add(user);
		recipe.setUsers(userList);
		
		Set<Ingredient> ig = new HashSet<Ingredient>();
		Ingredient ingr;
		for(Element e : zutaten){
			ingr = new Ingredient();
			ingr.setName(e.select("td[itemprop=name]").text());
			ingr.setAmount(e.select("td[itemprop=amount]").text());
			ig.add(ingr);
		}
		recipe.setIngredients(ig);
		
		return recipe;
	}

}

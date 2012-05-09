package de.bht.mme2.icart.ocp.parsers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestParser {

	public static void mytest() throws IOException{
		Document doc = Jsoup.connect("http://www.chefkoch.de/rezepte/2082671336381721/Pide-mit-Hack.html").get();
		Elements zutaten = doc.select(".zutaten .ingredient");

		ArrayList<Zutat> zut = new ArrayList<Zutat>();
		for(Element e : zutaten){
			Zutat z = new Zutat();
			z.setName(e.select("td").get(1).text());
			z.setAmount(e.select("td").get(0).text());
			zut.add(z);
		}
		
		System.out.println(zut.toString());
		/*
		 * System.out.println(http://www.chefkoch.de/rezepte/2082671336381721/Pide-mit-Hack.html.toString());
		
		Parser parser = new Parser ("http://www.chefkoch.de/rezepte/2082671336381721/Pide-mit-Hack.html");
		NodeList list = parser.parse (null);
		System.out.println(list.toString());
		*/
	}
	
}

package de.bht.mme2.icart.ocp.parsers;

import java.io.IOException;

import de.bht.mme2.icart.ocp.recipe.Recipe;
import de.bht.mme2.icart.ocp.user.User;

public interface IParser {
	public Recipe parseFromURL(String url, User user) throws IOException;
}

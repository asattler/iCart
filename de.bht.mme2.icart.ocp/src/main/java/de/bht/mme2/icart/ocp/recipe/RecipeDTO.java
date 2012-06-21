package de.bht.mme2.icart.ocp.recipe;

import java.util.HashSet;
import java.util.Set;

public class RecipeDTO {

	private Long id;
	
	private String name;
	
	private int amountPortion;
	
	private Set<Ingredient> ingredients;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Ingredient> getIngredients() {
		if(ingredients == null){
			return new HashSet<Ingredient>();
		}
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public int getAmountPortion() {
		return amountPortion;
	}

	public void setAmountPortion(int amountPortion) {
		this.amountPortion = amountPortion;
	}
	
	
	
	
	
	
}

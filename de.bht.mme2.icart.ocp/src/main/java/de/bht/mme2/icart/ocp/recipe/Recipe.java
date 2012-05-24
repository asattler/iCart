package de.bht.mme2.icart.ocp.recipe;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.bht.mme2.icart.ocp.user.User;

@Entity
@Table(name="icart_recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	private String url;
	
	private String imgURL;
	
	private int amountPortion;
	
	private Set<User> users;
	
	private Set<Ingredient> ingredients;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Set<User> getUsers() {
		if(users == null){
			return new HashSet<User>();
		}
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
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

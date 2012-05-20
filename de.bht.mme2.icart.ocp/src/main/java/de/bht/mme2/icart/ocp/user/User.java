package de.bht.mme2.icart.ocp.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.bht.mme2.icart.ocp.recipe.Recipe;

@Entity
@Table(name="icart_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	@ManyToMany(mappedBy = "users")
	private Set<Recipe> recipes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Recipe> getRecipes() {
		if(recipes == null){
			return new HashSet<Recipe>();
		}
		return recipes;
	}
	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	
}

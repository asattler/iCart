package de.bht.mme2.icart.ocp.recipe;

public class Ingredient {
	private String name;
	private String amount;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Zutat [name=" + name + ", amount=" + amount + "]";
	}
	
	
}

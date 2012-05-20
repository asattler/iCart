package de.bht.mme2.icart.ocp.recipe;

import java.io.Serializable;

public class Ingredient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9133045524463973061L;
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

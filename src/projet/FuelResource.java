package projet;

import java.util.ArrayList;

public class FuelResource extends Resource implements Fuel {
   
	private int value; 
	private CategoryFuelBurn category;
	
	 public FuelResource(String id, String name, int row, int stack, ArrayList<String> MinedBy, int value, CategoryFuelBurn categ) {
		super(id, name, row, stack, MinedBy);
		this.value=value;
		this.category=categ;
	 }

	 public CategoryFuelBurn getCategory() {
		 return category;
	 }

	 public int getValue() {
		 return value;
	 }
	
	 public String toString() {
		 return "Carburant " + super.toString() + " | Valeur : " + value + " | Categorie : " + category;
	 }
}

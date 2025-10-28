package projet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class RecipeComplex extends Recipe {
	
    private ArrayList<Component> produits;
    private ArrayList<Double> quantites_produits;
	public RecipeComplex(String id, String name, double time, String producers, ArrayList<Component> ingredients, ArrayList<Double> quantites, ArrayList<Component> produits, ArrayList<Double> quantites_out) {
		super(id, name, time, producers, ingredients, quantites);
		this.produits=produits;
		this.quantites_produits=quantites_out;
	}
    
	public ArrayList<Component> getProduits() {
  	  return produits;
    }
	
	public String toString() {
  	  String s_ingredients = "";
  	  for (int i = 0; i < ingredients.size(); i++) {
  		s_ingredients = s_ingredients + "  >> " + ingredients.get(i).getName() + " x" + quantites.get(i) + "\n";
  	  }
  	  
  	  String s_produits = "";
  	  for (int i = 0; i < produits.size(); i++) {
  		s_produits = s_produits + "  >> " + produits.get(i).getName() + " x" + quantites_produits.get(i) + "\n";
  	  }
  	  
  	  return "---- " + name + " produit par " + producers + " ----\nIngredients :" + s_ingredients + "Produits : " + s_produits;
    }

}
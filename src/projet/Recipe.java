package projet;

import java.util.ArrayList;

public class Recipe implements Comparable<Recipe> {
      protected String id;
      protected String name;
      protected double time;
      protected String producers;
      protected ArrayList<Component> ingredients;
      protected ArrayList<Double> quantites;
      
      public Recipe(String id,String name, double time, String producers, ArrayList<Component> ingredients, ArrayList<Double> quantites) {
    	  this.id=id;
    	  this.name=name;
    	  this.time=time;
    	  this.producers=producers;
    	  this.ingredients=ingredients;
    	  this.quantites=quantites;
      }
      
      public String getId() {
    	  return id;
      }
      
      public String getName() {
    	  return name;
      }
      
	  public int compareTo(Recipe r) {
		  int compare = name.compareTo(r.name);
		  return compare;
	  }
	  
	  //question 5
      public String getProducers() {
    	  return this.producers;
      }
      
      public ArrayList<Component> getIngredients() {
    	  return (ArrayList<Component>) ingredients.clone();
      }
	
      public ArrayList<Component> getProduits() {
    	  ArrayList<Component> retour = new ArrayList<>();
    	  retour.add(MainProjet.jeu.getComposantbyId(id));
    	  return retour;
      }
      
      public String toString() {
    	  String s = "";
    	  for (int i = 0; i < ingredients.size(); i++) {
    		  s = s + "  >> " + ingredients.get(i).getName() + " x" + quantites.get(i) + "\n";
    	  }
    	  return "---- " + name + " produit par " + producers + " ----\nIngredients :" + s + "Produit : >> " + getProduits().get(0).getName();
      }
      
}
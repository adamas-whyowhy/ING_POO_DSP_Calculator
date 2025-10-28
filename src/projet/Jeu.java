package projet;

import java.util.ArrayList;

public class Jeu {
	public ArrayList<Component> composants;
	public ArrayList<Component> composants_simples;
	public ArrayList<Resource> ressources;
	public ArrayList<Building> batiments;
	public ArrayList<Fuel> carburants;
	public ArrayList<FuelCompo> carburants_composants;
	public ArrayList<FuelResource> carburants_ressources;
	public ArrayList<Factory> usines;
	public ArrayList<FactoryCentrale> usines_centrales;
	public ArrayList<CentraleCarburant> centrales_carburant;
	public ArrayList<CentraleElectric> centrales_electriques;
	public ArrayList<Extractor> extracteurs;
	public ArrayList<ElectricExtractor> extracteurs_electriques;
	public ArrayList<ClassicExtractor> extracteurs_classiques;
	public ArrayList<Recipe> recettes;
	public ArrayList<RecipeComplex> recettes_complexes;
	public ArrayList<Recipe> recettes_usine;//question 5
	public ArrayList<Resource> resource_extractor;//question 6
	
	public Jeu() {
		composants = new ArrayList<>();
		composants_simples = new ArrayList<>();
		ressources =new ArrayList<>();
		batiments = new ArrayList<>();
		carburants = new ArrayList<>();
		carburants_ressources = new ArrayList<>();
		carburants_composants = new ArrayList<>();
		usines = new ArrayList<>();
		usines_centrales = new ArrayList<>();
		centrales_carburant = new ArrayList<>();
		centrales_electriques = new ArrayList<>();
		extracteurs = new ArrayList<>();
		extracteurs_electriques = new ArrayList<>();
		extracteurs_classiques = new ArrayList<>();
		recettes = new ArrayList<>();
		recettes_complexes = new ArrayList<>();
		recettes_usine= new ArrayList<>();
		resource_extractor=new ArrayList<>();
	}
	
	public Component getComposantbyId(String id) {
		Component sortie = null;
		for (Component c : composants) {
			if (c.getId().equals(id))
				sortie = c;
		}
		return sortie;
	}
	
	public Recipe getRecipebyId(String id) {
		Recipe sortie = null;
		for (Recipe c : recettes) {
			if (c.getId().equals(id))
				sortie = c;
		}
		return sortie;
	}
	
	public void ajouterComposantSimple(String id, String name, int row, int stack) {
		Component c = new Component(id, name, row, stack);
		composants.add(c);
		composants_simples.add(c);
	}
	
	public void ajouterCarburantCompo(String id, String name, int row, int stack, CategoryFuelBurn cat, int value) {
		Component c = new FuelCompo(id, name, row, stack, cat, value);
		composants.add(c);
		carburants.add((Fuel) c);
		carburants_composants.add((FuelCompo) c);
	}
	
	public void ajouterCarburantRessource(String id, String name, int row, int stack, CategoryFuelBurn cat, ArrayList<String> MinedBy, int value) {
		Component c = new FuelResource(id, name, row, stack, MinedBy, value, cat);
		composants.add(c);
		ressources.add((Resource) c);
		carburants.add((Fuel) c);
		carburants_ressources.add((FuelResource) c);
	}
	
	public void ajouterRessource(String id, String name, int row, int stack, ArrayList<String> minedBy) {
		Component c = new Resource(id,name,row,stack,minedBy);
		composants.add(c);
		ressources.add((Resource) c);
	}
	
    public void ajouterBatiment(String id, String name, int row, int stack) {
    	Component c = new Building(id,name,row,stack);
		composants.add(c);
    	batiments.add((Building) c);
	}
    
    public void ajouterUsine(String id, String name,int row, int stack, String type, double speed, double usage, double drain) {
    	Component c = new Factory(id, name, row, stack, type, speed, usage, drain);
    	composants.add(c);
    	batiments.add((Building) c);
    	usines.add((Factory) c);
    }
    
    public void ajouterCentraleElectrique(String id, String name, int row, int stack, String type, double speed, int value) {
    	Component c = new CentraleElectric(id, name, row, stack, type, speed, value);
    	composants.add(c);
    	batiments.add((Building) c);
    	usines_centrales.add((FactoryCentrale) c);
    	centrales_electriques.add((CentraleElectric) c);
    }
    
    public void ajouterCentraleCarburant(String id, String name, int row, int stack, CategoryFuelBurn categ, String type, double speed, int value, ArrayList<Fuel> liste_carburants) {
    	Component c = new CentraleCarburant(id, name, row, stack, categ, type, speed, value, liste_carburants);
    	composants.add(c);
    	batiments.add((Building) c);
    	usines_centrales.add((FactoryCentrale) c);
    	centrales_carburant.add((CentraleCarburant) c);
    }
    
    public void ajouterExtracteurElectrique(String id, String name, int row, int stack, String type, double speed, double usage, double drain) {
    	Component c = new ElectricExtractor(id, name, row, stack, type, speed, usage, drain);
    	composants.add(c);
    	batiments.add((Building) c);
    	extracteurs.add((Extractor) c);
    	extracteurs_electriques.add((ElectricExtractor) c);
    }
    
    public void ajouterExtracteurClassique(String id, String name, int row, int stack, double speedMining, String type, double speed, double usage, double drain) {
    	Component c = new ClassicExtractor(id, name, row, stack, speedMining, type, speed, usage, drain);
    	composants.add(c);
    	batiments.add((Building) c);
    	extracteurs.add((Extractor) c);
    	extracteurs_classiques.add((ClassicExtractor) c);
    }
    
    public void ajouterRecette(String id, String name, double time, String producers, ArrayList<Component> ingredients, ArrayList<Double> quantites) {
    	recettes.add(new Recipe(id, name, time, producers, ingredients, quantites));
    }
    
    public void ajouterRecetteComplexe(String id, String name, double time, String producers, ArrayList<Component> ingredients, ArrayList<Double> quantites_in, ArrayList<Component> produits, ArrayList<Double> quantites_out) {
    	Recipe r = new RecipeComplex(id, name, time, producers, ingredients, quantites_in, produits, quantites_out);
    	recettes.add(r);
    	recettes_complexes.add((RecipeComplex) r);
    }
    
    // question 5
    public void recette_usine(Recipe r) {
    	recettes_usine.add(r);
    }
    
    //question 6
    public void resource_extractor(Resource r) {
    	resource_extractor.add(r);
    }
}

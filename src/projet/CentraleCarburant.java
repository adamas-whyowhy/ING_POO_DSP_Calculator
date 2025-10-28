package projet;

import java.util.ArrayList;

public class CentraleCarburant extends FactoryCentrale {

	private CategoryFuelBurn category;
	private ArrayList<Fuel> carburants;

	public CentraleCarburant(String id, String name, int row, int stack, CategoryFuelBurn categ, String type, double speed, int value, ArrayList<Fuel> liste_carburants) {
		super(id, name, row, stack, type, speed, value);
		this.category=categ;
		this.carburants=liste_carburants;
	}
	
	public String toString() {
		String s = "---- [" + id + "] " + name + " ----\nCarburants possibles :\n";
		for (Fuel c : carburants) {
			s += "  >> " + c.toString() + " \n";
		}
		return s;
	}

}

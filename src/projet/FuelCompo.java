package projet;


public class FuelCompo extends Component implements Fuel {
     
	  private int value;
      private CategoryFuelBurn category;
   	  public FuelCompo(String id, String name, int row, int stack, CategoryFuelBurn category, int value) {
		super(id, name, row, stack);
		this.value=value;
		this.category=category;
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

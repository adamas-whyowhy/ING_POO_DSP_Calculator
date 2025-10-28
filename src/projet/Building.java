package projet;

public class Building extends Component {

	public Building(String id,String name,int row, int stack) {
		super(id, name, row, stack);
	}
	
	public String toString() {
		return "Batiment " + super.toString();
	}
	
}
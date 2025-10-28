package projet;

public class CentraleElectric extends FactoryCentrale {

	public CentraleElectric(String id, String name, int row, int stack, String type, double speed, int value) {
		super(id, name, row, stack, type, speed, value);
	}
	
	public String toString() {
		return "Centrale electrique " + super.toString();
	}

}

package projet;

public class FactoryCentrale extends Building{

	
	protected String type;
	protected double speed;
	protected int value;
	
	
	public FactoryCentrale(String id, String name, int row, int stack, String type, double speed, int value) {
		super(id, name, row, stack);
		this.type=type;
		this.speed=speed;
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "Usine centrale " + super.toString();
	}
	
}

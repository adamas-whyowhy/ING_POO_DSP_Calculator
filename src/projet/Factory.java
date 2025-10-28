package projet;

public class Factory extends Building {

	protected String type;
	protected double speed=1;
	protected double usage;
	protected double drain;
	
	
	public Factory(String id, String name,int row, int stack, String type, double speed, double usage, double drain) {
		super(id,name,row,stack);
		this.type=type;
		this.speed=speed;
		this.usage=usage;
		this.drain=drain;
	}
	
	public double getUsage() {
		return usage;
	}
	
	public double getDrain() {
		return drain;
	}
	
	public Component transformerComponent() {
		return null;
	}
	
	public String toString() {
		return "Usine " + super.toString();
	}
}

package projet;

public class ClassicExtractor extends Factory implements Extractor {
     
	private double speedMining;
	
	public ClassicExtractor(String id, String name, int row, int stack, double speedMining, String type, double speed, double usage, double drain) {
		super(id, name, row, stack, type, speed, usage, drain);
		this.speedMining=speedMining;
	}
	
	public String toString() {
		return "Exctracteur classique " + super.toString();
	}
	
}

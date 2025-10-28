package projet;

public class ElectricExtractor extends Factory implements Extractor {

	public ElectricExtractor(String id, String name, int row, int stack, String type, double speed, double usage, double drain) {
		super(id, name, row, stack, type, speed, usage, drain);
	}
	
	public String toString() {
		return "Extracteur electrique " + super.toString();
	}

}

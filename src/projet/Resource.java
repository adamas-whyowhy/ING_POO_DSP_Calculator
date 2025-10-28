package projet;
import java.util.ArrayList;

public class Resource extends Component {
	
      protected ArrayList<String> MinedBy;
      
      public Resource(String id, String name, int row, int stack, ArrayList<String> minedBy) {
		super(id, name, row, stack);
		this.MinedBy=minedBy;
		
      }

      public ArrayList<String> getMiners() {
    	  return MinedBy;
      }
      
      public String toString() {
    	  String s = "";
    	  for (String id_MinedBy : MinedBy) {
    		  s += MainProjet.jeu.getComposantbyId(id_MinedBy).getName() + " ";
    	  }
    	  return "Resource " + super.toString() + " | Mine par : " + s;
      }
}
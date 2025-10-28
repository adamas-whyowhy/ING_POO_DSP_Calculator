package projet;

public class Component implements Comparable<Component>{

	protected String id;
	protected String name;
	protected int row;
	protected int stack;
	
	
	public Component(String id,String name,int row, int stack) {
		this.id=id;
		this.name=name;
		this.row=row;
		this.stack=stack;
	}
     
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "ID : " + id + " | Nom : " + name + " | Row:" + row + " | Stack:"+stack;
	}

	public int compareTo(Component o) {
		int compare = name.compareTo(o.name);
		return compare;
	}
	
}

package csm.classes;

public class University {
	public int id;
	public String name;
	
	public University(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}

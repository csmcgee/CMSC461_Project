package csm.classes;

public class Course {
	public int id;
	public String name;
	
	public Course(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}

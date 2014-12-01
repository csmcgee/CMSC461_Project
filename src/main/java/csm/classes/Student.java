package csm.classes;

public class Student {
  public String first_name, last_name;
  public int id;

  public Student(int id, String first_name, String last_name) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
  }

  @Override
  public String toString() {
    return first_name + " " + last_name;
  }
}

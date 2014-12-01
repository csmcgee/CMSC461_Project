package csm.classes;

public class Book {
  public int quantity;
  public String ISBN, type, status, format;
  public double price;
  
  /**
   * Constructor used for Student Cart books
   */
  public Book(int quantity, String ISBN, String type, String status,
      String format, double price) {
    this.quantity = quantity;
    this.ISBN = ISBN;
    this.type = type;
    this.status = status;
    this.format = format;
    this.price = price;
  }

}

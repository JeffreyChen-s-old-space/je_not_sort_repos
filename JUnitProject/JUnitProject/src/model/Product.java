package model;
public class Product {
	private int id;
    private String type;
    private String title;
    private int price;    
    public Product(String type, String title, int price) {
    	id =0;
    	this.type = type;
        this.title = title;
        this.price = price;
    }
    public int getId() {
    	return id;
    }
    public String getType() {
        return type;
    }     
    public String getTitle() {
        return title;
    }    
    public int getPrice() {
       return price;
    }    
}
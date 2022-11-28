package model;
import java.util.*;
public class Store {
    private ArrayList<Product> items;    
    private int promote;
    private int discount;
    private int vipDiscount;    
    private int mass;
    public Store() {
        items = new ArrayList<Product>();
        promote = 0;
        discount=0;
        vipDiscount=0;        
        mass=0;
    }    
    //-----------------for Testing--------------
    public void init() {
    	addItem(new Product("Book", "Java", 50));
    	addItem(new Product("Book", "C++", 30));
    	addItem(new Product("Book", "C#", 40));
    	addItem(new Product("Book", "PHP", 20));    	
    	setPromote(0, 100, 0, 100);
    }
    public void read() {    	
        // file location ---------------------------------------------------    	
    	Data d = new Data("c:\\tmp\\workspace\\EC2\\store.txt");
        // file location ---------------------------------------------------
    	int len= d.getRow();
    	int promote, discount, mass, vipDiscount; 
    	String type, title;
    	int price;
    	promote = Integer.parseInt(d.read(len-1, 0));
    	discount = Integer.parseInt(d.read(len-1, 1));
    	mass = Integer.parseInt(d.read(len-1, 2));
    	vipDiscount = Integer.parseInt(d.read(len-1, 3));    	
    	setPromote(promote, discount, mass, vipDiscount);
    	for (int i=0; i<(len-1); i++) {
    		type = d.read(i, 0);
    		title = d.read(i,1);
    		price = Integer.parseInt(d.read(i,  2));
    		addItem(new Product(type, title, price));
    	}
    }
  //-----------------for Testing--------------
    public int getPromote() { return promote; }
    public int getDiscount() { return discount; }
    public int getMass() { return mass; }
    public int getVipDiscount() { return vipDiscount; }    
    public void setPromote(int promote, int discount, int mass, int vipDiscount) {
        this.promote=promote;    	
    	this.discount = discount;
    	this.mass = mass;
    	this.vipDiscount = vipDiscount;    	
    }
    public void addItem(Product item) {
        items.add(item);
    }
    public void removeItem(Product item) throws ProductNotFoundException {
        if (!items.remove(item)) {
            throw new ProductNotFoundException();
        }
    }
    public int getItemCount() {
        return items.size();
    }
    public Product getProduct(int i) {
    	return items.get(i);
    }
    public int SearchPriceByTitle(String key) {
    	Product p = null;
    	int length = items.size();
        int i=0, found=-1;
        while ((i < length) && (found==-1))    {
        	p = items.get(i);
            if (p.getTitle().compareTo(key)==0) found = i ;
            i++;
        }  // while
        if (found==-1 ) return found;
        else return p.getPrice();
    }
    public ProductCart SearchProductByTitle(String key) {
    	ProductCart results = new ProductCart();
    	Product p = null;
    	int length = items.size();
        int i=0, found=-1;
        while ((i < length) && (found==-1))    {
        	p = items.get(i);
            if (p.getTitle().indexOf(key)!=-1) results.addItem(p);
            i++;
        }  // while
        return results;
    } 
    
}
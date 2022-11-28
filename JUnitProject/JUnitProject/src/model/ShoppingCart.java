package model;
import java.util.*;
public class ShoppingCart {    
    private ArrayList<Product> items;
    private int vip;
    private int promote;
    private int discount;
    private int vipDiscount;    
    private int mass;
    public ShoppingCart(int vip) {
        items = new ArrayList<Product>();
        this.vip=vip;
        this.promote=0;        
        this.discount=100;
        this.vipDiscount=100;        
        this.mass=0;
    }    
    //---------for testing--------------------
    public void init(int promote, int discount, int mass, int vipDiscount) {
    	setPromote(promote, discount, mass, vipDiscount);
    }
    //-------------------------
    public void setPromote(int promote, int discount, int mass, int vipDiscount) {
        this.promote=promote;    	
    	this.discount = discount;
    	this.mass = mass;
    	this.vipDiscount = vipDiscount;    	
    }
    public int getPromoteTotal() {
    	int cost=0;
    	int thePrice = getTotal();
    	if (promote==0) return thePrice;
    	if (vip==1) {
            cost = (thePrice*vipDiscount)/100;
            if (cost>=mass) {
                cost = (cost*discount)/100;
            }
        }
    	return cost;
    }
    public int getTotal() {
        int balance = 0;
        for (Iterator<Product> i = items.iterator(); i.hasNext();) {
            Product item = (Product)i.next();
            balance += item.getPrice();
        }	
        return balance;
    }
    public void addItem(Product item) {
        items.add(item);
    }
    public void removeItem(Product item) throws ProductNotFoundException {
        if (!items.remove(item)) {
            throw new ProductNotFoundException();
        }
    }
    public void removeIndex(String ids) throws ProductNotFoundException {
    	// ----- bug -----
    	int id = 0;
    	try {
    		id = Integer.parseInt(ids);
        	Product p = items.get(id);
            if (!items. remove(p)) {
                throw new ProductNotFoundException();
            }    		
    	}
    	catch (Exception e) {}
    }
    public Product getItem(int i) {
    	return items.get(i);
    }
    public int getItemCount() {
        return items.size();
    }
    public void empty() {
        items.clear();
    }
}
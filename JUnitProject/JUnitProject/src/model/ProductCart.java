package model;

import java.util.ArrayList;

public class ProductCart {
    private ArrayList<Product> items;
    public ProductCart() {
        items = new ArrayList<Product>();
    }
    public void addItem(Product item) {
        items.add(item);
    }
    public Product getItem(int i) {
    	return items.get(i);
    }
    public int getItemCount() {
        return items.size();
    }    
}

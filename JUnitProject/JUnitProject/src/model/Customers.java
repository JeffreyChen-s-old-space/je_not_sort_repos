package model;
import java.util.*;
public class Customers {
	private ArrayList<Customer> customers;
    public Customers() {
        customers = new ArrayList<Customer>();
    }    
  //-----------------for Testing---------------------------------------------    
    public void init() {
        customers.add(new Customer("Tom", "1234", 100, 1));
        customers.add(new Customer("John", "5678", 200, 0));    	
    }
    public void read() {
        // file location ---------------------------------------------------    	
    	Data d = new Data("c:\\tmp\\workspace\\EC2\\customer.txt");
        // file location ---------------------------------------------------    	
    	int len= d.getRow();
    	String name, passwd;
    	int point, vip;
    	for (int i=0; i<len; i++) {
    		name = d.read(i, 0);    		
    		passwd = d.read(i,1);    		
    		point = Integer.parseInt(d.read(i, 2));
    		vip = Integer.parseInt(d.read(i, 3));    		
    		customers.add(new Customer(name, passwd, point, vip));
    	}    	
    }    
  //-----------------for Testing--------------------------------------------    
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public Customer getCustomer(String name, String passwd) {
        for (Iterator<Customer> i = customers.iterator(); i.hasNext();) {
            Customer customer = (Customer)i.next();
            if (customer.getName().compareTo(name)==0 && customer.getPasswd().compareTo(passwd)==0)
            		return customer;
        }	
        return null;
    }
}

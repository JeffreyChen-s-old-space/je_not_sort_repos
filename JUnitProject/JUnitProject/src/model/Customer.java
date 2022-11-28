package model;

public class Customer {
	private String name;
	private String passwd;
	private int pt;
	private int vip;
	public Customer(String name, String passwd, int pt, int vip) {
		this.name = name;
		this.passwd = passwd;
		this.pt=pt;
		this.vip=vip;
	}
	
	public void setPt(int newPt) { pt = newPt; }
	public int getPt() { return pt; }
	public int getVip() { return vip; }
    public String getName() { return name; }
    public String getPasswd() { return passwd; }
}

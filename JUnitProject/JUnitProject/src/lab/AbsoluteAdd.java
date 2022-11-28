package lab;
public class AbsoluteAdd {

	public int xAdd(int x, int y){
		if (x<0 && y<0)
			return -1*(x+y);
		if (x<0)
			return -1*x + y;
		if (y<0)
			return -1*x + y;
		
		return x+y;
	}

}

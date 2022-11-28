package lab;

public class MyPrimeNumber {

	public MyPrimeNumber() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isPrimeNumber(int number) {
		if (number == 1)
			return false;
		for(int i = 1; i < number; i++) {
			if( ( number % i ) == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

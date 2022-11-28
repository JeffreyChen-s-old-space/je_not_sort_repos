package lab;



public class ScoreFinder {

	public static void main(String[] args) {
	}
	
	public int search(int key, int[] score) {

		int mid=0, bottom = 0, top = score.length - 1, index = -1;

		while (bottom <= top && index == -1) {
			mid = (top + bottom) / 2;
			if (score[mid] == key) {				
				index = mid; //found
				return index;
			} // if part
			else if (score[mid] < key)
					bottom = mid + 1;
			else
					top = mid - 1;			
		} //while loop
		return index;
	}
}

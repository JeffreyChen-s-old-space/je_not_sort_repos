package lab;

public class LetterGrade {

	public LetterGrade() {
		// TODO Auto-generated constructor stub
	}

	public static char letterGrade(int score) {
		if (score <0 || score > 100)
			return 'X';
		else if (score>=90 && score <=100)
			return 'A';
		else if (score>=80 && score <90)
			return 'B';
		else if (score>=70 && score <80)
			return 'C';
		else if (score>=60 && score <70)
			return 'D';
		else
			return 'F';
		}


}

package algorithm.student;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.TreeMap;

public class CheckStudentGradeTest {

    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentDataArray;

    @BeforeEach
    public void setUP() {
        studentDataArray = new StudentData[0];
    }

    @Test
    public void testCheckStudentGrade() {
        String test = "97501 DS 80 DM 76 LA 63\n" +
                "97502 DS 53 DM 79 LA 98\n" +
                "97523 DS 83 DM 49 LA 78";
        StudentDataProcess studentDataProcess = new StudentDataProcess();
        try {
            studentDataArray = studentDataProcess.processRawString(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (StudentData studentData : studentDataArray) {
            if (studentData.getStudentNumber().equals("97502"))
                System.out.println(studentData.getStudentTreeMap().get("DS"));
        }
    }

}

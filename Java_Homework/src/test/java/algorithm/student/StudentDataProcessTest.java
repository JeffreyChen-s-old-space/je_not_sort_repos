package algorithm.student;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

public class StudentDataProcessTest {

    private static StudentData<String, TreeMap<String, Integer>, String, Integer>[] studentDataArray;

    @BeforeEach
    public void setUP() {
        studentDataArray = new StudentData[0];
    }

    @Test
    public void testStudentDataProcess() {
        String test = "97501 DS 80 DM 76 LA 63\n" +
                "97502 DM 79 LA 98\n" +
                "97503 LA 78\n" +
                "97504 \n" +
                "97505 DM 54 LA 60\n" +
                "97506 DS 51 DM 80 LA 48\n" +
                "97507 DS 76 DM 24 \n" +
                "97508 DS 41 DM 75 LA 88\n" +
                "97509 DS 22 \n" +
                "97510 DS 17 DM 60 LA 30 OS 43\n" +
                "97511 DS 32 DM 94 LA 79\n" +
                "97512 DS 67 DM 52 \n" +
                "97513 DS 60 DM 51 LA 54\n" +
                "97514 DS 14 DM 80 LA 42 OS 67 EN 69\n" +
                "97515 \n" +
                "97516 DS 72 DM 84 LA 77\n" +
                "97517 DS 80 DM 47 LA 76 EN 53\n" +
                "97518 DS 74 \n" +
                "97519 DS 60 DM 63 LA 79\n" +
                "\t97520 DS 48 DM 46\n";
        StudentDataProcess studentDataProcess = new StudentDataProcess();
        try {
            studentDataArray = studentDataProcess.processRawString(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentDataArray[0].printStudentData(studentDataArray);
    }

    @Test
    public void testStudentDataProcessWithOnlyStudentNumber() {
        String test = "97501 DS 80 DM 76 LA 63\n" +
                "97502\n" +
                "97523";
        StudentDataProcess studentDataProcess = new StudentDataProcess();
        try {
            studentDataArray = studentDataProcess.processRawString(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentDataArray[0].printStudentData(studentDataArray);
        System.out.println(Arrays.toString(studentDataArray));
    }

    @Test
    public void testStudentDataProcessWithNullString() {
        String test = "";
        StudentDataProcess studentDataProcess = new StudentDataProcess();
        try {
            studentDataArray = studentDataProcess.processRawString(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (studentDataArray.length > 0)
            studentDataArray[0].printStudentData(studentDataArray);
    }
}
package edu.virginia.cs.reviews;

import java.sql.SQLException;
import java.util.List;

public class MainMenu {
    static DataLayer dl;
    public static String[] student;

    public static void setStudent(String[] stud) {
        student = stud;
    }
    public static String[] getStudent() {
        return student;
    }

    static void setDL(DataLayer dataLayer) {
        dl = dataLayer;
    }
    public static void goToReviews() {
        AccessReviews.setDataLayer(dl);
    }
    public static boolean checkCourse(String course) {
        try {
            String[] courseInformation = course.split(" ");
            String department = courseInformation[0];
            String courseNumber = courseInformation[1];

            //Resource on how to check if a string only contains letters:
            // https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
            if (department.length() <= 4 && department.matches("[A-Z]+")){
                //department is within 4 capital letters.
                if (courseNumber.length() == 4) {
                    // will try to parse the course into an int, if there is an error. It is caught and returns false.
                    Integer.parseInt(courseNumber);
                    return true;
                }
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }
    public static List<String> getCourseList() throws SQLException {
        return dl.listCourses();
    }
    public static boolean checkIfCourseInDatabase(String course) {
        return dl.isInDataBase(course);
    }

    public static void addReview(String course, String message, String[] student, Integer rating) {
        dl.addRating(course, message, student, rating);
    }

    public static boolean alreadyReviewed(String course, String[] student) {
        return dl.alreadyReviewed(course, student);
    }

    public static void addCourse(String course) {
        dl.addCourse(course);
    }
}

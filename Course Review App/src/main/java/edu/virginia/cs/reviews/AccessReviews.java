package edu.virginia.cs.reviews;

import java.sql.SQLException;
import java.util.List;

public class AccessReviews {

    static DataLayer dl;

    public static void setDataLayer(DataLayer data) {
        dl = data;
    }

    public static List<String> getReviews(String course) throws SQLException {
        return dl.getReviewsForCourse(course);
    }
    public static double getAverage() {
        return dl.getAverage();
    }

}

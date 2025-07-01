package edu.virginia.cs.reviews;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {
    Connection connection;
    public static double average;
    public void connect() {

        String databaseName ="Reviews.sqlite3";
        String databaseURL = "jdbc:sqlite:" + databaseName;
        try{
            Class.forName("org.sqlite.JDBC");
            if(connection != null) {
                if(!connection.isClosed())
                    throw new IllegalStateException("Already Connected to database");
            }
            else {
                File databaseFile = new File(databaseName);
                boolean isDatabaseSetup = databaseFile.exists();
                connection = DriverManager.getConnection(databaseURL);
                if (!isDatabaseSetup) {
                    setupDatabase();
                }
            }
        } catch(ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setupDatabase() {
        System.out.println("No database exists\nRunning first time setup to create tables and populate with sample data");
        try {
            Statement statement = connection.createStatement();

            String createStudentTable = """
                    create table Students(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name varchar(256) unique not null,
                    password varchar(256) not null);""";
            statement.execute(createStudentTable);

            String createCourseTable = """
                    create table Courses(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    department varchar(4) not null,
                    catalog_number varchar(4) not null);""";
            statement.execute(createCourseTable);

            String createReviewTable = """
                    create table Reviews(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id int not null,
                    course_id int not null,
                    message text not null,
                    rating int not null check(rating >= 1 and rating <= 5),
                    foreign key(student_id) references Students(id) on delete cascade,
                    foreign key(course_id) references Courses(id) on delete cascade);""";
            statement.execute(createReviewTable);
            statement.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        populateTablesWithSampleData();
    }

    public void populateTablesWithSampleData(){
        //3-5 [5] Users (at least 3 of whom have made one or more reviews)

        try {
            String insertStudent = "INSERT INTO Students (name, password) VALUES (?, ?)";
            for (int i = 1; i <= 5; i++) {
                PreparedStatement statement = connection.prepareStatement(insertStudent);
                statement.setString(1, "User" + i);
                statement.setString(2, "password" + i);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Error adding Students to tables: " + e.getMessage());
        }
        System.out.println("just added students");
        //2-5 [5] Courses (with at least 1 having more than 1 review)
        try{
            String insertCourse = "INSERT INTO Courses (department, catalog_number) VALUES (?, ?)";

            for (int i = 0; i <5; i++)
            {
                PreparedStatement statement = connection.prepareStatement(insertCourse);
                statement.setString(1, "ANTH");
                statement.setString(2, "101" + i);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Error adding Courses to tables: " +  e.getMessage());
        }
        //4-10 [5] reviews
        try {
            String insertReview = "INSERT INTO Reviews (student_id, course_id, message, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertReview);
            // Reviews for course 1
            statement.setInt(1, 1);
            statement.setInt(2, 1);
            statement.setString(3, "This course was decent.");
            statement.setInt(4, 5);
            statement.executeUpdate();

            statement.setInt(1, 2);
            statement.setInt(2, 1);
            statement.setString(3, "This course was terrible.");
            statement.setInt(4, 1);
            statement.executeUpdate();

            statement.setInt(1, 3);
            statement.setInt(2, 1);
            statement.setString(3, "It was a fun course");
            statement.setInt(4, 3);
            statement.executeUpdate();

            // Reviews for course 2
            statement.setInt(1, 2);
            statement.setInt(2, 2);
            statement.setString(3, "I love this teacher!");
            statement.setInt(4, 5);
            statement.executeUpdate();

            statement.setInt(1, 3);
            statement.setInt(2, 2);
            statement.setString(3, "I thought it had too much homework");
            statement.setInt(4, 1);
            statement.executeUpdate();

            // Reviews for course 3
            statement.setInt(1, 4);
            statement.setInt(2, 3);
            statement.setString(3, "Im a huge fan of this course");
            statement.setInt(4, 4);
            statement.executeUpdate();

            statement.setInt(1, 5);
            statement.setInt(2, 3);
            statement.setString(3, "I didnt like this course very much.");
            statement.setInt(4, 3);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error adding data to tables: " + e.getMessage());
        }
    }

    public void printData()
    {
        Statement statement;
        // print students
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Students;");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                System.out.println(id + ", " + name + ", " + password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //print courses
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String department = resultSet.getString("department");
                String catalogNumber = resultSet.getString("catalog_number");
                System.out.println(id + ", " + department + ", " + catalogNumber);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //print reviews
        try {
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Reviews");
            while (results.next()) {
                int id = results.getInt("id");
                int studentId = results.getInt("student_id");
                int courseId = results.getInt("course_id");
                String message = results.getString("message");
                int rating = results.getInt("rating");
                System.out.printf("%d, %d, %d, %s, %d\n", id, studentId, courseId, message, rating);
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean isValidLogin(String username, String password) {
        try {
            String stringSearch = "SELECT * FROM Students WHERE name = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(stringSearch);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            boolean isValid = resultSet.next();

            statement.close();

            return isValid;

        } catch(SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
    public boolean isInDataBase(String course) {
        String[] courseInformation = course.split(" ");
        String departmentName = courseInformation[0];
        String courseNumber = courseInformation[1];
        try {
            String stringSearch = "SELECT * FROM Courses WHERE department = ? AND catalog_number = ?";
            PreparedStatement statement = connection.prepareStatement(stringSearch);
            statement.setString(1, departmentName);
            statement.setString(2, courseNumber);

            ResultSet resultSet = statement.executeQuery();

            boolean isValid = resultSet.next();

            statement.close();

            return isValid;

        } catch(SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
    public boolean studentAlreadyExists(String username) {
        try {
            String stringSearch = "SELECT * FROM Students WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(stringSearch);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            boolean isValid = resultSet.next();

            statement.close();

            return isValid;

        } catch(SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
    public boolean alreadyReviewed(String course, String[] student) {
        String[] courseInformation = course.split(" ");
        String departmentName = courseInformation[0];
        String courseNumber = courseInformation[1];

        String studentName = student[0];
        String studentPassword = student[1];

        try {
            Statement statement;
            statement = connection.createStatement();
            ResultSet courseSet = statement.executeQuery("SELECT id FROM Courses where department = '" + departmentName + "' AND catalog_number = '" + courseNumber + "'");
            int courseId = courseSet.getInt("id");

            ResultSet studSet = statement.executeQuery("SELECT id FROM Students where name = '" + studentName + "'AND password = '" + studentPassword + "'");
            int studId = studSet.getInt("id");


            String stringSearch = "SELECT * FROM Reviews WHERE student_id = ? AND course_id = ?";
            PreparedStatement statement1 = connection.prepareStatement(stringSearch);
            statement1.setInt(1, studId);
            statement1.setInt(2, courseId);

            ResultSet resultSet = statement1.executeQuery();
            boolean isValid = resultSet.next();
            statement.close();
            return isValid;
        } catch(SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public double getAverage() {
        return average;
    }

    public List<String> getReviewsForCourse(String course) throws SQLException {
        List<String> reviews = new ArrayList<>();
        String[] courseInformation = course.split(" ");
        String departmentName = courseInformation[0];
        String courseNumber = courseInformation[1];

        Statement statement;
        statement = connection.createStatement();
        ResultSet courseSet = statement.executeQuery("SELECT id FROM Courses where department = '" + departmentName + "' AND catalog_number = '" + courseNumber + "'");
        int courseID = courseSet.getInt("id");
        try {
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Reviews WHERE course_id = '" + courseID + "'");
            double sum = 0;
            int values = 0;
            while (results.next()) {
                String message = results.getString("message");
                sum += results.getInt("rating");
                reviews.add(message);
                values++;
            }
            average = sum/values;
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reviews;
    }

    public List<String> listCourses() {
        // figured out how to filter distinct SQL with aliases through here:
        // https://www.w3schools.com/sql/sql_distinct.asp
        List<String> courseList = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            String query = "SELECT DISTINCT c.department, c.catalog_number FROM Courses c";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String department = resultSet.getString("department");
                int catalogNumber = resultSet.getInt("catalog_number");
                String course = department + " " + catalogNumber;
                courseList.add(course);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return courseList;
    }

    public void addRating(String course, String message, String[] student, Integer rating) {
        // had to figure out JOIN and aliases with https://www.w3schools.com/sql/sql_join.asp
        String[] courseInformation = course.split(" ");
        String departmentName = courseInformation[0];
        String courseNumber = courseInformation[1];

        String studentName = student[0];
        String studentPassword = student[1];

        PreparedStatement insertReviewStatement;

        try {
            // Insert the review with JOIN statements to obtain the course and student IDs
            String insertReview = "INSERT INTO Reviews (student_id, course_id, message, rating) " +
                    "SELECT s.id, c.id, ?, ? " +
                    "FROM Students s " +
                    "JOIN Courses c ON c.department = ? AND c.catalog_number = ? " +
                    "WHERE s.name = ? AND s.password = ?";
            insertReviewStatement = connection.prepareStatement(insertReview);
            insertReviewStatement.setString(1, message);
            insertReviewStatement.setInt(2, rating);
            insertReviewStatement.setString(3, departmentName);
            insertReviewStatement.setString(4, courseNumber);
            insertReviewStatement.setString(5, studentName);
            insertReviewStatement.setString(6, studentPassword);
            insertReviewStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding data to tables: " + e.getMessage());
        }
    }

    public void addCourse(String course) {
        String[] courseInformation = course.split(" ");
        String departmentName = courseInformation[0];
        String courseNumber = courseInformation[1];
        try{
            String insertCourse = "INSERT INTO Courses (department, catalog_number) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertCourse);
            statement.setString(1, departmentName);
            statement.setString(2, courseNumber);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error adding Courses to tables: " +  e.getMessage());
        }
    }

    public void addStudent(String username, String password1)
    {
        try{
            String insertStudent = "INSERT INTO Students (name, password) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertStudent);
            statement.setString(1, username);
            statement.setString(2, password1);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error adding Student: " + e.getMessage());
        }
    }
}

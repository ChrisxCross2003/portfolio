package edu.virginia.cs.reviews;

public class Login {

    static DataLayer dl = null;

    public static void setDataLayer(DataLayer datalayer) {
        dl = datalayer;
    }

    public boolean checkReturningUser(String username, String password) {
        return dl.isValidLogin(username,password);
    }

    public static void goToMainMenu(String username, String password) {
        MainMenu.setDL(dl);
        String[] student = new String[] {username, password};
        MainMenu.setStudent(student);
    }
    public static boolean existingUser(String username) {
        return dl.studentAlreadyExists(username);
    }

    public static void addStudent(String username, String password)
    {
        dl.addStudent(username, password);
    }
}

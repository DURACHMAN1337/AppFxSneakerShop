package Denis;

public class User {
    private String firstName;
    private String surname;
    private String userName;
    private String password;
    private String location;
    private String gender;
    private String priority;
    public static String currentPriority = "";

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User(String firstName, String surname, String userName, String password, String location, String gender) {
        this.firstName = firstName;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.gender = gender;
        this.priority = "low";
    }

    public User(String firstName, String surname, String userName, String password, String location, String gender, String priority) {
        this.firstName = firstName;
        this.surname = surname;
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.gender = gender;
        this.priority = priority;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

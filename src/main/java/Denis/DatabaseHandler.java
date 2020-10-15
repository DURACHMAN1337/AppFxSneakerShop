package Denis;

import java.sql.*;

public class DatabaseHandler extends Configs{

    Connection dbConnection;

    public  Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_FIRSTNAME + "," + Const.USERS_SURNAME + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_LOCATION + "," + Const.USERS_GENDER + "," + Const.USERS_PRIORITY + ")" +
                "VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getFirstName());
            prSt.setString(2, user.getSurname());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.setString(7, user.getPriority());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());


            resSet = prSt.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void downPriority(String name){
        String SQL = "UPDATE " + Const.USER_TABLE + " SET priority = 'low' WHERE username = " + "'" + name + "';";
        DatabaseHandler dbHandler = new DatabaseHandler();
        try {
            Connection connection = dbHandler.getDbConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void upPriority(String name){
        String SQL = "UPDATE " + Const.USER_TABLE + " SET priority = 'high' WHERE username = " + "'" + name + "';";
        DatabaseHandler dbHandler = new DatabaseHandler();
        try {
            Connection connection = dbHandler.getDbConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username,String id) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Connection connection = null;
        try {
            connection = dbHandler.getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (!username.isEmpty()) {
            String SQL = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + " = '" + username + "';";
            try {
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (!id.isEmpty()){
            String SQL = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + " = '" + id + "';";
            try {
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }



}

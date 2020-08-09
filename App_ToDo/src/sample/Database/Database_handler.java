package sample.Database;

import sample.Model.Tasks;
import sample.Model.User;

import java.sql.*;

public class Database_handler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);


        return dbConnection;
    }


    public void signUpUser(User user)  {

        String insert = "INSERT INTO " + Const.Users_table + "(" + Const.user_fn
                + "," + Const.user_ln + ","+ Const.user_gender+ "," + Const.user_un+ ","
                + Const.user_pass + "," + Const.user_loc
                 + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getGender());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getLocation());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void deleteTask( int taskId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.Task_table + " WHERE "+
               Const.task_id + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, taskId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ResultSet checkUser(User user) {
        ResultSet resultSet = null;


        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.Users_table + " WHERE "
                    + Const.user_un + "=?" + " AND " + Const.user_pass
                    + "=?";

            // select all from users where username="paulo" and password="password"

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Please enter your credentials");

        }


        return resultSet;
    }
    public int getTaskno(int userId) throws SQLException, ClassNotFoundException {
        String query="Select Count(*) from "+ Const.Task_table+" where "+Const.user_id+"=?;";
        PreparedStatement ps=getDbConnection().prepareStatement(query);
        ps.setInt(1,userId);
        ResultSet rs=ps.executeQuery();
//        while(rs.next())
//        {
//            return rs.getInt(1);
//        }
        return rs.getInt(1);
    }

    public void insertTask(Tasks tasks) {

        String insert = "INSERT INTO " + Const.Task_table + "(" + Const.user_id + ","
                + Const.task_task + "," + Const.task_date + "," + Const.task_desc + ")"
                + "VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);


            System.out.println("From DBHandler UserId: " + tasks.getUserId());

            preparedStatement.setInt(1, tasks.getUserId());
            preparedStatement.setString(2, tasks.getTask_work());
            preparedStatement.setTimestamp(3, tasks.getDatecreated());
            preparedStatement.setString(4, tasks.getDescription());



            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public ResultSet getTasksByUser(int userId) {

        ResultSet resultTasks = null;

        String query = "SELECT * FROM " + Const.Task_table + " WHERE "
                + Const.user_id + "=?";
        //System.out.println(query);
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

            preparedStatement.setInt(1, userId);


            resultTasks = preparedStatement.executeQuery();
            System.out.println(resultTasks);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return resultTasks;
    }


}

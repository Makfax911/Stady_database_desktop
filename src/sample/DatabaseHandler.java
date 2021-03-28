package sample;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    //підключення до бд
    public Connection getDbConnection()
        throws ClassNotFoundException,SQLException{
        String connectionString="jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection=DriverManager.getConnection(connectionString,
                dbUser,dbPass);
//перевірка підключення до бд
        System.out.println("Connecting to a selected database...");
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/notebook_sherm", "root", "root1");
        System.out.println("Connected database successfully...");

        return dbConnection;

    }
//додавання записів в бд
    public void  insertTask(String task) throws SQLException,ClassNotFoundException{
       String sql = "INSERT INTO `text` (task) VALUES (?)";

        PreparedStatement prSt=getDbConnection().prepareStatement(sql);
        prSt.setString(1,task);
        prSt.executeUpdate();
    }
//метод для отриманя записів з бд
    public ArrayList<String> getTasks()throws SQLException,ClassNotFoundException{
        String sql="SELECT * FROM text ORDER BY 'id' DESC ";
        Statement statement=getDbConnection().createStatement();
        ResultSet res=statement.executeQuery(sql);
        ArrayList<String> task=new ArrayList<>();
        while (res.next()){
            task.add(res.getString("task"));

        }
        return task;
    }
// метод для видаленя записів з бд
    public void delete_bd(String task)throws SQLException,ClassNotFoundException{
        String del="DELETE FROM text ORDER BY task LIMIT 1 ";

        PreparedStatement prSt=getDbConnection().prepareStatement(del);
      //  prSt.setString(0,task);
        prSt.executeUpdate();

        System.out.println("Record deleted successfully");

    }}


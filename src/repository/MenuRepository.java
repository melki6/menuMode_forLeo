package repository;

import com.sun.xml.internal.ws.util.xml.ContentHandlerToXMLStreamWriter;
import domain.Plate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private void closeJDBCConnection(Connection conn){
        try {
            conn.close();
        }catch (SQLException e){
            System.out.println("Problems during close connection");
        }
    }

    private Connection makeJDBCConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentsystemclass?serverTimezone=UTC", "root", "root");
            if (connection != null) {
                log("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("MySQL Connection Failed!");
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }

    public void addPlate(Plate plate){
        Connection connection = makeJDBCConnection();

        try {
            String sql = "INSERT INTO plates(id,name,type,drink,idListIngridients) VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, plate.getPlateId());
            pstmt.setString(2, plate.getName());
            pstmt.setString(3, plate.getType());
            pstmt.setString(4, plate.getDrink());
            pstmt.setInt(5, plate.getIdListIngridients());

            pstmt.executeUpdate();

        } catch (SQLException e){
            log(e.getMessage());
        } finally {
            closeJDBCConnection(connection);
        }
    }

    public void modifyPlate(Plate plate) throws Exception{
        Connection connection = makeJDBCConnection();

        try{
            String sql = "UPDATE plates set name = ?, type = ?, drink = ?, idListIngridients = ? WHERE plates.id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, plate.getName());
            pstmt.setString(2, plate.getType());
            pstmt.setString(3, plate.getDrink());
            pstmt.setInt(4, plate.getIdListIngridients());
            pstmt.setInt(5, plate.getPlateId());

            if(pstmt.executeUpdate() == 0){
                throw new Exception("The Update wasn't executed!");
            }
        } catch (SQLException e){
            log(e.getMessage());
        } finally {
            closeJDBCConnection(connection);
        }

    }

    public void removePlate(Plate plate) throws Exception {
        Connection connection = makeJDBCConnection();

        try{

            String sql = "DELETE FROM plates WHERE plates.id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, plate.getPlateId());

            if(pstmt.executeUpdate() == 0){
                throw new Exception("The delete wasn't executed!");
            }
        } catch (SQLException e){
            log(e.getMessage());
        }

    }

    public Plate findPlateById(int id){
        Plate plate = null;

        Connection connection = makeJDBCConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM plates WHERE plates.id=?");
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String drink = resultSet.getString("drink");
                int idlistIngridients = resultSet.getInt("idListIngridients");

                plate = new Plate(id, name, type, drink, idlistIngridients);
            }
        } catch(SQLException e){
            log(e.getMessage());
        } finally {
            closeJDBCConnection(connection);
        }

        return plate;

    }


    public List<Plate> findAll() {
        List<Plate> plates = new ArrayList<Plate>();
        Connection conn = makeJDBCConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM plates");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String drink = resultSet.getString("drink");
                int idlistIngridients = resultSet.getInt("idListIngridients");

                plates.add(new Plate(id, name, type, drink, idlistIngridients));
            }

        } catch (SQLException e) {
            log(e.getMessage());
        } finally {
            closeJDBCConnection(conn);
        }
        return plates;
    }
}
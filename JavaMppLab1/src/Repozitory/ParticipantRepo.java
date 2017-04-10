package Repozitory;

import Domain.Participant;
import Utils.Conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class ParticipantRepo implements iRepo<Participant,Integer> {
    public Connection conn = null;
    public ParticipantRepo( ){
        try {
            // db parameters
            // Class.forName("org.sqlite.JDBC");
            //  String url = "jdbc:sqlite:C:/Users/Bogdan/Documents/Visual Studio 2015/Projects/P3-Mpp-Lab1/P3-Mpp-Lab1/bin/Debug/Lab1Mpp.db";
            // create a connection to the database
            conn = Conn.getConn();

            System.out.println("Connection to SQLite has been established.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Participant item) {

        String insert = String.format("INSERT INTO participanti (varsta , nume) VALUES ('%s', '%s');" , item.get_varsta(), item.get_nume());
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insert);
        } catch (SQLException ex) {
            System.out.println("Insert error " + ex);
        }
    }

    @Override
    public void delete(Integer key) {

        String delString = "delete from participanti where id = " + key;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(delString);
        } catch (SQLException ex) {
            System.out.println("Delete error error " + ex);

        }

    }

    @Override
    public void update(Participant newItem) {

        String upString;
        upString = String.format ("UPDATE participanti SET varsta = '%s', nume = '%s' WHERE id = %d", newItem.get_varsta(),newItem.get_varsta(),newItem.get_id());
        try(Statement stmt=conn.createStatement()){
            stmt.executeUpdate(upString);
        }catch(SQLException ex){

            System.err.println("Update error: " + ex.getMessage());
        }
    }

    @Override
    public List<Participant> getAll() {
        ArrayList<Participant> list  = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Participanti");
            while(rs.next()){
                Participant e = new Participant(rs.getString("Nume"),rs.getString("Varsta"));
                e.set_id(rs.getInt("id"));
                list.add(e);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;

    }

}

package Repozitory;

import Domain.Proba;
import Utils.Conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class ProbaRepo implements iRepo<Proba,Integer> {


    public Connection conn = null;
    public ProbaRepo(){
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
    public void add(Proba item) {

        String insert = String.format("INSERT INTO probe (distanta,stil,nr_participanti) VALUES (%d, '%s',%d);" , item.get_distanta(),item.get_stil(),item.get_nr_participanti());
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insert);
        } catch (SQLException ex) {
            System.out.println("Insert error " + ex);
        }
    }

    @Override
    public void delete(Integer key) {
        String delString = "delete from Probe where id = " + key;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(delString);
        } catch (SQLException ex) {
            System.out.println("Delete error error " + ex);

        }
    }

    @Override
    public void update(Proba newItem) {
        String upString;
        upString = String.format ("UPDATE probe SET distanta = %d, stil = '%s' ,nr_participanti = %d WHERE id = %d", newItem.get_distanta(),newItem.get_stil(),newItem.get_nr_participanti(),newItem.get_id());
        try(Statement stmt=conn.createStatement()){
            stmt.executeUpdate(upString);
        }catch(SQLException ex){

            System.err.println("Update error: " + ex.getMessage());
        }
    }

    @Override
    public ArrayList<Proba> getAll() {
        ArrayList<Proba> list  = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM probe");
            while(rs.next()){
                Proba e = new Proba(rs.getInt("distanta"), rs.getString("Stil"), rs.getInt("nr_participanti"));
                list.add(e);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;

    }

}

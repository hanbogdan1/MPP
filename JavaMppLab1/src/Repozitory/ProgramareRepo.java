package Repozitory;

import Domain.Programare;
import Utils.Conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class ProgramareRepo implements iRepo<Programare,Integer> {

    public Connection conn = null;
    public ProgramareRepo() {
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
    public void add(Programare item) {
        String insert = String.format("INSERT INTO programari (id_proba, id_participant) VALUES (%d, %d);", item.get_id_proba(), item.get_id_participant());
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insert);
        } catch (SQLException ex) {
            System.out.println("Insert error " + ex);
        }
    }

    @Override
    public void delete(Integer key) {
        String delString = "delete from programari where id = " + key;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(delString);
        } catch (SQLException ex) {
            System.out.println("Delete error error " + ex);

        }
    }

    @Override
    public void update(Programare e) {
        String upString;
        upString = String.format ("UPDATE programari SET id_participant = %d, id_proba = %d WHERE id = %d", e.get_id_participant(),e.get_id_proba(),e.get_id());
        try(Statement stmt=conn.createStatement()){
            stmt.executeUpdate(upString);
        }catch(SQLException ex){

            System.err.println("Update error: " + ex.getMessage());
        }
    }

    @Override
    public List<Programare> getAll() {
        ArrayList<Programare> list  = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM programari");
            while(rs.next()){
                Programare e = new Programare(rs.getInt("id_participant"), rs.getInt("id_proba"));
                e.set_id(rs.getInt("id"));
                list.add(e);
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;

    }
    public void add_part_proba(String nume, int varsta , String Stil, int distanta) throws Exception {
        int id_participant;
        int id_proba;
        Statement stmt = conn.createStatement(
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        System.out.println();

        ResultSet rs = stmt.executeQuery(String.format("select id from participanti where nume = '%s' and varsta = %d", nume,varsta));
        if (!rs.isBeforeFirst())
            throw new Exception("Participantul introdus nu exista ! \n");
        else
            id_participant = rs.getInt("id");

        ResultSet rs2 = stmt.executeQuery(String.format("select id from probe where stil = '%s' and distanta = %d", Stil,distanta));
        if (!rs2.isBeforeFirst())
            throw new Exception("Proba introdus nu exista ! \n");
        else
            id_proba = rs.getInt("id");

        stmt.executeUpdate("update 'probe' set nr_participanti = nr_participanti + 1 where id =" +id_proba);

        this.add(new Programare(id_participant,id_proba));
    }


}

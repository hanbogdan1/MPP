package Repozitory;

import Domain.Admin;
import Domain.Participant;
import Utils.Conn;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class AdminRepo {
    public Connection conn = null;

    public AdminRepo() {
        try {
            // db parameters
           // Class.forName("org.sqlite.JDBC");
          //  String url = "jdbc:sqlite:C:/Users/Bogdan/Documents/Visual Studio 2015/Projects/P3-Mpp-Lab1/P3-Mpp-Lab1/bin/Debug/Lab1Mpp.db";
            // create a connection to the database
            conn = Conn.getConn();

            System.out.println("Connection to SQLite has been established.");

        } catch (Exception e) {

            System.out.println(e.getMessage() + "error");
        }
    }


    public void add(Admin e) {
        String insert = String.format("INSERT INTO admini (Nume , Parola , Usernam) VALUES ('%s','%s', '%s');", e.get_nume(), e.get_parola(), e.get_username());
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(insert);
        } catch (SQLException ex) {
            System.out.println("Insert error " + ex);
        }
    }


    public void update(Admin item) {
        String upString;
        upString = String.format("update admini set Nume= '%s' , Username = '%s', parola = '%s'  where id = %d", item.get_nume(), item.get_username(), item.get_parola(), item.get_id());
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(upString);
        } catch (SQLException ex) {

            System.err.println("Update error: " + ex.getMessage());
        }
    }


    public void delete(Admin e) {
        String delString = "delete from test where id = " + e.get_id();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(delString);
        } catch (SQLException ex) {
            System.out.println("Delete error error " + ex);

        }
    }


    public ArrayList<Admin> get_all() {
        ArrayList<Admin> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Admini");
            while (rs.next()) {
                Admin e = new Admin(rs.getString("Username"), rs.getString("Name"), rs.getString("Parola"));
                e.set_id(rs.getInt("id"));
                list.add(e);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;

    }


    public boolean check_user(String s, String s1) throws Exception {
        if (s == "" || s1 == "")
            throw new Exception("Completati campurile de login !");
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            String cmd = String.format("select * from Admini WHERE username = '%s' AND parola = '%s'", s, s1);

            ResultSet rs = stmt.executeQuery(cmd);
            if (!rs.isBeforeFirst()) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.err.println("login error: " + e.getMessage());
        }
        return false;

    }


    public ArrayList<Participant> get_random(String s1, String s2) {
        ArrayList<Participant> list = new ArrayList<Participant>();
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);
            System.out.println(String.format("select id, nume, varsta from (select * from probe where probe.stil = '%s' and probe.distanta = %s ) as A inner join Programari B on A.id = B.id_proba inner join Participanti C on c.id = B.id_participant", s1, s2));
            ResultSet rs = stmt.executeQuery(String.format("select C.id, C.nume, C.varsta from (select * from probe where probe.stil = '%s' and probe.distanta = %s ) as A inner join Programari B on A.id = B.id_proba inner join Participanti C on c.id = B.id_participant", s1, s2));
            while (rs.next()) {
                Participant e = new Participant(rs.getString("C.Nume"), rs.getString("C.Varsta"));
                e.set_id(rs.getInt("C.Id"));
                list.add(e);

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }




}

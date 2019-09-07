package com.vignesh.membership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database
 */
public class Database {
    private static Connection con = null;

    public Database(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:membership.db");
   
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
         System.out.println("Opened database successfully");
    }
    
    public List<Member> fetchAllMembers(){        
        List<Member> members = new ArrayList<>();
        String sql = "SELECT _id, name FROM member";
        try (Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                members.add(new Member(rs.getInt("_id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
   }

   public void deleteByID(int id) {
       String sql = "DELETE FROM member WHERE _id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMembers(List<Member> members) {
        String query = "INSERT INTO member VALUES " ;
        List<String> tuples = new ArrayList<>();
        for (Member m : members) {
            tuples.add("(" + Integer.toString(m.getId()) + ", '" + m.getName() + "')");
        }
        query += String.join(", ", tuples);
        query += ";";
        
         try (PreparedStatement pstmt = con.prepareStatement(query)) {
             pstmt.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
     }

     public void addRelationships(List<Relationship> relationships) {
        String query = "INSERT INTO relationship VALUES " ;
        List<String> tuples = new ArrayList<>();
        for (Relationship r : relationships) {
            tuples.add("(" + Integer.toString(r.getId()) + ", " + 
                            Integer.toString(r.getFrom()) +  ", " + 
                            Integer.toString(r.getTo())  +  ", " +  
                            Integer.toString(r.getType()) + ")");
        }
        query += String.join(", ", tuples);
        query += ";";
        
         try (PreparedStatement pstmt = con.prepareStatement(query)) {
             pstmt.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
     }
}
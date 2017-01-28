import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.io.IOException;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Database {

    /**
     * storeTree
     * 
     * @param {byte[]} byteTree - Binary version of tree to store in database
     * 
     * @return {int} - Database generated id for stored tree
     */
    public static int storeTree(byte[] byteTree) {

      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();

      try {
        // Connect to PostgreSQL
        connection = DatabaseUrl.extract().getConnection();

        // Create table and schema
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS trees (id SERIAL PRIMARY KEY, tree BYTEA NOT NULL)");

        // Store Tree
        PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement("INSERT INTO trees (tree) VALUES (?) RETURNING id");
        pstmt.setBytes(1, byteTree);
        pstmt.execute();

        // Retrieve id of stored tree
        ResultSet rs = pstmt.getResultSet();

        int returnedId = 0;

        if (rs.next()) {
          returnedId = rs.getInt(1);
        }

        return returnedId;

      } catch (Exception e) {
        e.printStackTrace();

      } finally {
        if (connection != null) {
          try {
            connection.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
      }
    }
    return 0;
  }


  public static byte[] loadTree(int id) {

    Connection connection = null;
    Map<String, Object> attributes = new HashMap<>();

    try {
      // Connect to PostgreSQL
      connection = DatabaseUrl.extract().getConnection();

      // Load Tree
      PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement("SELECT tree FROM trees WHERE id = ?");
      pstmt.setInt(1, id);
      pstmt.execute();

      // Retrieve tree
      ResultSet rs = pstmt.getResultSet();
      System.out.println(rs.toString());

      byte[] returnedTree = new byte[0];

      if (rs.next()) {
        returnedTree = rs.getBytes(1);
        System.out.println("HERE");
      }

      return returnedTree;

    } catch (Exception e) {
        e.printStackTrace();

      } finally {
        if (connection != null) {
          try {
            connection.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
      }
    }

    return new byte[0];
  }

}
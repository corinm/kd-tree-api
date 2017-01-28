import java.sql.*;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Database {

    /**
     * storeTree
     * 
     * @param {String]} tree - JSON form of tree
     * 
     * @return {int} - Database generated id for stored tree
     */
    public static int storeTree(String tree) {

      Connection connection = null;

      try {
        // Connect to PostgreSQL
        connection = DatabaseUrl.extract().getConnection();

        // Create table and schema
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS trees (id SERIAL PRIMARY KEY, tree TEXT NOT NULL, secret TEXT)");

        // Store Tree
        PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement("INSERT INTO trees (tree) VALUES (?) RETURNING id");
        pstmt.setString(1, tree);
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


  public static String loadTree(int id) {

    Connection connection = null;

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

      String tree = "";

      if (rs.next()) {
        tree = rs.getString(1);
      }

      return tree;

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

    return "";
  }

  public static void storeSecret(int id, String secret) {

    Connection connection = null;

    try {
      // Connect to PostgreSQL
      connection = DatabaseUrl.extract().getConnection();

      // Load Tree
      PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement("UPDATE trees SET secret = ? WHERE id = ?");
      pstmt.setString(1, secret);
      pstmt.setInt(2, id);
      pstmt.execute();

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

  }

}
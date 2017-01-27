import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.io.IOException;

import static spark.Spark.*;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {

    /*
     * Server setup
     */

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    /*
     * API Routes
     */

    get("/db-test", (req, res) -> {
      Connection connection = null;
        Map<String, Object> attributes = new HashMap<>();
        try {
          connection = DatabaseUrl.extract().getConnection();

          Statement stmt = connection.createStatement();

          byte[] testBytes = new byte[0];

          try {
              testBytes = Serialiser.serialise((Object) "Test");

          } catch (IOException e) {
              e.printStackTrace();
          }


          // Create table and schema
          stmt.executeUpdate("CREATE TABLE IF NOT EXISTS trees (id SERIAL NOT NULL, tree BYTEA NOT NULL, PRIMARY KEY (id))");
          // Store something
          String query = "INSERT INTO tree (id, tree) VALUES (?,?)";
          PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(query);
          pstmt.setString(1, "1");
          pstmt.setBytes(2, testBytes);

          pstmt.execute();


          // stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tree (tick timestamp)");
          // stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
          // ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

          // ArrayList<String> output = new ArrayList<String>();
          // while (rs.next()) {
          //   output.add( "Read from DB: " + rs.getTimestamp("tick"));
          // }

          // attributes.put("results", output);
          // // return new ModelAndView(attributes, "db.ftl");
          return "CONNECTED TO DB";
        } catch (Exception e) {
          attributes.put("message", "There was an error: " + e);
          // return new ModelAndView(attributes, "error.ftl");
          return "DB CONNECTION FAILED - " + e;
        } finally {
          if (connection != null) try{connection.close();} catch(SQLException e){}
        }
    });

    /*
     * POST: /tree/create - Creates a new tree, returns id of tree for later access
     */
    post("/tree/create", (req, res) -> {
      // Get data from req.body
      String body = req.body();

      // Process JSON
      JsonProcessor p = new JsonProcessor();
      TreeCreatePayloadItem[] items = p.processCreateData(body);

      // Create tree
      Tree t = new Tree();
      t.createTree(items);

      // Store tree for later
        // Need database
        // Convert tree to byte array (?)
        byte[] toStore = t.getStorableTree();
        // Store in database
        // Get id from database

      // Return tree's id
      return "CREATE ROUTE";
    });

    /*
     * POST: /tree/search - Returns nearest match based on a given key
     */
    post("/tree/search", (req, res) -> {
      // Get data from req.body
      // Process JSON
      // Retrieve stored tree
      // Search tree for nearest match
      // Return match's 'data' attribute
      return "SEARCH ROUTE";
    });

  }

}

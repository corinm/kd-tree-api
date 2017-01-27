import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

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

      System.out.println("HERE");

      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        // return new ModelAndView(attributes, "db.ftl");
        return "DB WORKING";
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        // return new ModelAndView(attributes, "error.ftl");
        return "There was an error: " + e;
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

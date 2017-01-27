import static spark.Spark.*;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {

    /*
     * Server setup
     */

    port(Integer.valueOf(System.getenv("PORT")));
    // staticFileLocation("/public");


    /*
     * API Routes
     */

    /*
     * POST: /tree/create - Creates a new tree, returns id of tree for later access
     */
    post("/tree/create", (req, res) -> {
      // Get data from req.body
      // Process JSON
      // Create tree
      // Store tree for later
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

import static spark.Spark.*;

import com.google.gson.Gson;

public class Main {

  public static void main(String[] args) {

    /*
     * Server setup
     */

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
    final Test test = new Test();

    /*
     * API Routes
     */

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

      // Convert tree to byte array
      Gson gson = new Gson();
      String jsonTree = gson.toJson(t);

      // Store in database AND get id from database
      int idOfStoredTree = Database.storeTree(jsonTree);

      String response = p.createReturnIdJson(idOfStoredTree);

      // Return tree's id
      return response;
    });

    /*
     * POST: /tree/search - Returns nearest match based on a given key
     */
    post("/tree/search", (req, res) -> {
      // Get data from req.body
      String body = req.body();

      // Process JSON
      JsonProcessor p = new JsonProcessor();
      int requestedId = p.processSearchDataId(body);
      double[] requestedKey = p.processSearchDataKey(body);

      // Retrieve stored tree
      String jsonTree = Database.loadTree(requestedId);
      Gson gson = new Gson();
      Tree tree = gson.fromJson(jsonTree, Tree.class);

      // tree.loadExistingTree(retrievedTree);

      // Search tree for nearest match
      // Return match's 'data' attribute
      return "SEARCH ROUTE";
    });

  }

}

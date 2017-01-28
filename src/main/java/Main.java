import static spark.Spark.*;

import com.google.gson.Gson;

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

    /*
     * POST: /tree/create - Creates a new tree, returns id of tree for later access
     */
    post("/tree/create", (req, res) -> {

      Tree t = new Tree();
      JsonProcessor p = new JsonProcessor();
      Gson gson = new Gson();

      // Create tree
      String body = req.body();
      TreeCreatePayloadItem[] items = p.processCreateData(body);
      t.createTree(items);

      // Store tree & get id, generate and store secret
      String jsonTree = gson.toJson(t);
      int idOfStoredTree = Database.storeTree(jsonTree);
      String secret = SecretGenerator.generateNewSecret();
      Database.storeSecret(idOfStoredTree, secret);

      // Return tree's id
      String response = p.createReturnJson(idOfStoredTree, secret);
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

      // Search tree for nearest match
      String result = p.processSearchResult(tree.searchTree(requestedKey));

      // Return match's 'data' attribute
      return result;
    });

  }

}

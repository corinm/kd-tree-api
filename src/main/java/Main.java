import static spark.Spark.*;

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
        // Store in database AND get id from database
        int idOfStoredTree = Database.storeTree(toStore);

      // Return tree's id
      // TODO: Return this in JSON form
      return "Tree saved, id: " + idOfStoredTree;
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

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {

    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    // Instantiate LatLongSearcher and create tree
    final LatLongSearcher lls = new LatLongSearcher();
    lls.createTree();

    /*
     * API Routes
     */

    /*
     * POST: /tree/create - Creates a new tree, returns id of tree for later access
     */
    post("/tree/create", (req, res) -> {
      return "CREATE ROUTE";
    });

    /*
     * POST: /tree/search - Returns nearest match based on a given key
     */
    post("/tree/search", (req, res) -> {
      return "SEARCH ROUTE";
    });

  }

}

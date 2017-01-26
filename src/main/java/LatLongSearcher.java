import net.sf.javaml.core.kdtree.KDTree;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class LatLongSearcher {

    private JSONParser parser;
    private KDTree tree;
    private String metApiKey;

    LatLongSearcher() {
        this.parser = new JSONParser();
        this.tree = new KDTree(2);
        this.metApiKey = System.getenv().get("MET_KEY");
    }

    public void createTree() {

        try {
            JSONArray rawMetLocations = this.retrieveMetOfficeLocations();

            // Turn rawMetLocations into JSON needed by original createTree function
            for (Object rawMetLocation : rawMetLocations) {
                this.insertIntoTree((JSONObject) rawMetLocation);
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public int searchMetLocations(String postcode) {

        // Look up lat and long for postcode
        // Search in tree

        // int result = 0;

        // double[] search = new double[2];
        // search[0] = currentLocation.getLatitudeActual();
        // search[1] = currentLocation.getLongitudeActual();

        // result = (int) tree.nearest(search);

        // return result;
        return 0;
    }


    /*
     * Requests Met Office locations from Met Office API, returns JSONArray
     */
    private JSONArray retrieveMetOfficeLocations() throws UnirestException, ParseException {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/sitelist")
            .queryString("key", this.metApiKey)
            .asJson();
            
        String rawBody = jsonResponse.getBody().toString();
        JSONObject body = (JSONObject) this.parser.parse(rawBody);
        JSONObject bodyLocations = (JSONObject) body.get("Locations");
        JSONArray rawMetLocations = (JSONArray) bodyLocations.get("Location");
        return rawMetLocations;
    }


    /*
     * Given a raw Met Office location in JSONObject form, inserts it into KDTree
     */
    private void insertIntoTree(JSONObject rawMetLocation) {
        double latitude = Double.parseDouble(rawMetLocation.get("latitude").toString());
        double longitude = Double.parseDouble(rawMetLocation.get("longitude").toString());
        String name = rawMetLocation.get("name").toString();
        String id = rawMetLocation.get("id").toString();
        String area = rawMetLocation.get("unitaryAuthArea").toString();

        // Build new JSON object
        JSONObject location = new JSONObject();
        location.put("id", id);
        location.put("name", name);
        location.put("area", area);

        double[] key = new double[2];
        key[0] = latitude;
        key[1] = longitude;

        this.tree.insert(key, location);
    }

    

}

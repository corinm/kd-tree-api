import net.sf.javaml.core.kdtree.KDTree;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedList;
import java.util.ListIterator;

public class LatLongSearcher {

    private JSONParser parser;
    private KDTree tree;

    LatLongSearcher() {
        this.parser = new JSONParser();
    }

    public String createTree(String json) {

        String toPrint = "Nothing here yet";

        try {
            // Get array of locations from JSON
            Object object = parser.parse(json);
            JSONArray jsonArray = (JSONArray) object;
            // JSONObject jsonObject = (JSONObject) object;
            toPrint = jsonArray.toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Create tree, store in private property
            // tree.insert([lat, long], object);
        return toPrint;

    }

    // public int searchMetLocations(Location currentLocation) {

    //     int result = 0;

    //     double[] search = new double[2];
    //     search[0] = currentLocation.getLatitudeActual();
    //     search[1] = currentLocation.getLongitudeActual();

    //     result = (int) tree.nearest(search);

    //     return result;
    // }

}

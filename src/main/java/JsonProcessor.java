import org.json.*;
import org.json.simple.parser.JSONParser;
import java.util.Set;

public class JsonProcessor {

    /**
     * processCreateBody
     * 
     * @param {String} rawJson - Raw request body
     
     * @return {TreeCreatePayloadItem[]} - Data format required for creating a tree
     */
    public TreeCreatePayloadItem[] processCreateData(String rawJson) {

        JSONArray rawItems = new JSONArray(rawJson);

        // Create new array of TreeCreatePayloadItems of required length
        int count = rawItems.length();
        TreeCreatePayloadItem[] items = new TreeCreatePayloadItem[count];

        // Iterate over JSONObjects in rawItems, converting to TreeCreatePayloadItems
        // Insert each into items array
        for (int i = 0; i < rawItems.length(); i++) {
            JSONObject rawItem = (JSONObject) rawItems.get(i);

            TreeCreatePayloadItem item = convertItem(rawItem);
            items[i] = item;
        }
        
        return items;
    }

    private TreeCreatePayloadItem convertItem(JSONObject rawItem) {

        /*
         * Expected input
         * {"location":{"area":"Cumbria","name":"Carlisle Airport","id":"14"},"lat":54,"long":-2}
         */

        double key1 = Double.parseDouble(rawItem.get("key1").toString());
        double key2 = Double.parseDouble(rawItem.get("key2").toString());
        double[] keys = new double[2];
        keys[0] = key1;
        keys[1] = key2;
        JSONObject data = (JSONObject) rawItem.get("data");

        TreeCreatePayloadItem item = new TreeCreatePayloadItem(keys, data);

        return item;
    }
    

}

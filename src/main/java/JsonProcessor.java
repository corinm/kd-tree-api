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


    /**
     * createReturnIdJson
     * 
     * @param {int} id - Id of stored tree
     * @return {String} - String of JSON containing {id: id}
     */
    public String createReturnIdJson(int id) {
        JSONObject json = new JSONObject();
        json.put("id", id);
        return json.toString();
    }


    /**
     * processSearchDataId
     * 
     * @param {String} rawJson - Raw request body
     * @return {int} - Id of requested tree (for accessing in database)
     */
    public int processSearchDataId(String rawJson) {
        JSONObject raw = new JSONObject(rawJson);
        return raw.getInt("treeId");
    }


    /**
     * processSearchDataKey
     * 
     * @param {String} rawJson - Raw request body
     * @return {double[]} - Array of two keys, to be used as key for searching tree
     */
    public double[] processSearchDataKey(String rawJson) {
        JSONObject body = new JSONObject(rawJson);
        JSONObject rawKey = body.getJSONObject("key");
        System.out.println(rawKey);
        double[] key = new double[2];
        key[0] = Double.valueOf(rawKey.get("key1").toString());
        key[1] = Double.valueOf(rawKey.get("key2").toString());
        return key;
    }


    /**
     * Accepts result object from Tree.searchTree(), converts to String of JSON
     */
    public String processSearchResult(Object rawResult) {
        JSONObject result = (JSONObject )rawResult;
        return result.toString(2);
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

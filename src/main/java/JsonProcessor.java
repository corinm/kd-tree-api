import org.json.*;

public class JsonProcessor {

    /**
     * processCreateBody
     * 
     * @param {String} rawJson - Raw request body
     * @return {TreeCreatePayloadItem[]} - Data format required for creating a tree
     */
    public TreeCreatePayloadItem[] processCreateData(String rawJson) throws JsonInputException {

        try {

            JSONArray rawItems = new JSONArray(rawJson);
            
            // Create new array of TreeCreatePayloadItems of required length
            int count = rawItems.length();
            TreeCreatePayloadItem[] items = new TreeCreatePayloadItem[count];

            // Iterate over JSONObjects in rawItems, converting to TreeCreatePayloadItems
            // Insert each into items array
            for (int i = 0; i < rawItems.length(); i++) {
                JSONObject rawItem = (JSONObject) rawItems.get(i);

                TreeCreatePayloadItem item = this.convertItem(rawItem);
                items[i] = item;
            }
            
            return items;

        } catch (Exception e) {
            throw new JsonInputException();
        }
        
    }


    /**
     * createReturnIdJson
     * 
     * @param {int} id - Id of stored tree
     * @return {String} - String of JSON containing {id: id}
     */
    public String createReturnJson(int id, String secret) {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("secret", secret);
        return json.toString(2);
    }


    /**
     * processSearchDataId
     * 
     * @param {String} rawJson - Raw request body
     * @return {int} - Id of requested tree (for accessing in database)
     */
    public int processSearchDataId(String rawJson) throws JsonInputException {
        try {
            JSONObject raw = new JSONObject(rawJson);
            return raw.getInt("id");
        } catch (Exception e) {
            throw new JsonInputException();            
        }
    }

    /**
     * processSearchDataSecret
     * 
     * @param {String} rawJson - Raw request body
     * @return {String} - Secret for requested tree (for accessing in database)
     */
    public String processSearchDataSecret(String rawJson) throws JsonInputException {
        try {
            JSONObject raw = new JSONObject(rawJson);
            return raw.getString("secret");
        } catch (Exception e) {
            throw new JsonInputException();
        }
    }


    /**
     * processSearchDataKey
     * 
     * @param {String} rawJson - Raw request body
     * @return {double[]} - Array of two keys, to be used as key for searching tree
     */
    public double[] processSearchDataKey(String rawJson) throws JsonInputException {
        try {
            JSONObject body = new JSONObject(rawJson);
            JSONObject rawKey = body.getJSONObject("key");
            double[] key = new double[2];
            key[0] = Double.valueOf(rawKey.get("key1").toString());
            key[1] = Double.valueOf(rawKey.get("key2").toString());
            return key;
        } catch (Exception e) {
            throw new JsonInputException();
        }
    }


    /**
     * Accepts result object from Tree.searchTree(), converts to String of JSON
     */
    public String processSearchResult(JSONObject json) {
        return json.toString(2);
    }


    private TreeCreatePayloadItem convertItem(JSONObject rawItem) throws JsonInputException {

        /*
         * Expected input
         * {"location":{"area":"Cumbria","name":"Carlisle Airport","id":"14"},"lat":54,"long":-2}
         */

        try {
            double key1 = Double.parseDouble(rawItem.get("key1").toString());
            double key2 = Double.parseDouble(rawItem.get("key2").toString());
            double[] keys = new double[2];
            keys[0] = key1;
            keys[1] = key2;
            JSONObject dataObject = (JSONObject) rawItem.get("data");
            String dataString = dataObject.toString();

            TreeCreatePayloadItem item = new TreeCreatePayloadItem(keys, dataString);

            return item;
        } catch (Exception e) {
            throw new JsonInputException();
        }
    }
    

}

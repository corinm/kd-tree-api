import org.json.*;

public class TreeCreatePayloadItem {

    private double[] keys;
    private JSONObject data;

    TreeCreatePayloadItem(double[] keys, JSONObject data) {
        this.keys = keys;
        this.data = data;
    }

    public double[] getKeys() {
        return this.keys;
    }

    public JSONObject getData() {
        return this.data;
    }

}

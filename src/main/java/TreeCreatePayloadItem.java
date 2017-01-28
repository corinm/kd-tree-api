public class TreeCreatePayloadItem {

    private double[] keys;
    private String data;

    TreeCreatePayloadItem(double[] keys, String data) {
        this.keys = keys;
        this.data = data;
    }

    public double[] getKeys() {
        return this.keys;
    }

    public String getData() {
        return this.data;
    }

}

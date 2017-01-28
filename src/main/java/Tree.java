import net.sf.javaml.core.kdtree.KDTree;

import org.json.*;

/**
 * Class containing a KDTree instance and associated helper methods
 */
public class Tree {

    private KDTree tree;

    Tree() {
        this.tree = new KDTree(2);
    }

    /**
     * Creates a new KDTree by inserting in array of TreeCreatePayloadItems
     * 
     * @param {TreeCreatePayloadItem[]} items - Rows of data to be added to tree
     * @return {void}
     */
    public void createTree(TreeCreatePayloadItem[] items) {
        for (TreeCreatePayloadItem item : items) {
            this.insertItemIntoTree(item);
        }
    }

    /**
     * Searches tree for a given key, returns ?
     * 
     * @param {double[]} key - Key to search for in tree
     * @returns {??} - Result of search
     */
    public Object searchTree(double[] key) {
        return this.tree.nearest(key);
    }

    private void insertItemIntoTree(TreeCreatePayloadItem item) {
        double[] keys = item.getKeys();
        JSONObject data = item.getData();
        this.tree.insert(keys, data);
    }
    
}

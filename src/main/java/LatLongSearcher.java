import net.sf.javaml.core.kdtree.KDTree;

import java.util.LinkedList;
import java.util.ListIterator;

public class LatLongSearcher {

    private KDTree tree;

    LatLongSearcher() {

        

    }

    private void createTree(String json) {

        // Get array of locations from JSON
        // Create tree, store in private property
            // tree.insert([lat, long], object);

    }

    public int searchMetLocations(Location currentLocation) {

        int result = 0;

        double[] search = new double[2];
        search[0] = currentLocation.getLatitudeActual();
        search[1] = currentLocation.getLongitudeActual();

        result = (int) tree.nearest(search);

        return result;
    }

}

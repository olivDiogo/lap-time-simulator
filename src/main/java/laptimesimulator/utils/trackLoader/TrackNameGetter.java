package laptimesimulator.utils.trackLoader;

import laptimesimulator.domain.valueObject.Name;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrackNameGetter {

    /**
     * Private constructor to hide the implicit public one.
     */
    private TrackNameGetter() {
    }

    /**
     * Extract the part of the fileName before the dot.
     *
     * @param fileName the name of the file
     * @return the part of the fileName before the dot
     */
    public static String getName(String fileName) {
        // Find the index of the first dot
        int dotIndex = fileName.indexOf('.');

        // Extract the substring before the dot
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex);
        } else {
            // If there is no dot, return the whole fileName
            return fileName;
        }
    }
}

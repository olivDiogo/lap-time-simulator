package lapTimeSimulator.utils.trackLoader;

import lapTimeSimulator.domain.valueObject.Name;

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
     * Get the names of the tracks from each of the track files in the \track directory.
     *
     * @return a list of Name objects
     */
    public static List<Name> getNamesFromTracksDirectory() {
        List<Name> names = new ArrayList<>();

        // Get the directory
        File directory = new File("./src/main/resources/tracks");

        // Get all the .track files in the directory
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".track"));

        if (files != null) {
            for (File file : files) {
                // Extract the part before the dot
                String fileName = file.getName();
                String namePart = getNamePart(fileName);

                // Instantiate a Name object and add it to the list
                Name name = new Name(namePart);
                names.add(name);
            }
        } else {
            System.out.println("No .track files found in the directory.");
        }

        return names;
    }

    /**
     * Extract the part of the fileName before the dot.
     *
     * @param fileName the name of the file
     * @return the part of the fileName before the dot
     */
    private static String getNamePart(String fileName) {
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

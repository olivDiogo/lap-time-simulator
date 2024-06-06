package laptimesimulator.utils.trackLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrackIconPathGetter {

    private TrackIconPathGetter(){}

    /**
     * Get the path of the track image.
     *
     * @param filePath the path to the file
     * @return the path of the track image
     */
    public static String getTrackIconPath(String filePath) {
        String trackIconPath = "";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 5) {
                    trackIconPath = currentLine.trim();
                    break; // Exit the loop as we have found the 5th line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trackIconPath;
    }
}

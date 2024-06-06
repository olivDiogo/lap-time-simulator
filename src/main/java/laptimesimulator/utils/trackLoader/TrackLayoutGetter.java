package laptimesimulator.utils.trackLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrackLayoutGetter {

    private TrackLayoutGetter(){}

    /**
     * Get the location of the track in the file.
     *
     * @param filePath the path to the file
     * @return the location of the track
     */
    public static String getTrackLayout(String filePath) {
        String trackLayout = "";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 4) {
                    trackLayout = currentLine.trim();
                    break; // Exit the loop as we have found the 4th line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trackLayout;
    }

}

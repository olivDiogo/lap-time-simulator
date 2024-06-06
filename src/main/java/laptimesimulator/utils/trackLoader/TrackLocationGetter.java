package laptimesimulator.utils.trackLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrackLocationGetter {

    private TrackLocationGetter(){}

    /**
     * Get the location of the track in the file.
     *
     * @param filePath the path to the file
     * @return the location of the track
     */
    public static String getTrackLocation(String filePath) {
        String trackLocation = "";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 6) {
                    trackLocation = currentLine.trim();
                    break; // Exit the loop as we have found the 6th line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trackLocation;
    }




}

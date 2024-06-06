package laptimesimulator.utils.trackLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrackLengthGetter {

    private TrackLengthGetter(){}

    /**
     * Get the length of the track in the track file (line before the last).
     *
     * @param filePath the path to the file
     * @return the length of the track
     */
    public static int getLength(String filePath) {
        int trackLength = 0;
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 2) {
                    trackLength = Integer.parseInt(currentLine);
                    break; // Exit the loop as we have found the 2nd line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trackLength;
    }



}

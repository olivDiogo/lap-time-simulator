package laptimesimulator.utils.trackLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrackRaceLapRecordGetter {

    private TrackRaceLapRecordGetter(){}

    /**
     * Get the track's lap time record.
     *
     * @param filePath the path to the file
     * @return the track's lap time record
     */
    public static String getTrackRaceLapRecord(String filePath) {
        String trackRaceLapRecord = "";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 7) {
                    trackRaceLapRecord = currentLine.trim();
                    break; // Exit the loop as we have found the 7th line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trackRaceLapRecord;
    }



}

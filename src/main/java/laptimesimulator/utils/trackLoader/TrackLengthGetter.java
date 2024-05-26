package laptimesimulator.utils.trackLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrackLengthGetter {

    private TrackLengthGetter(){}

    /**
     * Get the length of the track in the file.
     *
     * @param filePath the path to the file
     * @return the length of the track
     */
    public static int getLength(String filePath) {
        int trackLength = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    trackLength = (int) Math.round(Double.parseDouble(parts[0]));
                } catch (NumberFormatException ignored) {
                    // This line does not start with a number, ignore it
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trackLength;
    }

}

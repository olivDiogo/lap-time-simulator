package laptimesimulator.domain.track;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;

public interface ITrackFactory {
    /**
     * Creates a new track with the specified name.
     *
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    Track createTrack(Name trackName, TrackLength trackLength, String trackIconPath, String trackLocation, String trackRaceLapRecord, String numberOfCorners, String trackLayout);

    /**
     * Creates a new track with the specified ID and name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    Track createTrack(TrackID trackID, Name trackName, TrackLength trackLength, String trackIconPath, String trackLocation, String trackRaceLapRecord, String numberOfCorners, String trackLayout);
}

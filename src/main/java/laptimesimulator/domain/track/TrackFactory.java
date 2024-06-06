package laptimesimulator.domain.track;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
import org.springframework.stereotype.Component;

@Component
public class TrackFactory implements ITrackFactory { //Review need for this class!!!

    /**
     * Creates a new track with the specified name.
     *
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    @Override
    public Track createTrack(Name trackName, TrackLength trackLength, String trackIconPath, String trackLocation, String trackRaceLapRecord, String numberOfCorners, String trackLayout) {
        if (trackName == null || trackLength == null || trackIconPath == null || trackLocation == null || trackRaceLapRecord == null || numberOfCorners == null || trackLayout == null)
            throw new IllegalArgumentException("Track parameters cannot be null.");
        return new Track(trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout);
    }

    /**
     * Creates a new track with the specified ID and name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    @Override
    public Track createTrack(TrackID trackID, Name trackName, TrackLength trackLength, String trackIconPath, String trackLocation, String trackRaceLapRecord, String numberOfCorners, String trackLayout) {
        if(trackID == null || trackName == null || trackLength == null || trackIconPath == null || trackLocation == null || trackRaceLapRecord == null || numberOfCorners == null || trackLayout == null)
            throw new IllegalArgumentException("Track parameters cannot be null.");
        return new Track(trackID, trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout);
    }
}

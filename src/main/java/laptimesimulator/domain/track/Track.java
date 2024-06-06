package laptimesimulator.domain.track;

import laptimesimulator.ddd.IAggregateRoot;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Track implements IAggregateRoot<TrackID> {
    private final TrackID trackID;
    private final Name trackName;
    private final TrackLength trackLength;
    private final String trackIconPath;
    private final String trackLocation;
    private final String trackRaceLapRecord;
    private final String numberOfCorners;
    private final String trackLayout;

    /**
     * Constructs a new Track instance with the specified track name.
     *
     * @param trackName The name of the track. Must not be null.
     */
    Track(Name trackName, TrackLength trackLength, String trackIconPath, String trackLocation, String trackRaceLapRecord, String numberOfCorners, String trackLayout) {
        // Parameter validation is done in the factory.
        this.trackName = trackName;
        this.trackLength = trackLength;
        this.trackIconPath = trackIconPath;
        this.trackLocation = trackLocation;
        this.trackRaceLapRecord = trackRaceLapRecord;
        this.numberOfCorners = numberOfCorners;
        this.trackLayout = trackLayout;
        this.trackID = new TrackID(UUID.randomUUID().toString());
    }

    /**
     * Constructs a new Track instance with the specified track ID and track name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     */
    Track(TrackID trackID, Name trackName, TrackLength trackLength, String trackIconPath, String trackLocation, String trackRaceLapRecord, String numberOfCorners, String trackLayout) {
        // Parameter validation is done in the factory.
        this.trackID = trackID;
        this.trackName = trackName;
        this.trackLength = trackLength;
        this.trackIconPath = trackIconPath;
        this.trackLocation = trackLocation;
        this.trackRaceLapRecord = trackRaceLapRecord;
        this.numberOfCorners = numberOfCorners;
        this.trackLayout = trackLayout;
    }

//    /**
//     * Updates the name of the track.
//     *
//     * @param trackName The new name of the track. Must not be null.
//     */
//    public Name updateTrackName(Name trackName) {
//        if (trackName == null) {
//            throw new IllegalArgumentException("Track name cannot be null.");
//        }
//        this.trackName = trackName;
//        return trackName;
//    }

}

package lapTimeSimulator.domain.track;

import lapTimeSimulator.ddd.IAggregateRoot;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Track implements IAggregateRoot<TrackID> {
    private final TrackID trackID;
    private Name trackName;

    /**
     * Constructs a new Track instance with the specified track name.
     *
     * @param trackName The name of the track. Must not be null.
     */
    Track(Name trackName) {
        this.trackName = trackName;
        this.trackID = new TrackID(UUID.randomUUID().toString());
    }

    /**
     * Constructs a new Track instance with the specified track ID and track name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     */
    Track(TrackID trackID, Name trackName) {
        this.trackID = trackID;
        this.trackName = trackName;
    }

    /**
     * Updates the name of the track.
     *
     * @param trackName The new name of the track. Must not be null.
     */
    public Name updateTrackName(Name trackName) {
        if (trackName == null) {
            throw new IllegalArgumentException("Track name cannot be null.");
        }
        this.trackName = trackName;
        return trackName;
    }

}

package lapTimeSimulator.domain.track;

import lapTimeSimulator.ddd.IAggregateRoot;
import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.domain.valueObject.TrackID;
import lombok.Getter;
import java.util.UUID;

public class Track implements IAggregateRoot<TrackID> {
    private final TrackID trackID;
    @Getter
    private Description trackName;

    /**
     * Constructs a new Track instance with the specified track name.
     *
     * @param trackName The name of the track. Must not be null.
     */
    Track(Description trackName) {
        if(trackName == null) {
            throw new IllegalArgumentException("Track name cannot be null.");
        }
        this.trackName = trackName;
        this.trackID = new TrackID(UUID.randomUUID().toString());
    }

    /**
     * Constructs a new Track instance with the specified track ID and track name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     */
    Track(TrackID trackID, Description trackName) {
        if(trackID == null || trackName == null) {
            throw new IllegalArgumentException("Track ID and track name cannot be null.");
        }

        this.trackID = trackID;
        this.trackName = trackName;
    }

    /**
     * Getter for the track ID.
     *
     * @return the ID of the track.
     */
    @Override
    public TrackID getId() {
        return trackID;
    }

}

package lapTimeSimulator.domain.track;

import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.domain.valueObject.TrackID;
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
    public Track createTrack(Description trackName) {
        if (trackName == null)
            throw new IllegalArgumentException("Track name cannot be null.");
        return new Track(trackName);
    }

    /**
     * Creates a new track with the specified ID and name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    @Override
    public Track createTrack(TrackID trackID, Description trackName) {
        if(trackID == null || trackName == null)
            throw new IllegalArgumentException("Track ID and track name cannot be null.");
        return new Track(trackID, trackName);
    }
}

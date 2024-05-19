package lapTimeSimulator.domain.track;

import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.domain.valueObject.TrackID;

public interface ITrackFactory {
    /**
     * Creates a new track with the specified name.
     *
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    Track createTrack(Description trackName);

    /**
     * Creates a new track with the specified ID and name.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     * @return The new track instance.
     */
    Track createTrack(TrackID trackID, Description trackName);
}

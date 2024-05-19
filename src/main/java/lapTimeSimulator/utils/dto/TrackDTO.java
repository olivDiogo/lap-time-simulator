package lapTimeSimulator.utils.dto;

import lapTimeSimulator.domain.track.Track;

public class TrackDTO {
    public final String trackID;
    public final String trackName;

    /**
     * Constructor of the class.
     *
     * @param trackID The ID of the track. Must not be null.
     * @param trackName The name of the track. Must not be null.
     */
    public TrackDTO(String trackID, String trackName) {
        this.trackID = trackID;
        this.trackName = trackName;
    }
}

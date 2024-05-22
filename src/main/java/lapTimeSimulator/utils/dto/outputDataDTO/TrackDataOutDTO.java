package lapTimeSimulator.utils.dto.outputDataDTO;

public class TrackDataOutDTO {
    public final String trackID;
    public final String trackName;

    public TrackDataOutDTO(String trackID, String trackName) {
        if (trackID == null || trackID.isBlank() || trackName == null || trackName.isBlank()) {
            throw new IllegalArgumentException("Track ID and track name must be non-empty strings.");
        }

        this.trackID = trackID;
        this.trackName = trackName;
    }

}

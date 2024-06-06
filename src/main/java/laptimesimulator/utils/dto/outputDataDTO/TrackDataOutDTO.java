package laptimesimulator.utils.dto.outputDataDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrackDataOutDTO {
    public final String trackID;
    public final String trackName;
    public final int trackLength;
    public final String trackLocation;
    public final String trackIconPath;
    public final String trackRaceLapRecord;
    public final String numberOfCorners;
    public final String trackLayout;
}

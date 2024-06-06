package laptimesimulator.mapper;

import laptimesimulator.ddd.IMapper;
import laptimesimulator.domain.track.Track;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
//@AllArgsConstructor
public class TrackMapper implements IMapper<Track, TrackDataOutDTO> {

    /**
     * Method to convert a Track into a TrackDTO.
     *
     * @param track is the track object to be converted.
     * @return the TrackDTO.
     */
    @Override
    public TrackDataOutDTO toDTO(Track track) {
        if(track == null)
            throw new IllegalArgumentException("The track cannot be null.");

        String trackID = track.getTrackID().getId();
        String trackName = track.getTrackName().getStrName();
        int trackLength = track.getTrackLength().getLength();
        String trackLocation = track.getTrackLocation();
        String trackIconPath = track.getTrackIconPath();
        String trackRaceLapRecord = track.getTrackRaceLapRecord();
        String numberOfCorners = track.getNumberOfCorners();
        String trackLayout = track.getTrackLayout();

        return new TrackDataOutDTO(trackID, trackName, trackLength, trackLocation, trackIconPath, trackRaceLapRecord, numberOfCorners, trackLayout);
    }

    /**
     * Method to convert a list of Tracks into a list of TrackDTOs.
     *
     * @param tracks is the list of track objects to be converted.
     * @return the list of DTOs.
     */
    @Override
    public List<TrackDataOutDTO> toDTO(List<Track> tracks) {
        if(tracks == null)
            throw new IllegalArgumentException("The list of tracks cannot be null.");

        return tracks.stream().map(this::toDTO).toList();
    }
}

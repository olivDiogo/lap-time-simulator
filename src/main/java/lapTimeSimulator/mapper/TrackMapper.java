package lapTimeSimulator.mapper;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.utils.dto.TrackDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TrackMapper implements IMapper<Track, TrackDTO> {

    /**
     * Method to convert a Track into a TrackDTO.
     *
     * @param track is the track object to be converted.
     * @return the TrackDTO.
     */
    @Override
    public TrackDTO toDTO(Track track) {
        if(track == null)
            throw new IllegalArgumentException("The track cannot be null.");

        String trackID = track.getId().getId();
        String trackName = track.getTrackName().getDescription();
        return new TrackDTO(trackID, trackName);
    }

    /**
     * Method to convert a list of Tracks into a list of TrackDTOs.
     *
     * @param tracks is the list of track objects to be converted.
     * @return the list of DTOs.
     */
    @Override
    public List<TrackDTO> toDTO(List<Track> tracks) {
        if(tracks == null)
            throw new IllegalArgumentException("The list of tracks cannot be null.");

        return tracks.stream().map(this::toDTO).toList();
    }
}

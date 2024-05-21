package lapTimeSimulator.mapper;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.utils.dto.TrackDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
//@AllArgsConstructor
public class TrackMapper implements IMapper<Track, TrackDTO> {
//    private final ITrackFactory trackFactory;

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

        String trackID = track.getTrackID().getId();
        String trackName = track.getTrackName().getStrName();
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

//    /**
//     * Method to convert a TrackDTO into a Track.
//     *
//     * @param trackDTO is the DTO to be converted.
//     * @return the track object.
//     */
//    @Override
//    public Track toDomain(TrackDTO trackDTO) {
//        if(trackDTO == null)
//            throw new IllegalArgumentException("The DTO cannot be null.");
//
//        TrackID trackID = new TrackID(trackDTO.trackID);
//        Name trackName = new Name(trackDTO.trackName);
//
//        return trackFactory.createTrack(trackID, trackName);
//    }
}

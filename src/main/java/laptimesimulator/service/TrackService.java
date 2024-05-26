package laptimesimulator.service;

import laptimesimulator.ddd.IMapper;
import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrackService {
    private IRepository<TrackID, Track> repository;
    private IMapper<Track, TrackDataOutDTO> trackMapper;

    /**
     * Gets all tracks in the database.
     *
     * @return a list with all tracks.
     */
    public List<TrackDataOutDTO> getTracks() {
        List<Track> tracks = repository.findAll();
        return trackMapper.toDTO(tracks);
    }
}

package lapTimeSimulator.service;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
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

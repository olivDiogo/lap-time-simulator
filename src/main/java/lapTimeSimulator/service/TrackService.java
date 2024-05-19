package lapTimeSimulator.service;

import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.TrackID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    private final IRepository<TrackID, Track> repository;

    /**
     * Constructs a new TrackService instance with the specified repository and track factory.
     *
     * @param repository The track repository. Must not be null.
     */
    public TrackService(IRepository<TrackID, Track> repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }
        this.repository = repository;
    }

    /**
     * Gets all tracks in the database.
     *
     * @return a list with all tracks.
     */
    public List<Track> getTracks() {
        return repository.findAll();
    }
}

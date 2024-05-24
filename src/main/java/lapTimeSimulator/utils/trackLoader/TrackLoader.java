package lapTimeSimulator.utils.trackLoader;

import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.persistence.track.ITrackRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackLoader {
    private final IRepository<TrackID, Track> trackRepository;
    private final ITrackFactory trackFactory;

    /**
     * Constructs a new TrackLoader instance with the specified track repository and track factory.
     *
     * @param trackRepository The track repository. Must not be null.
     * @param trackFactory The track factory. Must not be null.
     */
    public TrackLoader(ITrackRepository trackRepository, ITrackFactory trackFactory) {
        this.trackRepository = trackRepository;
        this.trackFactory = trackFactory;
        loadTracks();
    }

    /**
     * Load the tracks from the tracks directory.
     */
    private void loadTracks() {
        // Get the names of the tracks
        List<Name> names = TrackNameGetter.getNamesFromTracksDirectory();

        // Load the tracks
        for (Name trackName : names) {
            Track track = trackFactory.createTrack(trackName);
            trackRepository.save(track);
        }
    }
}

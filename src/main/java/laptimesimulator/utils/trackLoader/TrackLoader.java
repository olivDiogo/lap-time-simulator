package laptimesimulator.utils.trackLoader;

import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.track.ITrackFactory;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
import laptimesimulator.persistence.track.ITrackRepository;
import org.springframework.stereotype.Component;

import java.io.File;

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
        // Get the directory
        File tracksDirectory = new File("./src/main/resources/tracks");

        // Get all the .track files in the directory
        File[] trackFiles = tracksDirectory.listFiles((dir, name) -> name.endsWith(".track"));

        if (trackFiles != null) {
            for (File file : trackFiles) {
                // Extract the part before the dot
                String fileName = file.getName();

                String strTrackName = TrackNameGetter.getName(fileName);
                int nTrackLength = TrackLengthGetter.getLength(file.getPath());
                String strTrackIconPath = TrackIconPathGetter.getTrackIconPath(file.getPath());
                String strTrackLocation = TrackLocationGetter.getTrackLocation(file.getPath());
                String strTrackRaceLapRecord = TrackRaceLapRecordGetter.getTrackRaceLapRecord(file.getPath());
                String strNumberOfCorners = TrackNumberOfCornersGetter.getTrackNumberOfCorners(file.getPath());
                String strTrackLayout = TrackLayoutGetter.getTrackLayout(file.getPath());

                // Instantiate a Name object
                Name trackName = new Name(strTrackName);
                TrackLength trackLength = new TrackLength(nTrackLength);

                // Create a new track and save it
                Track track = trackFactory.createTrack(trackName, trackLength, strTrackIconPath, strTrackLocation, strTrackRaceLapRecord, strNumberOfCorners, strTrackLayout);
                trackRepository.save(track);
            }
        } else {
            System.out.println("No .track files found in the directory.");
        }

    }
}

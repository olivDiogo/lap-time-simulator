package laptimesimulator.persistence.assembler;

import laptimesimulator.domain.track.ITrackFactory;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
import laptimesimulator.persistence.dataModel.TrackDataModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackDataModelAssembler implements IDataModelAssembler<Track, TrackDataModel>{
    private final ITrackFactory trackFactory;

    /**
     * Constructor of the class.
     *
     * @param trackFactory is the track factory.
     */
    public TrackDataModelAssembler(ITrackFactory trackFactory) {
        if(trackFactory == null)
            throw new IllegalArgumentException("The track factory must be not null.");
        this.trackFactory = trackFactory;
    }

    /**
     * Method to convert a track data model to a track entity.
     *
     * @param dataModel is the data model to be converted.
     * @return the track entity.
     */
    @Override
    public Track toDomain(TrackDataModel dataModel) {
        if(dataModel == null)
            throw new IllegalArgumentException("The track data model must be not null.");

        TrackID trackID = new TrackID(dataModel.getTrackID());
        Name trackName = new Name(dataModel.getTrackName());
        TrackLength trackLength = new TrackLength(dataModel.getTrackLength());

        return trackFactory.createTrack(trackID, trackName, trackLength);
    }

    /**
     * Method to convert a list of track data models to a list of track entities.
     *
     * @param dataModels is the list of data models to be converted.
     * @return the list of track entities.
     */
    @Override
    public List<Track> toDomain(List<TrackDataModel> dataModels) {
        List<Track> tracks = new ArrayList<>();

        for(TrackDataModel dataModel : dataModels) {
            Track track = toDomain(dataModel);
            tracks.add(track);
        }

        return tracks;
    }
}

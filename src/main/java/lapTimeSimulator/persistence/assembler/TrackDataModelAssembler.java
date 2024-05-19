package lapTimeSimulator.persistence.assembler;

import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.persistence.dataModel.TrackDataModel;
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
        Description trackName = new Description(dataModel.getTrackName());

        return trackFactory.createTrack(trackID, trackName);
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

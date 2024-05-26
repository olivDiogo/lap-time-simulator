package laptimesimulator.persistence.track;

import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.persistence.assembler.IDataModelAssembler;
import laptimesimulator.persistence.dataModel.TrackDataModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrackRepository implements ITrackRepository {
    ITrackSpringDataRepository repository;
    IDataModelAssembler<Track, TrackDataModel> assembler;

    /**
     * Constructor of the class.
     *
     * @param repository is the track repository.
     * @param assembler is the track data model assembler.
     */
    public TrackRepository(ITrackSpringDataRepository repository, IDataModelAssembler<Track, TrackDataModel> assembler) {
        if(repository == null || assembler == null)
            throw new IllegalArgumentException("The repository and the assembler must be not null.");
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * Method to save a track entity.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Track save(Track entity) {
        if(entity == null)
            throw new IllegalArgumentException("The track must be not null.");

        TrackDataModel dataModel = new TrackDataModel(entity);
        repository.save(dataModel);

        return entity;
    }

    /**
     * Method to find all track entities.
     *
     * @return a list with all track entities.
     */
    @Override
    public List<Track> findAll() {
        List<TrackDataModel> listTrackDataModelSaved = repository.findAll();

        return assembler.toDomain(listTrackDataModelSaved);
    }

    /**
     * Method to find a track entity by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<Track> ofIdentity(TrackID objectID) {
        if(objectID == null)
            throw new IllegalArgumentException("The objectID must be not null.");

        Optional<TrackDataModel> trackDataModel = repository.findById(objectID.getId());

        return trackDataModel.map(dataModel -> assembler.toDomain(dataModel));
    }

    /**
     * Method to check if a track entity exists by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return true if the entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(TrackID objectID) {
        return repository.existsById(objectID.getId());
    }

}

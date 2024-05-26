package laptimesimulator.persistence.track;

import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.TrackID;

public interface ITrackRepository extends IRepository<TrackID, Track>{
}

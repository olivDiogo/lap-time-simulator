package lapTimeSimulator.persistence.track;

import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.TrackID;

public interface ITrackRepository extends IRepository<TrackID, Track>{
}

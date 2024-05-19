package lapTimeSimulator.persistence.track;

import lapTimeSimulator.persistence.dataModel.TrackDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrackSpringDataRepository extends JpaRepository<TrackDataModel, String>{
}

package laptimesimulator.persistence.track;

import laptimesimulator.persistence.dataModel.TrackDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrackSpringDataRepository extends JpaRepository<TrackDataModel, String>{
}

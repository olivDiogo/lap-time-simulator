package laptimesimulator.persistence.simulation;

import laptimesimulator.persistence.dataModel.SimulationDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISimulationSpringDataRepository extends JpaRepository<SimulationDataModel, String>{
}

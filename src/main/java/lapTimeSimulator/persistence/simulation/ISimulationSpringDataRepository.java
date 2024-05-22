package lapTimeSimulator.persistence.simulation;

import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.persistence.dataModel.SimulationDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISimulationSpringDataRepository extends JpaRepository<SimulationDataModel, String>{
}

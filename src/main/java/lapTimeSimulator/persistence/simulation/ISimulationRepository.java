package lapTimeSimulator.persistence.simulation;

import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.valueObject.SimulationID;

public interface ISimulationRepository extends IRepository<SimulationID, Simulation> {
}

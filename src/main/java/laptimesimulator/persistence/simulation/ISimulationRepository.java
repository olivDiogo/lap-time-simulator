package laptimesimulator.persistence.simulation;

import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.valueObject.SimulationID;

public interface ISimulationRepository extends IRepository<SimulationID, Simulation> {
}

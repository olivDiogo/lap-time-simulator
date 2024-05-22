package lapTimeSimulator.domain.simulation;

import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.SimulationID;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;

public interface ISimulationFactory {
    /**
     * Create a simulation
     *
     * @param simulationName is the name of the simulation.
     * @param vehicleID is the ID of the vehicle.
     * @param trackID is the ID of the track.
     * @return Simulation
     */
    Simulation createSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID);

    /**
     * Create a simulation
     *
     * @param simulationID is the ID of the simulation.
     * @param simulationName is the name of the simulation.
     * @param vehicleID is the ID of the vehicle.
     * @param trackID is the ID of the track.
     * @return Simulation
     */
    Simulation createSimulation(SimulationID simulationID, Name simulationName, VehicleID vehicleID, TrackID trackID);
}

package laptimesimulator.domain.simulation;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;

public interface ISimulationFactory {
    /**
     * Create a simulation
     *
     * @param simulationName is the name of the simulation.
     * @param vehicleID is the ID of the vehicle.
     * @param trackID is the ID of the track.
     * @return Simulation
     */
    Simulation createSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID, Name vehicleName, Name trackName);

    /**
     * Create a simulation
     *
     * @param simulationID is the ID of the simulation.
     * @param simulationName is the name of the simulation.
     * @param vehicleID is the ID of the vehicle.
     * @param trackID is the ID of the track.
     * @return Simulation
     */
    Simulation createSimulation(SimulationID simulationID, Name simulationName, VehicleID vehicleID, TrackID trackID, Name vehicleName, Name trackName);
}

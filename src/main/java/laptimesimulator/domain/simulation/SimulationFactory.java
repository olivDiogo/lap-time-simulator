package laptimesimulator.domain.simulation;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
import org.springframework.stereotype.Component;

@Component
public class SimulationFactory implements ISimulationFactory{

    /**
     * Create a simulation with the given parameters.
     *
     * @param simulationName is the name of the simulation.
     * @param vehicleID is the ID of the vehicle.
     * @param trackID is the ID of the track.
     * @return the created simulation.
     */
    @Override
    public Simulation createSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID) {
        if (simulationName == null || vehicleID == null || trackID == null)
            throw new IllegalArgumentException("Simulation parameters cannot be null.");
        return new Simulation(simulationName, vehicleID, trackID);
    }

    /**
     * Create a simulation with the given parameters.
     *
     * @param simulationID is the ID of the simulation.
     * @param simulationName is the name of the simulation.
     * @param vehicleID is the ID of the vehicle.
     * @param trackID is the ID of the track.
     * @return the created simulation.
     */
    @Override
    public Simulation createSimulation(SimulationID simulationID, Name simulationName, VehicleID vehicleID, TrackID trackID) {
        if (simulationID == null || simulationName == null || vehicleID == null || trackID == null)
            throw new IllegalArgumentException("Simulation parameters cannot be null.");
        return new Simulation(simulationID, simulationName, vehicleID, trackID);
    }
}

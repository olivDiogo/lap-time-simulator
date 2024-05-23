package lapTimeSimulator.domain.simulation;

import lapTimeSimulator.ddd.IAggregateRoot;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.SimulationID;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Simulation implements IAggregateRoot<SimulationID> {
    private final SimulationID simulationID;
    private Name simulationName;
    private VehicleID vehicleID;
    private TrackID trackID;

    /**
     * Constructor of the class Simulation
     *
     * @param simulationName is the name of the simulation.
     * @param vehicleID      is the ID of the vehicle.
     * @param trackID        is the ID of the track.
     */
    Simulation(Name simulationName, VehicleID vehicleID, TrackID trackID) {
        // Parameter validation is done in the factory
        this.simulationID = new SimulationID(UUID.randomUUID().toString());
        this.simulationName = simulationName;
        this.vehicleID = vehicleID;
        this.trackID = trackID;
    }

    /**
     * Constructor of the class Simulation with a given ID
     *
     * @param simulationID   is the ID of the simulation.
     * @param simulationName is the name of the simulation.
     * @param vehicleID      is the ID of the vehicle.
     * @param trackID        is the ID of the track.
     */
    Simulation(SimulationID simulationID, Name simulationName, VehicleID vehicleID, TrackID trackID) {
        // Parameter validation is done in the factory
        this.simulationID = simulationID;
        this.simulationName = simulationName;
        this.vehicleID = vehicleID;
        this.trackID = trackID;
    }
}

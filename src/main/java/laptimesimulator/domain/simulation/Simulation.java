package laptimesimulator.domain.simulation;

import laptimesimulator.ddd.IAggregateRoot;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
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
    private Name vehicleName;
    private TrackID trackID;
    private Name trackName;

    /**
     * Constructor of the class Simulation
     *
     * @param simulationName is the name of the simulation.
     * @param vehicleID      is the ID of the vehicle.
     * @param trackID        is the ID of the track.
     */
    Simulation(Name simulationName, VehicleID vehicleID, TrackID trackID, Name vehicleName, Name trackName) {
        // Parameter validation is done in the factory
        this.simulationID = new SimulationID(UUID.randomUUID().toString());
        this.simulationName = simulationName;
        this.vehicleID = vehicleID;
        this.trackID = trackID;
        this.vehicleName = vehicleName;
        this.trackName = trackName;
    }

    /**
     * Constructor of the class Simulation with a given ID
     *
     * @param simulationID   is the ID of the simulation.
     * @param simulationName is the name of the simulation.
     * @param vehicleID      is the ID of the vehicle.
     * @param trackID        is the ID of the track.
     */
    Simulation(SimulationID simulationID, Name simulationName, VehicleID vehicleID, TrackID trackID, Name vehicleName, Name trackName) {
        // Parameter validation is done in the factory
        this.simulationID = simulationID;
        this.simulationName = simulationName;
        this.vehicleID = vehicleID;
        this.trackID = trackID;
        this.vehicleName = vehicleName;
        this.trackName = trackName;
    }
}

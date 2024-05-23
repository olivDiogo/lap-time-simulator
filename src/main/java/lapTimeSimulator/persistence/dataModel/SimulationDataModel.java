package lapTimeSimulator.persistence.dataModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lapTimeSimulator.domain.simulation.Simulation;
import lombok.Getter;

@Entity
@Table(name = "Simulation")
@Getter
public class SimulationDataModel {
    @Id
    private String simulationID;
    private String simulationName;
    private String vehicleID;
    private String trackID;

    @Version
    private long version;

    public SimulationDataModel() {
    }

    /**
     * Constructor of the class.
     *
     * @param simulation is the simulation domain entity.
     */
    public SimulationDataModel(Simulation simulation) {
        if (simulation == null) {
            throw new IllegalArgumentException("The simulation cannot be null.");
        }

        this.simulationID = simulation.getSimulationID().getId();
        this.simulationName = simulation.getSimulationName().getStrName();
        this.vehicleID = simulation.getVehicleID().getId();
        this.trackID = simulation.getTrackID().getId();
    }
}

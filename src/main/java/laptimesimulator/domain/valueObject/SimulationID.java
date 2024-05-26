package laptimesimulator.domain.valueObject;

import laptimesimulator.ddd.IDomainID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SimulationID implements IDomainID {
    private final String id;

    /**
     * Constructor of the class SimulationID.
     *
     * @param id is the ID of the simulation.
     */
    public SimulationID(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Simulation ID must be a non-empty string.");
        }
        this.id = id;
    }

}

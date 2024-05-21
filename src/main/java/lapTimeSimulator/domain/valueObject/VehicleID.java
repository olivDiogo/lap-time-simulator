package lapTimeSimulator.domain.valueObject;

import jakarta.validation.constraints.NotNull;
import lapTimeSimulator.ddd.IDomainID;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class VehicleID implements IDomainID {
    private final String id;

    /**
     * Constructor of the class VehicleID.
     *
     * @param id is the ID of the vehicle.
     */
    public VehicleID(String id) {
        if(id == null || id.isBlank()) {
            throw new IllegalArgumentException("Vehicle ID must be a non-empty string.");
        }
        this.id = id;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of the vehicle.
     */
    public String getId() {
        return id;
    }
}

package laptimesimulator.domain.valueObject;

import laptimesimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ChassisModel implements IValueObject {
    private final double mass;

    /**
     * Constructor of the class ChassisModel
     *
     * @param mass is the mass of the object.
     */
    public ChassisModel(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("Mass value must be positive.");
        }

        this.mass = mass;
    }
}

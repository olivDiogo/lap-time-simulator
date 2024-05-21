package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BrakeModel implements IValueObject {
    private final double pressureToTorqueRatio;

    /**
     * Constructor of the class BrakeModel
     *
     * @param pressureToTorqueRatio is the pressure to torque ratio of the object.
     */
    public BrakeModel(double pressureToTorqueRatio) {
        if (pressureToTorqueRatio < 0) {
            throw new IllegalArgumentException("Pressure to torque ratio must be positive.");
        }

        this.pressureToTorqueRatio = pressureToTorqueRatio;
    }
}

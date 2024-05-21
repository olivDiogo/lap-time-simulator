package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PowertrainModel implements IValueObject {
    private final double power;
    private final double torque;

    /**
     * Constructor of the class PowertrainModel
     *
     * @param power is the power of the object.
     * @param torque is the torque of the object.
     */
    public PowertrainModel(double power, double torque) {
        if (power < 0 || torque < 0) {
            throw new IllegalArgumentException("Power and torque values must be positive.");
        }

        this.power = power;
        this.torque = torque;
    }
}

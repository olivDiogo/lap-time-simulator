package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PowertrainModelCombustion implements IValueObject {
    private final double powerMax;
    private final double torqueMax;
    private final double rpmPowerMax;
    private final double rpmTorqueMax;

    /**
     * Constructor of the class PowertrainModel
     *
     * @param powerMax is the power of the object.
     * @param torqueMax is the torque of the object.
     */
    public PowertrainModelCombustion(double powerMax, double torqueMax, double rpmPowerMax, double rpmTorqueMax) {
        if (powerMax <= 0 || torqueMax <= 0 || rpmPowerMax <= 0 || rpmTorqueMax <= 0) {
            throw new IllegalArgumentException("Engine parameters must be positive.");
        }

        this.powerMax = powerMax;
        this.torqueMax = torqueMax;
        this.rpmPowerMax = rpmPowerMax;
        this.rpmTorqueMax = rpmTorqueMax;
    }
}

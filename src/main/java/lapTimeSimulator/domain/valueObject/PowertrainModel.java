package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PowertrainModel implements IValueObject {
    private final double powerMax;
    private final double torqueMax;
    private final double rpmPowerMax;
    private final double rpmTorqueMax;

    /**
     * Constructor of the class PowertrainModel for combustion powertrains.
     *
     * @param powerMax     is the maximum power of the engine
     * @param torqueMax    is the maximum torque of the engine
     * @param rpmPowerMax  is the RPM at which the engine reaches its maximum power
     * @param rpmTorqueMax is the RPM at which the engine reaches its maximum torque
     */
    public PowertrainModel(double powerMax, double torqueMax, Double rpmPowerMax, Double rpmTorqueMax) {
        if (powerMax <= 0 || torqueMax <= 0 || rpmPowerMax <= 0 || rpmTorqueMax <= 0) {
            throw new IllegalArgumentException("PowertrainModel parameters must be greater than 0");
        }
        this.powerMax = powerMax;
        this.torqueMax = torqueMax;
        this.rpmPowerMax = rpmPowerMax;
        this.rpmTorqueMax = rpmTorqueMax;
    }

    /**
     * Constructor of the class PowertrainModel for electric powertrains.
     *
     * @param powerMax  is the max power output of the electric motor
     * @param torqueMax is the max torque output of the electric motor
     */
    public PowertrainModel(double powerMax, double torqueMax) {
        if (powerMax <= 0 || torqueMax <= 0) {
            throw new IllegalArgumentException("PowertrainModel parameters must be greater than 0");
        }
        this.powerMax = powerMax;
        this.torqueMax = torqueMax;
        this.rpmPowerMax = 0;
        this.rpmTorqueMax = 0;
    }
}

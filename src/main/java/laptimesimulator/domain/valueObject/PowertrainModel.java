package laptimesimulator.domain.valueObject;

import laptimesimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
public class PowertrainModel implements IValueObject {
    private final double powerMax;
    private final double torqueMax;
    private final Double rpmPowerMax;
    private final Double rpmTorqueMax;
    private final PowertrainType powertrainType;

    /**
     * Constructor of the class PowertrainModel for combustion powertrains.
     *
     * @param powerMax     is the maximum power of the engine
     * @param torqueMax    is the maximum torque of the engine
     * @param rpmPowerMax  is the RPM at which the engine reaches its maximum power
     * @param rpmTorqueMax is the RPM at which the engine reaches its maximum torque
     */
    public PowertrainModel(double powerMax, double torqueMax, Double rpmPowerMax, Double rpmTorqueMax, PowertrainType powertrainType) {
        if(powertrainType == null){
            throw new IllegalArgumentException("PowertrainType must be defined");
        }

        if (Objects.equals(powertrainType.getValue(), PowertrainType.COMBUSTION.getValue()) && (powerMax <= 0 || torqueMax <= 0 || rpmPowerMax <= 0 || rpmTorqueMax <= 0)) {
            throw new IllegalArgumentException("PowertrainModel parameters must be greater than 0");
        }

        if (Objects.equals(powertrainType.getValue(), PowertrainType.ELECTRIC.getValue()) && (powerMax <= 0 || torqueMax <= 0)) {
            throw new IllegalArgumentException("PowertrainModel parameters must be greater than 0");
        }

        if(powertrainType == PowertrainType.ELECTRIC){
            this.rpmPowerMax = null;
            this.rpmTorqueMax = null;
        } else {
            this.rpmPowerMax = rpmPowerMax;
            this.rpmTorqueMax = rpmTorqueMax;
        }

        this.powerMax = powerMax;
        this.torqueMax = torqueMax;
        this.powertrainType = powertrainType;
    }

}

package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;

public class PowertrainModelElectric implements IValueObject {
    private final double powerMax;
    private final double torqueMax;

    /**
     * Constructor of the class PowertrainModelElectric
     *
     * @param powerMax is the power of the object.
     * @param torqueMax is the torque of the object.
     */
    public PowertrainModelElectric(double powerMax, double torqueMax) {
        if (powerMax <= 0 || torqueMax <= 0) {
            throw new IllegalArgumentException("Engine parameters must be positive.");
        }

        this.powerMax = powerMax;
        this.torqueMax = torqueMax;
    }
}

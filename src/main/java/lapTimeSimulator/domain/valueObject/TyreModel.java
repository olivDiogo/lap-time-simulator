package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TyreModel implements IValueObject {
    private final double longitudinalGrip;
    private final double lateralGrip;

    /**
     * Constructor of the class TyreModel
     *
     * @param longitudinalGrip is the longitudinal grip of the object.
     * @param lateralGrip      is the lateral grip of the object.
     */
    public TyreModel(double longitudinalGrip, double lateralGrip) {
        if (longitudinalGrip < 0 || lateralGrip < 0) {
            throw new IllegalArgumentException("Grip values must be positive.");
        }

        this.longitudinalGrip = longitudinalGrip;
        this.lateralGrip = lateralGrip;
    }
}

package laptimesimulator.domain.valueObject;

import laptimesimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TyreModel implements IValueObject {
    private final double longitudinalGrip;
    private final double lateralGrip;
    private final double tyreRadius;

    /**
     * Constructor of the class TyreModel
     *
     * @param longitudinalGrip is the longitudinal grip of the object.
     * @param lateralGrip      is the lateral grip of the object.
     */
    public TyreModel(double longitudinalGrip, double lateralGrip, double tyreRadius) {
        this.tyreRadius = tyreRadius;
        if (longitudinalGrip < 0 || lateralGrip < 0 || tyreRadius <= 0) {
            throw new IllegalArgumentException("Tyre parameters must be positive.");
        }

        this.longitudinalGrip = longitudinalGrip;
        this.lateralGrip = lateralGrip;
    }
}

package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class AeroModel implements IValueObject {
    private final double downforceCoefficient;
    private final double dragCoefficient;

    public AeroModel(double downforceCoefficient, double dragCoefficient) {
        if (dragCoefficient > 0) {
            throw new IllegalArgumentException("Drag coefficient cannot be positive.");
        }

        this.downforceCoefficient = downforceCoefficient;
        this.dragCoefficient = dragCoefficient;
    }
}

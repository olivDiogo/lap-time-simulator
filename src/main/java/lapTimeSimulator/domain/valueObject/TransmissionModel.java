package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class TransmissionModel implements IValueObject {
    private final int numberOfGears;
    private final List<Double> gears;
    private final double finalDriveRatio;

    /**
     * Constructor of the class TransmissionModel
     *
     * @param numberOfGears   is the number of gears of the object.
     * @param gears           is the array of gears of the object.
     * @param finalDriveRatio is the final drive ratio of the object.
     */
    public TransmissionModel(int numberOfGears, List<Double> gears, double finalDriveRatio) {
        if (numberOfGears < 0 || finalDriveRatio < 0) {
            throw new IllegalArgumentException("Number of gears and final drive ratio must be positive.");
        }
        if (gears == null) {
            throw new IllegalArgumentException("The list of gears must not be null.");
        }

        this.numberOfGears = numberOfGears;
        this.gears = gears;
        this.finalDriveRatio = finalDriveRatio;
    }
}

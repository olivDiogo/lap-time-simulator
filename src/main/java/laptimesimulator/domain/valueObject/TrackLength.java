package laptimesimulator.domain.valueObject;

import laptimesimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TrackLength implements IValueObject {
    private final int length;

    /**
     * Constructor of the class TrackLength
     *
     * @param length is the length of the track.
     */
    public TrackLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("The length must be a positive number.");
        }
        this.length = length;
    }
}

package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class AeroModel implements IValueObject {
    private final double downforce;
    private final double drag;
}

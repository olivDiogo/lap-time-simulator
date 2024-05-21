package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Name implements IValueObject {
    private final String strName;

    /**
     * Constructor of the class Description
     *
     * @param strName is the strName of the object.
     */
    public Name(String strName) {
        if (strName == null || strName.isBlank()){
            throw new IllegalArgumentException("The name must be a non-empty string.");
        }
        this.strName = strName;
    }
}

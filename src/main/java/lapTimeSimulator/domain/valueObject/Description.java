package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Description implements IValueObject {
    private final String description;

    /**
     * Constructor of the class Description
     *
     * @param description is the description of the object.
     */
    public Description(String description) {
        if (description == null || description.isBlank()){
            throw new IllegalArgumentException("Description must be a non-empty string.");
        }
        this.description = description;
    }
}

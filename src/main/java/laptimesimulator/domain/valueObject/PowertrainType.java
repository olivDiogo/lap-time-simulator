package laptimesimulator.domain.valueObject;

import lombok.Getter;

@Getter
public enum PowertrainType {
    COMBUSTION("combustion"),
    ELECTRIC("electric");

    private final String value;

    PowertrainType(String value) {
        this.value = value;
    }
}

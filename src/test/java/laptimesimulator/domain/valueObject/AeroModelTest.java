package laptimesimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AeroModelTest {

    @Test
    void shouldCreateAeroModel_whenParametersAreValid() {
        // Arrange
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;

        // Act
        AeroModel aeroModel = new AeroModel(downforceCoefficient, dragCoefficient);

        // Assert
        assertNotNull(aeroModel);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenDragCoefficientIsPositive() {
        // Arrange
        double downforceCoefficient = 1.0;
        double dragCoefficient = 2.0;

        String expectedMessage = "Drag coefficient cannot be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AeroModel(downforceCoefficient, dragCoefficient);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

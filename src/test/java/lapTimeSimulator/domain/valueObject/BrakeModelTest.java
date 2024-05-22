package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrakeModelTest {

    @Test
    void shouldCreateBrakeModel_whenPressureToTorqueRatioIsPositive() {
        // Arrange
        double pressureToTorqueRatio = 1.0;

        // Act
        BrakeModel brakeModel = new BrakeModel(pressureToTorqueRatio);

        // Assert
        assertNotNull(brakeModel);
    }

    @Test
    void shouldCreateBrakeModel_whenPressureToTorqueRatioIsZero() {
        // Arrange
        double pressureToTorqueRatio = 0;

        // Act
        BrakeModel brakeModel = new BrakeModel(pressureToTorqueRatio);

        // Assert
        assertNotNull(brakeModel);
    }


    @Test
    void shouldThrowException_whenPressureToTorqueRatioIsNegative() {
        // Arrange
        double pressureToTorqueRatio = -1.0;
        String expectedMessage = "Pressure to torque ratio must be positive.";

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BrakeModel(pressureToTorqueRatio);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

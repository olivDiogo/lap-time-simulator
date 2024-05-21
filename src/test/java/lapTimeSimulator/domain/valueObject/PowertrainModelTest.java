package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PowertrainModelTest {

    @Test
    void shouldCreatePowertrainModel_whenPowerAndTorqueArePositive() {
        // Arrange
        double power = 100;
        double torque = 50;

        // Act
        PowertrainModel powertrainModel = new PowertrainModel(power, torque);

        // Assert
        assertNotNull(powertrainModel);
    }

    @Test
    void shouldThrowException_whenPowerIsNegative() {
        // Arrange
        double power = -100;
        double torque = 50;

        String expectedMessage = "Power and torque values must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(power, torque);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTorqueIsNegative() {
        // Arrange
        double power = 100;
        double torque = -50;

        String expectedMessage = "Power and torque values must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(power, torque);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}

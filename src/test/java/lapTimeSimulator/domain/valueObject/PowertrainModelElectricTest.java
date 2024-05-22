package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PowertrainModelElectricTest {

    @Test
    void shouldCreatePowertrainModel_whenParametersAreValid() {
        // Arrange
        double power = 100;
        double torque = 50;

        // Act
        PowertrainModelElectric powertrainModelElectric = new PowertrainModelElectric(power, torque);

        // Assert
        assertNotNull(powertrainModelElectric);
    }

    @Test
    void shouldThrowException_whenPowerMaxIsNegative() {
        // Arrange
        double powerMax = -100;
        double torqueMax = 50;

        String expectedMessage = "Engine parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModelElectric(powerMax, torqueMax);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenTorqueMaxIsNegative() {
        // Arrange
        double powerMax = 100;
        double torqueMax = -50;

        String expectedMessage = "Engine parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModelElectric(powerMax, torqueMax);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

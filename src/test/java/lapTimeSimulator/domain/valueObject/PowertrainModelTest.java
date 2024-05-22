package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PowertrainModelTest {

    @Test
    void shouldCreatePowertrainModel_whenParametersAreValid() {
        // Arrange
        double power = 100;
        double torque = 50;
        double rpmPower = 5000;
        double rpmTorque = 2500;

        // Act
        PowertrainModel powertrainModel = new PowertrainModel(power, torque, rpmPower, rpmTorque);

        // Assert
        assertNotNull(powertrainModel);
    }

    @Test
    void shouldThrowException_whenPowerMaxIsNegative() {
        // Arrange
        double powerMax = -100;
        double torqueMax = 50;
        double rpmPowerMax = 5000;
        double rpmTorqueMax = 2500;

        String expectedMessage = "Engine parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenTorqueMaxIsNegative() {
        // Arrange
        double powerMax = 100;
        double torqueMax = -50;
        double rpmPowerMax = 5000;
        double rpmTorqueMax = 2500;

        String expectedMessage = "Engine parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenRpmPowerMaxIsNegative() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 50;
        double rpmPowerMax = -5000;
        double rpmTorqueMax = 2500;

        String expectedMessage = "Engine parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenRpmTorqueMaxIsNegative() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 50;
        double rpmPowerMax = 5000;
        double rpmTorqueMax = -2500;

        String expectedMessage = "Engine parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

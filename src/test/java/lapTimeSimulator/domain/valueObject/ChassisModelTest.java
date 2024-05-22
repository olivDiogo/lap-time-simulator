package lapTimeSimulator.domain.valueObject;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChassisModelTest {

    @Test
    void shouldCreateChassisModel_whenMassIsPositive() {
        // Assert
        double mass = 100;

        // Act
        ChassisModel chassisModel = new ChassisModel(mass);

        // Assert
        assertNotNull(chassisModel);
    }

    @Test
    void shouldThrowException_whenMassIsZero() {
        // Assert
        double mass = 0;
        String expectedMessage = "Mass value must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ChassisModel(mass);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void shouldThrowException_whenMassIsNegative() {
        // Assert
        double mass = -100;
        String expectedMessage = "Mass value must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ChassisModel(mass);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

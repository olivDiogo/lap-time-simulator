package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleIDTest {
    @Test
    void shouldCreateVehicleID_whenParameterIsValid() {
        // Arrange
        String strVehicleID = "1";

        // Act
        VehicleID vehicleID = new VehicleID(strVehicleID);

        // Assert
        assertNotNull(vehicleID);
    }

    @Test
    void shouldThrowException_whenParameterIsNull() {
        // Arrange
        String expectedMessage = "Vehicle ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new VehicleID(null);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsEmpty() {
        // Arrange
        String strVehicleID = "";
        String expectedMessage = "Vehicle ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new VehicleID(strVehicleID);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsBlank() {
        // Arrange
        String strVehicleID = " ";
        String expectedMessage = "Vehicle ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new VehicleID(strVehicleID);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

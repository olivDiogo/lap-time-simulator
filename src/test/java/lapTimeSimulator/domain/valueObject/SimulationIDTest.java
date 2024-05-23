package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationIDTest {

    @Test
    void shouldCreateSimulationID_whenParameterIsValid() {
        // Arrange
        String strSimulationID = "1";

        // Act
        SimulationID simulationID = new SimulationID(strSimulationID);

        // Assert
        assertNotNull(simulationID);
    }

    @Test
    void shouldThrowException_whenParameterIsNull() {
        // Arrange
        String expectedMessage = "Simulation ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SimulationID(null);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsEmpty() {
        // Arrange
        String strSimulationID = "";
        String expectedMessage = "Simulation ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SimulationID(strSimulationID);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsBlank() {
        // Arrange
        String strSimulationID = " ";
        String expectedMessage = "Simulation ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SimulationID(strSimulationID);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

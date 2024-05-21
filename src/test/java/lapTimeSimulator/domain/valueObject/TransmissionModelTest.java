package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransmissionModelTest {

    @Test
    void shouldCreateTransmissionModel_whenArgumentsAreValid() {
        // Arrange
        int numberOfGears = 6;
        List<Double> gears = Arrays.asList(3.5, 2.5, 1.8, 1.4, 1.0, 0.8);
        double finalDriveRatio = 3.9;

        // Act
        TransmissionModel transmissionModel = new TransmissionModel(numberOfGears, gears, finalDriveRatio);

        // Assert
        assertNotNull(transmissionModel);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenNumberOfGearsIsNegative() {
        // Arrange
        int numberOfGears = -6;
        List<Double> gears = Arrays.asList(3.5, 2.5, 1.8, 1.4, 1.0, 0.8);
        double finalDriveRatio = 3.9;

        String expectedMessage = "Number of gears and final drive ratio must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TransmissionModel(numberOfGears, gears, finalDriveRatio);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenFinalDriveRatioIsNegative() {
        // Arrange
        int numberOfGears = 6;
        List<Double> gears = Arrays.asList(3.5, 2.5, 1.8, 1.4, 1.0, 0.8);
        double finalDriveRatio = -3.9;

        String expectedMessage = "Number of gears and final drive ratio must be positive.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TransmissionModel(numberOfGears, gears, finalDriveRatio);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenGearsIsNull() {
        // Arrange
        int numberOfGears = 6;
        double finalDriveRatio = 3.9;

        String expectedMessage = "The list of gears must not be null.";

        // Act & Assert
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TransmissionModel(numberOfGears, null, finalDriveRatio);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}

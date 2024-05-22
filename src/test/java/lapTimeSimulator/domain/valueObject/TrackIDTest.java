package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TrackIDTest {
    @Test
    void shouldCreateVehicleID_whenParameterIsValid() {
        // Arrange
        String strTrackID = "1";

        // Act
        TrackID trackID = new TrackID(strTrackID);

        // Assert
        assertNotNull(trackID);
    }

    @Test
    void shouldThrowException_whenParameterIsNull() {
        // Arrange
        String expectedMessage = "Track ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TrackID(null);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsEmpty() {
        // Arrange
        String strTrackID = "";
        String expectedMessage = "Track ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TrackID(strTrackID);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsBlank() {
        // Arrange
        String strTrackID = " ";
        String expectedMessage = "Track ID must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TrackID(strTrackID);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
}

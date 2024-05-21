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
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TrackID(null);
        });
        assertEquals("Track ID must be a non-empty string.", exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsEmpty() {
        // Arrange
        String strTrackID = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TrackID(strTrackID);
        });
        assertEquals("Track ID must be a non-empty string.", exception.getMessage());
    }

    @Test
    void shouldThrowException_whenParameterIsBlank() {
        // Arrange
        String strTrackID = " ";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TrackID(strTrackID);
        });
        assertEquals("Track ID must be a non-empty string.", exception.getMessage());
    }
}

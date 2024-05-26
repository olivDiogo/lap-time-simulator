package laptimesimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackLengthTest {

    @Test
    void shouldInstantiateTrackLength_whenLengthIsValid(){
        // Arrange
        int length = 1;

        // Act
        TrackLength trackLength = new TrackLength(length);

        // Assert
        assertNotNull(trackLength);
    }

    @Test
    void shouldThrowException_whenLengthIsNegative(){
        // Arrange
        int length = -1;
        String expectedMessage = "The length must be a positive number.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new TrackLength(length));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenLengthIsZero(){
        // Arrange
        int length = 0;
        String expectedMessage = "The length must be a positive number.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new TrackLength(length));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}

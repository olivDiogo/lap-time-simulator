package laptimesimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void shouldCreateNameObject_whenNameIsValid(){
        // Arrange
        String name = "Name";

        // Act
        Name nameObject = new Name(name);

        // Assert
        assertNotNull(nameObject);
    }

    @Test
    void shouldThrowException_whenNameIsNull(){
        // Arrange
        String expectedMessage = "The name must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(null);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenNameIsEmpty(){
        // Arrange
        String name = "";
        String expectedMessage = "The name must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenNameIsBlank(){
        // Arrange
        String name = " ";
        String expectedMessage = "The name must be a non-empty string.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}

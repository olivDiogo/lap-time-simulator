package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TyreModelTest {

    @Test
    void shouldCreateTyreModel_whenParametersAreValid(){
        // Arrange
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;

        // Act
        TyreModel tyreModel = new TyreModel(longitudinalGrip, lateralGrip);

        // Assert
        assertNotNull(tyreModel);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenLongitudinalGripIsNegative(){
        // Arrange
        double longitudinalGrip = -1.0;
        double lateralGrip = 1.0;

        String expectedMessage = "Grip values must be positive.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TyreModel(longitudinalGrip, lateralGrip);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenLateralGripIsNegative(){
        // Arrange
        double longitudinalGrip = 1.0;
        double lateralGrip = -1.0;

        String expectedMessage = "Grip values must be positive.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TyreModel(longitudinalGrip, lateralGrip);
        });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}

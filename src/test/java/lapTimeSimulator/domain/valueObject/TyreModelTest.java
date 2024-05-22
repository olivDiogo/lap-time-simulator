package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TyreModelTest {

    @Test
    void shouldCreateTyreModel_whenParametersAreValid(){
        // Arrange
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 0.3;

        // Act
        TyreModel tyreModel = new TyreModel(longitudinalGrip, lateralGrip, tyreRadius);

        // Assert
        assertNotNull(tyreModel);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenLongitudinalGripIsNegative(){
        // Arrange
        double longitudinalGrip = -1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 0.3;

        String expectedMessage = "Tyre parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TyreModel(longitudinalGrip, lateralGrip, tyreRadius);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenLateralGripIsNegative(){
        // Arrange
        double longitudinalGrip = 1.0;
        double lateralGrip = -1.0;
        double tyreRadius = 0.3;

        String expectedMessage = "Tyre parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TyreModel(longitudinalGrip, lateralGrip, tyreRadius);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTyreRadiusIsNegative(){
        // Arrange
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = -0.3;

        String expectedMessage = "Tyre parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TyreModel(longitudinalGrip, lateralGrip, tyreRadius);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTyreRadiusIsZero(){
        // Arrange
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 0;

        String expectedMessage = "Tyre parameters must be positive.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new TyreModel(longitudinalGrip, lateralGrip, tyreRadius);
        });

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


}

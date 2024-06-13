package laptimesimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowertrainModelTest {

    @Test
    void shouldCreateCombustionPowertrainModel_whenParametersAreValid() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act
        PowertrainModel powertrainModel = new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);

        // Assert
        assertNotNull(powertrainModel);
    }

    @Test
    void shouldCreateElectricPowertrainModel_whenParametersAreValid() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = 0;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.ELECTRIC;

        // Act
        PowertrainModel powertrainModel = new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);

        // Assert
        assertNotNull(powertrainModel);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenPowerMaxIsEqualToZero() {
        // Arrange
        double powerMax = 0;
        double torqueMax = 200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenPowerMaxIsNegative(){
        // Arrange
        double powerMax = -100;
        double torqueMax = 200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTorqueMaxIsEqualToZero() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 0;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTorqueMaxIsNegative(){
        // Arrange
        double powerMax = 100;
        double torqueMax = -200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRpmPowerMaxIsEqualToZero() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = 0;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRpmPowerMaxIsNegative(){
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = -300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRpmTorqueMaxIsEqualToZero() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 0;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRpmTorqueMaxIsNegative(){
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = -400;
        PowertrainType powerTrainType = PowertrainType.COMBUSTION;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenPowerMaxIsEqualToZeroForElectricPowertrain() {
        // Arrange
        double powerMax = 0;
        double torqueMax = 200;
        Double rpmPowerMax = 0.0;
        Double rpmTorqueMax = 0.0;
        PowertrainType powerTrainType = PowertrainType.ELECTRIC;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenPowerMaxIsNegativeForElectricPowertrain(){
        // Arrange
        double powerMax = -100;
        double torqueMax = 200;
        Double rpmPowerMax = 0.0;
        Double rpmTorqueMax = 0.0;
        PowertrainType powerTrainType = PowertrainType.ELECTRIC;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTorqueMaxIsEqualToZeroForElectricPowertrain() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 0;
        Double rpmPowerMax = 0.0;
        Double rpmTorqueMax = 0.0;
        PowertrainType powerTrainType = PowertrainType.ELECTRIC;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTorqueMaxIsNegativeForElectricPowertrain(){
        // Arrange
        double powerMax = 100;
        double torqueMax = -200;
        Double rpmPowerMax = 0.0;
        Double rpmTorqueMax = 0.0;
        PowertrainType powerTrainType = PowertrainType.ELECTRIC;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainModel parameters must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldThrowNullPointerExceptionException_whenPowertrainTypeIsNull() {
        // Arrange
        double powerMax = 100;
        double torqueMax = 200;
        double rpmPowerMax = 300;
        double rpmTorqueMax = 400;
        PowertrainType powerTrainType = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PowertrainModel(powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powerTrainType);
        });

        // Assert
        assertEquals("PowertrainType must be defined", exception.getMessage());
    }
}

package lapTimeSimulator.service;

import lapTimeSimulator.domain.valueObject.VehicleParameters;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.persistence.vehicle.IVehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class VehicleServiceTest {

    @MockBean
    IVehicleRepository vehicleRepository;

    @MockBean
    IVehicleFactory vehicleFactory;

    @Test
    void shouldInstantiateVehicleService_whenParametersAreValid() {
        // Act
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository);

        // Assert
        assertNotNull(vehicleService);
    }

//    @Test
//    void shouldThrowException_whenVehicleFactoryIsNull() {
//        // Arrange
//        String expectedMessage = "Vehicle factory and repository cannot be null.";
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            new VehicleService(null, vehicleRepository);
//        });
//
//        // Assert
//        String actualMessage = exception.getMessage();
//        assertEquals(expectedMessage, actualMessage);
//    }
//
//    @Test
//    void shouldThrowException_whenVehicleRepositoryIsNull() {
//        // Arrange
//        String expectedMessage = "Vehicle factory and repository cannot be null.";
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            new VehicleService(vehicleFactory, null);
//        });
//
//        // Assert
//        String actualMessage = exception.getMessage();
//        assertEquals(expectedMessage, actualMessage);
//    }

    @Test
    void shouldCreateVehicle_whenParametersAreValid() {
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository);

        VehicleParameters vehicleParameters = mock(VehicleParameters.class);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicleFactory.createVehicle(vehicleParameters)).thenReturn(vehicle);

        // Act
        Vehicle result = vehicleService.createVehicle(vehicleParameters);

        // Assert
        assertEquals(vehicle, result);
    }

    @Test
    void shouldThrowException_whenVehicleParametersAreNull(){
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository);

        String expectedMessage = "Vehicle parameters cannot be null.";

        when(vehicleFactory.createVehicle(null)).thenThrow(new IllegalArgumentException("Vehicle parameters cannot be null."));

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.createVehicle(null);
        });

        // Assert
        String result = e.getMessage();
        assertEquals(expectedMessage, result);
    }
}

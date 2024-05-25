package lapTimeSimulator.service;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.valueObject.VehicleParameters;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.persistence.vehicle.IVehicleRepository;
import lapTimeSimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class VehicleServiceTest {

    @MockBean
    IVehicleRepository vehicleRepository;

    @MockBean
    IVehicleFactory vehicleFactory;

    @MockBean
    IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper;

    @Test
    void shouldInstantiateVehicleService_whenParametersAreValid() {
        // Act
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        // Assert
        assertNotNull(vehicleService);
    }

    @Test
    void shouldCreateVehicle_whenParametersAreValid() {
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        VehicleParameters vehicleParameters = mock(VehicleParameters.class);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getVehicleID()).thenReturn(mock(VehicleID.class));
        when(vehicle.getVehicleID().getId()).thenReturn("1");
        when(vehicleFactory.createVehicle(vehicleParameters)).thenReturn(vehicle);

        VehicleDataOutDTO expected = new VehicleDataOutDTO("1", "vehicle", 1, -1, 1, 1, 1, 1,
                1.0, 1.0, 1, List.of(1.0), 1, 1, 1, 1);
        when(vehicleMapper.toDTO(vehicle)).thenReturn(expected);

        // Act
        VehicleDataOutDTO result = vehicleService.createVehicle(vehicleParameters);

        // Assert
        assertEquals(expected.vehicleID, result.vehicleID);
    }

    @Test
    void shouldThrowException_whenVehicleParametersAreNull(){
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

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

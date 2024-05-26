package laptimesimulator.service;

import laptimesimulator.ddd.IMapper;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.domain.valueObject.VehicleParameters;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.persistence.vehicle.IVehicleRepository;
import laptimesimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldGetVehicles_whenThereAreVehicles() {
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getVehicleID()).thenReturn(mock(VehicleID.class));
        when(vehicle.getVehicleID().getId()).thenReturn("1");
        when(vehicle.getVehicleName()).thenReturn(mock(Name.class));
        when(vehicle.getVehicleName().getStrName()).thenReturn("vehicle");

        List<Vehicle> vehicles = List.of(vehicle);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleDataOutDTO> expected = List.of(new VehicleDataOutDTO("1", "vehicle", 1, -1, 1, 1, 1, 1,
                1.0, 1.0, 1, List.of(1.0), 1, 1, 1, 1));
        when(vehicleMapper.toDTO(vehicles)).thenReturn(expected);

        // Act
        List<VehicleDataOutDTO> result = vehicleService.getVehicles();

        // Assert
        assertEquals(expected.get(0).vehicleName, result.get(0).vehicleName);
    }

    @Test
    void shouldGetEmptyVehicleList_whenThereAreNoVehicles() {
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        List<Vehicle> vehicles = List.of();
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleDataOutDTO> expected = List.of();
        when(vehicleMapper.toDTO(vehicles)).thenReturn(expected);

        // Act
        List<VehicleDataOutDTO> result = vehicleService.getVehicles();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void shouldGetVehicleByItsID_whenVehicleExists() {
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        VehicleID vehicleID = mock(VehicleID.class);
        when(vehicleID.getId()).thenReturn("1");

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getVehicleID()).thenReturn(vehicleID);
        when(vehicle.getVehicleName()).thenReturn(mock(Name.class));
        when(vehicle.getVehicleName().getStrName()).thenReturn("vehicle");

        Optional<Vehicle> optionalVehicle = Optional.of(vehicle);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(optionalVehicle);

        VehicleDataOutDTO expected = new VehicleDataOutDTO("1", "vehicle", 1, -1, 1, 1, 1, 1,
                1.0, 1.0, 1, List.of(1.0), 1, 1, 1, 1);
        when(vehicleMapper.toDTO(vehicle)).thenReturn(expected);

        // Act
        VehicleDataOutDTO result = vehicleService.getVehicle(vehicleID);

        // Assert
        assertEquals(expected.vehicleID, result.vehicleID);
    }

    @Test
    void shouldThrowException_whenVehicleDoesNotExist() {
        // Arrange
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        VehicleID vehicleID = mock(VehicleID.class);
        when(vehicleID.getId()).thenReturn("1");

        Optional<Vehicle> optionalVehicle = Optional.empty();
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(optionalVehicle);

        String expectedMessage = "Vehicle not found.";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            vehicleService.getVehicle(vehicleID);
        });

        // Assert
        String result = e.getMessage();
        assertEquals(expectedMessage, result);
    }
}

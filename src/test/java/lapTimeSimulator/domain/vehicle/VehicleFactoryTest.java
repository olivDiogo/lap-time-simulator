package lapTimeSimulator.domain.vehicle;

import lapTimeSimulator.domain.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class VehicleFactoryTest {

    @Test
    void shouldCreateVehicle_whenVehicleParametersAreValid() {
        // Arrange
        VehicleFactory vehicleFactory = new VehicleFactory();
        VehicleParameters vehicleParameters = mock(VehicleParameters.class);
        when(vehicleParameters.getVehicleName()).thenReturn(mock(Name.class));
        when(vehicleParameters.getAeroModel()).thenReturn(mock(AeroModel.class));
        when(vehicleParameters.getBrakeModel()).thenReturn(mock(BrakeModel.class));
        when(vehicleParameters.getChassisModel()).thenReturn(mock(ChassisModel.class));
        when(vehicleParameters.getPowertrainModel()).thenReturn(mock(PowertrainModel.class));
        when(vehicleParameters.getTransmissionModel()).thenReturn(mock(TransmissionModel.class));
        when(vehicleParameters.getTyreModel()).thenReturn(mock(TyreModel.class));

        try (MockedConstruction<VehicleID> vehicleIDMocked = mockConstruction(VehicleID.class)) {
            //Act
            Vehicle vehicle = vehicleFactory.createVehicle(vehicleParameters);

            //Assert
            assertNotNull(vehicle);
        }
    }

    @Test
    void shouldThrowIllegalArgumentException_whenVehicleParametersAreNull() {
        // Arrange
        VehicleFactory vehicleFactory = new VehicleFactory();

        String expectedMessage = "Vehicle parameters cannot be null.";

        //Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> vehicleFactory.createVehicle(null));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void shouldCreateVehicle_whenVehicleIDAndVehicleParametersAreValid() {
        // Arrange
        VehicleFactory vehicleFactory = new VehicleFactory();
        VehicleParameters vehicleParameters = mock(VehicleParameters.class);
        VehicleID vehicleID = mock(VehicleID.class);
        when(vehicleParameters.getVehicleName()).thenReturn(mock(Name.class));
        when(vehicleParameters.getAeroModel()).thenReturn(mock(AeroModel.class));
        when(vehicleParameters.getBrakeModel()).thenReturn(mock(BrakeModel.class));
        when(vehicleParameters.getChassisModel()).thenReturn(mock(ChassisModel.class));
        when(vehicleParameters.getPowertrainModel()).thenReturn(mock(PowertrainModel.class));
        when(vehicleParameters.getTransmissionModel()).thenReturn(mock(TransmissionModel.class));
        when(vehicleParameters.getTyreModel()).thenReturn(mock(TyreModel.class));

        try (MockedConstruction<VehicleID> vehicleIDMocked = mockConstruction(VehicleID.class)) {
            //Act
            Vehicle vehicle = vehicleFactory.createVehicle(vehicleID, vehicleParameters);

            //Assert
            assertNotNull(vehicle);
        }
    }

    @Test
    void shouldThrowIllegalArgumentException_whenVehicleIDIsNull() {
        // Arrange
        VehicleFactory vehicleFactory = new VehicleFactory();
        VehicleParameters vehicleParameters = mock(VehicleParameters.class);

        String expectedMessage = "Vehicle parameters cannot be null.";

        //Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> vehicleFactory.createVehicle(null, vehicleParameters));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenVehicleIDIsValidAndVehicleParametersAreNull() {
        // Arrange
        VehicleFactory vehicleFactory = new VehicleFactory();
        VehicleID vehicleID = mock(VehicleID.class);

        String expectedMessage = "Vehicle parameters cannot be null.";

        //Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> vehicleFactory.createVehicle(vehicleID, null));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}

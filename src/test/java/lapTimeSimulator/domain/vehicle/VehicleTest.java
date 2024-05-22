package lapTimeSimulator.domain.vehicle;

import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.valueObject.VehicleParameters;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class VehicleTest {

    @Test
    void shouldInstantiateVehicle_whenVehicleParametersAreValid() {
        // Assert
        VehicleParameters vehicleParameters = mock(VehicleParameters.class);

        try (MockedConstruction<VehicleID> vehicleIDMocked = mockConstruction(VehicleID.class)) {
            //Act
            Vehicle vehicle = new Vehicle(vehicleParameters);

            //Assert
            assertNotNull(vehicle);
        }
    }

    @Test
    void shouldInstantiateVehicle_whenVehicleIDAndVehicleParametersAreValid() {
        // Assert
        VehicleParameters vehicleParameters = mock(VehicleParameters.class);
        VehicleID vehicleID = mock(VehicleID.class);

        //Act
        Vehicle vehicle = new Vehicle(vehicleID, vehicleParameters);

        //Assert
        assertNotNull(vehicle);
    }
}

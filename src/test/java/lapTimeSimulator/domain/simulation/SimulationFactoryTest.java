package lapTimeSimulator.domain.simulation;

import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.SimulationID;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class SimulationFactoryTest {

    @Test
    void shouldCreateSimulation_whenParametersAreValid() {
        // Arrange
        SimulationFactory simulationFactory = new SimulationFactory();
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        try(MockedConstruction<SimulationID> mocked = mockConstruction(SimulationID.class)) {
            // Act
            Simulation simulation = simulationFactory.createSimulation(simulationName, vehicleID, trackID);

            // Assert
            assertNotNull(simulation);
        }
    }

    @Test
    void shouldThrowException_whenSimulationNameIsNull() {
        // Arrange
        SimulationFactory simulationFactory = new SimulationFactory();
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        String expectedMessage = "Simulation parameters cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> simulationFactory.createSimulation(null, vehicleID, trackID));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void shouldThrowException_whenVehicleIDIsNull() {
        // Arrange
        SimulationFactory simulationFactory = new SimulationFactory();
        Name simulationName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);

        String expectedMessage = "Simulation parameters cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> simulationFactory.createSimulation(simulationName, null, trackID));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void shouldThrowException_whenTrackIDIsNull() {
        // Arrange
        SimulationFactory simulationFactory = new SimulationFactory();
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);

        String expectedMessage = "Simulation parameters cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> simulationFactory.createSimulation(simulationName, vehicleID, null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void shouldCreateSimulation_whenParametersAreValidWithSimulationID() {
        // Arrange
        SimulationFactory simulationFactory = new SimulationFactory();
        SimulationID simulationID = mock(SimulationID.class);
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        // Act
        Simulation simulation = simulationFactory.createSimulation(simulationID, simulationName, vehicleID, trackID);

        // Assert
        assertNotNull(simulation);
    }

    @Test
    void shouldThrowException_whenSimulationIDIsNull() {
        // Arrange
        SimulationFactory simulationFactory = new SimulationFactory();
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        String expectedMessage = "Simulation parameters cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> simulationFactory.createSimulation(null, simulationName, vehicleID, trackID));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }
}

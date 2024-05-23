package lapTimeSimulator.domain.simulation;

import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.SimulationID;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class SimulationTest {
    @Test
    void shouldInstantiateSimulation_whenSimulationParametersAreValid() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        try(MockedConstruction<SimulationID> simulationIDMocked = mockConstruction(SimulationID.class)) {
            // Act
            Simulation simulation = new Simulation(simulationName, vehicleID, trackID);

            // Assert
            assertNotNull(simulation);
        }
    }

    @Test
    void shouldInstantiateSimulation_whenSimulationIDAndSimulationParametersAreValid() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);
        SimulationID simulationID = mock(SimulationID.class);

        // Act
        Simulation simulation = new Simulation(simulationID, simulationName, vehicleID, trackID);

        // Assert
        assertNotNull(simulation);
    }


}

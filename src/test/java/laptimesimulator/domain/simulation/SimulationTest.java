package laptimesimulator.domain.simulation;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
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
        Name vehicleName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Name trackName = mock(Name.class);

        try(MockedConstruction<SimulationID> simulationIDMocked = mockConstruction(SimulationID.class)) {
            // Act
            Simulation simulation = new Simulation(simulationName, vehicleID, trackID, vehicleName, trackName);

            // Assert
            assertNotNull(simulation);
        }
    }

    @Test
    void shouldInstantiateSimulation_whenSimulationIDAndSimulationParametersAreValid() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        Name vehicleName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Name trackName = mock(Name.class);
        SimulationID simulationID = mock(SimulationID.class);

        // Act
        Simulation simulation = new Simulation(simulationID, simulationName, vehicleID, trackID, vehicleName, trackName);

        // Assert
        assertNotNull(simulation);
    }


}

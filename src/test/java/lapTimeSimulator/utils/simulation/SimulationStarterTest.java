package lapTimeSimulator.utils.simulation;

import lapTimeSimulator.utils.dto.inputDataDTO.SimulationResultDTO;
import lapTimeSimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationStarterTest {

    @Test
    void shouldCreateJSONFileAndGetResultFileBack_whenSimulationDataOutDTOIsValid() {
        // Arrange
        String simulationID = "123456789";

        String vehicleID = "kjdf@984";
        String vehicleName = "Carocha";
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 600.0;
        double powerMax = 500;
        double torqueMax = 300;
        double rpmPowerMax = 5000;
        double rpmTorqueMax = 3000;
        int numberOfGears = 5;
        List<Double> gears = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        double finalDriveRatio = 3.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 4;
        double tyreRadius = 0.3;

        String trackID = "trackID";
        String trackName = "trackName";

        SimulationDataOutDTO simulationDataOutDTO = new SimulationDataOutDTO(simulationID, vehicleID, vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio,
                mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius, trackID, trackName);

        // Act
        SimulationResultDTO result = SimulationStarter.startSimulation(simulationDataOutDTO);

        // Assert
        Path outputPath = Paths.get("simulationData.json");
        assertTrue(Files.exists(outputPath), "Output file was not generated");
        assertEquals(simulationID, result.simulationID);
    }

    @Test
    void shouldThrowException_whenSimulationDataOutDTOIsNull() {
        // Arrange
        String expectedMessage = "Simulation data cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                SimulationStarter.startSimulation(null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }
}

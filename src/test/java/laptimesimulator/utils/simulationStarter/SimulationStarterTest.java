package laptimesimulator.utils.simulationStarter;

import laptimesimulator.utils.dto.inputDataDTO.SimulationResultDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationOptionsDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationTrackDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationVehicleDataOutDTO;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationStarterTest {

    @Test
    void shouldCreateSimulationDataJSONFilesAndGetResultFileBack_whenSimulationDataOutDTOIsValid() {
        // Arrange
        String simulationID = "123456789";
        String simulationName = "Simulation";

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
        List<Double> gears = List.of(1.0, 2.0, 3.0, 4.0, 5.0);
        double finalDriveRatio = 3.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 4;
        double tyreRadius = 0.3;

        String trackID = "trackID";
        String trackName = "trackName";

        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = new SimulationVehicleDataOutDTO(vehicleID, vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio,
                mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        SimulationTrackDataOutDTO simulationTrackDataOutDTO = new SimulationTrackDataOutDTO(trackID, trackName);
        SimulationOptionsDataOutDTO simulationOptionsDataOutDTO = new SimulationOptionsDataOutDTO(simulationID, simulationName);

        // Act
        SimulationResultDTO result = SimulationStarter.startSimulation(simulationVehicleDataOutDTO, simulationTrackDataOutDTO, simulationOptionsDataOutDTO);

        // Assert
        Path vehicleOutputPath = Paths.get("simulationVehicleData.json");
        assertTrue(Files.exists(vehicleOutputPath), "Output file was not generated");
        Path trackOutputPath = Paths.get("simulationTrackData.json");
        assertTrue(Files.exists(trackOutputPath), "Output file was not generated");
        Path simulationOutputPath = Paths.get("simulationOptionsData.json");
        assertTrue(Files.exists(simulationOutputPath), "Output file was not generated");
        assertEquals(simulationID, result.simulationID);
    }

    @Test
    void shouldThrowException_whenSimulationVehicleDataOutDTOIsNull() {
        // Arrange
        String expectedMessage = "Simulation data cannot be null.";
        SimulationTrackDataOutDTO simulationTrackDataOutDTO = new SimulationTrackDataOutDTO("trackID", "trackName");
        SimulationOptionsDataOutDTO simulationOptionsDataOutDTO = new SimulationOptionsDataOutDTO("123456789", "Simulation");

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                SimulationStarter.startSimulation(null, simulationTrackDataOutDTO, simulationOptionsDataOutDTO));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void shouldThrowException_whenSimulationTrackDataOutDTOIsNull(){
        // Arrange
        String expectedMessage = "Simulation data cannot be null.";
        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = new SimulationVehicleDataOutDTO("kjdf@984", "Carocha", 1.0, -1.0, 1.0, 600.0, 500, 300, 5000, 3000, List.of(1.0, 2.0, 3.0, 4.0, 5.0), 3.0, 1.0, 4, 0.3);
        SimulationOptionsDataOutDTO simulationOptionsDataOutDTO = new SimulationOptionsDataOutDTO("123456789", "Simulation");

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                SimulationStarter.startSimulation(simulationVehicleDataOutDTO, null, simulationOptionsDataOutDTO));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void shouldThrowException_whenSimulationOptionsDataOutDTOIsNull(){
        // Arrange
        String expectedMessage = "Simulation data cannot be null.";
        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = new SimulationVehicleDataOutDTO("kjdf@984", "Carocha", 1.0, -1.0, 1.0, 600.0, 500, 300, 5000, 3000, List.of(1.0, 2.0, 3.0, 4.0, 5.0), 3.0, 1.0, 4, 0.3);
        SimulationTrackDataOutDTO simulationTrackDataOutDTO = new SimulationTrackDataOutDTO("trackID", "trackName");

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                SimulationStarter.startSimulation(simulationVehicleDataOutDTO, simulationTrackDataOutDTO, null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }
}

package laptimesimulator.utils.simulationStarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import laptimesimulator.utils.dto.inputDataDTO.SimulationResultDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationOptionsDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationTrackDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationVehicleDataOutDTO;
import laptimesimulator.utils.runCppExe.RunCppExeWithJson;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class SimulationStarter {

    /**
     * Private constructor to hide the implicit public one.
     */
    private SimulationStarter() {
    }

    /**
     * Writes simulation data into a JSON file and waits for the algorithm application to process it and write the response.
     *
     * @param simulationVehicleDataOutDTO the simulation data to be written to the file
     * @return the simulation result
     */
    public static SimulationResultDTO startSimulation(SimulationVehicleDataOutDTO simulationVehicleDataOutDTO,
                                                      SimulationTrackDataOutDTO simulationTrackDataOutDTO, SimulationOptionsDataOutDTO simulationOptionsDataOutDTO) {
        sendSimulationData(simulationVehicleDataOutDTO, simulationTrackDataOutDTO, simulationOptionsDataOutDTO);


        String cppExePath = "cpp\\cmake-build-debug-visual-studio\\cpp.exe";
        String jsonDataPath = "simulationData.json";
        String workingDirectoryPath = System.getProperty("user.dir");

        RunCppExeWithJson.runCppExecutable(cppExePath, jsonDataPath, workingDirectoryPath);

        Path responseFilePath = Paths.get("simulationResult.json");
        return getSimulationResult(responseFilePath);
    }


    /**
     * Writes the simulation data to a file.
     *
     * @param simulationVehicleDataOutDTO the simulation data to be written to the file
     */
    private static void sendSimulationData(SimulationVehicleDataOutDTO simulationVehicleDataOutDTO,
                                           SimulationTrackDataOutDTO simulationTrackDataOutDTO,
                                           SimulationOptionsDataOutDTO simulationOptionsDataOutDTO) {
        if (simulationVehicleDataOutDTO == null || simulationTrackDataOutDTO == null || simulationOptionsDataOutDTO == null) {
            throw new IllegalArgumentException("Simulation data cannot be null.");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Create the output JSON structure
            SimulationDataOutDTO simulationOutputData = new SimulationDataOutDTO(
                    new SimJson(simulationOptionsDataOutDTO.simulationID, simulationOptionsDataOutDTO.simulationName),
                    new VehicleJson(
                            simulationVehicleDataOutDTO.vehicleID,
                            simulationVehicleDataOutDTO.vehicleName,
                            simulationVehicleDataOutDTO.sCz,
                            simulationVehicleDataOutDTO.sCx,
                            simulationVehicleDataOutDTO.rBrkF2P,
                            simulationVehicleDataOutDTO.mCar,
                            simulationVehicleDataOutDTO.PEngMax,
                            simulationVehicleDataOutDTO.MEngMax,
                            simulationVehicleDataOutDTO.nEngPMax,
                            simulationVehicleDataOutDTO.nEngMMax,
                            simulationVehicleDataOutDTO.gears,
                            simulationVehicleDataOutDTO.finalDriveRatio,
                            simulationVehicleDataOutDTO.mux0,
                            simulationVehicleDataOutDTO.muy0,
                            simulationVehicleDataOutDTO.rrTyre
                    ),
                    new TrackJson(
                            simulationTrackDataOutDTO.trackID,
                            simulationTrackDataOutDTO.trackName
                    )
            );

            // Convert the structured data to JSON
            String simulationDataJson = objectMapper.writeValueAsString(simulationOutputData);

            // Write JSON to file
            File outputFile = new File("simulationData.json");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(simulationDataJson);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AllArgsConstructor
    @Getter
    public static class SimJson {
        private String simulationID;
        private String simulationName;
    }

    @AllArgsConstructor
    @Getter
    public static class TrackJson {
        private String trackId;
        private String trackName;
    }

    @AllArgsConstructor
    @Getter
    public static class VehicleJson {
        private String vehicleId;
        private String vehicleName;
        private double sCz;
        private double sCx;
        private double rBrkF2P;
        private double mCar;
        private double pEngMax;
        private double mEngMax;
        private Double nEngPMax;
        private Double nEngMMax;
        private List<Double> gears;
        private double finalDriveRatio;
        private double mux;
        private double muy;
        private double rrTyre;
    }


    /**
     * Reads the simulation result from a file.
     *
     * @param responseFilePath the path to the file containing the simulation result
     * @return the simulation result
     */
    private static SimulationResultDTO getSimulationResult(Path responseFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Record the start time of the simulation
        Instant startTime = Instant.now();

        // Wait for the algorithm app to process and write response
        while (true) {
            if (Files.exists(responseFilePath) && Files.isRegularFile(responseFilePath) && Files.isReadable(responseFilePath)) {
                // Break the loop when the response file is found
                break;
            }

            // Check if 5 minutes have passed
            if (Duration.between(startTime, Instant.now()).toMinutes() >= 5) {
                throw new RuntimeException("Timeout waiting for response");
            }

            // Sleep for a short period of time to avoid busy waiting
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(responseFilePath.toFile()))) {
            // Read response
            String response = reader.readLine();

            // Convert response to SimulationResultDTO
            return objectMapper.readValue(response, SimulationResultDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}


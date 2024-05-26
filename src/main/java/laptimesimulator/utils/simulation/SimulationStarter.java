package laptimesimulator.utils.simulation;

import com.fasterxml.jackson.databind.ObjectMapper;
import laptimesimulator.utils.dto.inputDataDTO.SimulationResultDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class SimulationStarter {

    /**
     * Private constructor to hide the implicit public one.
     */
    private SimulationStarter() {
    }

    /**
     * Writes simulation data into a JSON file and waits for the algorithm application to process it and write the response.
     *
     * @param simulationDataOutDTO the simulation data to be written to the file
     * @return the simulation result
     */
    public static SimulationResultDTO startSimulation(SimulationDataOutDTO simulationDataOutDTO){
        sendSimulationData(simulationDataOutDTO);

        Path responseFilePath = Paths.get("simulationResult.json");
        return getSimulationResult(responseFilePath);
    }


    /**
     * Writes the simulation data to a file.
     *
     * @param simulationDataOutDTO the simulation data to be written to the file
     */
    private static void sendSimulationData(SimulationDataOutDTO simulationDataOutDTO) {
        if (simulationDataOutDTO == null) {
            throw new IllegalArgumentException("Simulation data cannot be null.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File outputFile = new File("simulationData.json");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Convert SimulationDataOutDTO to JSON
            String simulationDataJson = objectMapper.writeValueAsString(simulationDataOutDTO);

            // Write data to file
            writer.write(simulationDataJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
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


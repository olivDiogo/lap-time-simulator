package laptimesimulator.utils.simulationStarter;

import com.fasterxml.jackson.core.JsonGenerator;
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

        RunCppExeWithJson.runCppExecutable(cppExePath, jsonDataPath, workingDirectoryPath, workingDirectoryPath);

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
                            simulationVehicleDataOutDTO.type,
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
            // Had to use JsonGenerator to avoid casing issues with writeValueAsString() method
            File simulationDataFile = new File("simulationData.json");
            FileWriter fileWriter = new FileWriter(simulationDataFile);

            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(fileWriter);
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("sim");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("simulationId", simulationOutputData.sim.simulationID);
            jsonGenerator.writeObjectField("simulationName", simulationOutputData.sim.simulationName);
            jsonGenerator.writeEndObject();

            jsonGenerator.writeFieldName("vehicle");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("vehicleId", simulationOutputData.vehicle.vehicleId);
            jsonGenerator.writeObjectField("vehicleName", simulationOutputData.vehicle.vehicleName);
            jsonGenerator.writeObjectField("sCz", simulationOutputData.vehicle.sCz);
            jsonGenerator.writeObjectField("sCx", simulationOutputData.vehicle.sCx);
            jsonGenerator.writeObjectField("rBrkF2P", simulationOutputData.vehicle.rBrkF2P);
            jsonGenerator.writeObjectField("mCar", simulationOutputData.vehicle.mCar);
            jsonGenerator.writeObjectField("PEngMax", simulationOutputData.vehicle.PEngMax);
            jsonGenerator.writeObjectField("MEngMax", simulationOutputData.vehicle.MEngMax);
            jsonGenerator.writeObjectField("nEngPMax", simulationOutputData.vehicle.nEngPMax);
            jsonGenerator.writeObjectField("nEngMMax", simulationOutputData.vehicle.nEngMMax);
            jsonGenerator.writeObjectField("type", simulationOutputData.vehicle.type);
            jsonGenerator.writeObjectField("gears", simulationOutputData.vehicle.gears);
            jsonGenerator.writeObjectField("finalDriveRatio", simulationOutputData.vehicle.finalDriveRatio);
            jsonGenerator.writeObjectField("mux0", simulationOutputData.vehicle.mux0);
            jsonGenerator.writeObjectField("muy0", simulationOutputData.vehicle.muy0);
            jsonGenerator.writeObjectField("rrTyre", simulationOutputData.vehicle.rrTyre);
            jsonGenerator.writeEndObject();

            jsonGenerator.writeFieldName("track");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("trackId", simulationOutputData.track.trackId);
            jsonGenerator.writeObjectField("trackName", simulationOutputData.track.trackName);
            jsonGenerator.writeEndObject();

            jsonGenerator.close();
            fileWriter.close();

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
        private double PEngMax;
        private double MEngMax;
        private Double nEngPMax;
        private Double nEngMMax;
        private String type;
        private List<Double> gears;
        private double finalDriveRatio;
        private double mux0;
        private double muy0;        private double rrTyre;
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


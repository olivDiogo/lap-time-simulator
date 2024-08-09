package laptimesimulator.controller;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.service.SimulationService;
import laptimesimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationVehicleDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/simulations")
@AllArgsConstructor
public class SimulationController {
    private SimulationService simulationService;

    /**
     * Starts a simulation.
     *
     * @param simulationID is the simulation identifier.
     * @return the simulation entity.
     */
    @PostMapping("/start")
    public ResponseEntity<EntityModel<SimulationInfoOutDTO>> startSimulation(@RequestParam SimulationID simulationID) {
        SimulationInfoOutDTO simulation;
        try {
            simulation = simulationService.startSimulation(simulationID);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();  // Log the stack trace for debugging
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Link selfLink = linkTo(methodOn(SimulationController.class).startSimulation(simulationID)).withSelfRel();
        EntityModel<SimulationInfoOutDTO> response = EntityModel.of(simulation, selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Creates a simulation.
     *
     * @param simulationDataInDTO is the simulation data.
     * @return the simulation entity
     */
    @PostMapping
    public ResponseEntity<EntityModel<SimulationInfoOutDTO>> createSimulation (@RequestBody SimulationDataInDTO simulationDataInDTO) {
        SimulationInfoOutDTO simulation;
        try {
            Name simulationName = new Name(simulationDataInDTO.simulationName);
            VehicleID vehicleID = new VehicleID(simulationDataInDTO.vehicleID);
            TrackID trackID = new TrackID(simulationDataInDTO.trackID);

            simulation = simulationService.createSimulation(simulationName, vehicleID, trackID);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();  // Log the stack trace for debugging
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Link selfLink = linkTo(methodOn(SimulationController.class).createSimulation(simulationDataInDTO)).withSelfRel();
        EntityModel<SimulationInfoOutDTO> response = EntityModel.of(simulation, selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Gets all the simulations.
     *
     * @return the simulation entity.
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<SimulationInfoOutDTO>>> getSimulations() {
        List<SimulationInfoOutDTO> simulations;

        try {
            simulations = simulationService.getSimulations();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<EntityModel<SimulationInfoOutDTO>> response = simulations.stream()
                .map(simulation -> EntityModel.of(simulation,
                        linkTo(methodOn(SimulationController.class).getSimulationById(simulation.simulationID)).withRel("get-simulation")
                                .withTitle("Get the simulation")
                                .withType("GET")))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{strSimulationID}")
    public ResponseEntity<EntityModel<SimulationInfoOutDTO>> getSimulationById(@PathVariable String strSimulationID) {
        SimulationInfoOutDTO simulation;
        SimulationID simulationID = new SimulationID(strSimulationID);

        try {
            simulation = simulationService.getSimulationByID(simulationID);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        EntityModel<SimulationInfoOutDTO> response = EntityModel.of(simulation);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

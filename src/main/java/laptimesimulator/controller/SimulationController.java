package laptimesimulator.controller;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.service.SimulationService;
import laptimesimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * @param simulationDataInDTO is the simulation data.
     * @return the simulation entity.
     */
    @PostMapping
    public ResponseEntity<EntityModel<SimulationDataOutDTO>> startSimulation(@RequestBody SimulationDataInDTO simulationDataInDTO) {

        SimulationDataOutDTO simulation;

        try {
            Name simulationName = new Name(simulationDataInDTO.simulationName);
            VehicleID vehicleID = new VehicleID(simulationDataInDTO.vehicleID);
            TrackID trackID = new TrackID(simulationDataInDTO.trackID);
            simulation = simulationService.startSimulation(simulationName, vehicleID, trackID);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Link selfLink = linkTo(methodOn(SimulationController.class).startSimulation(simulationDataInDTO)).withSelfRel();
        EntityModel<SimulationDataOutDTO> response = EntityModel.of(simulation, selfLink);

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

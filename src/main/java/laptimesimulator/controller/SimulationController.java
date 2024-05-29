package laptimesimulator.controller;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.service.SimulationService;
import laptimesimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
}

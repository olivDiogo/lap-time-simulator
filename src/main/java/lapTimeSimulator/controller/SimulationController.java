package lapTimeSimulator.controller;

import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.service.SimulationService;
import lapTimeSimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<EntityModel<Simulation>> startSimulation(SimulationDataInDTO simulationDataInDTO) {
        if (simulationDataInDTO == null) {
            throw new IllegalArgumentException("Simulation data cannot be null.");
        }

        Name simulationName = new Name(simulationDataInDTO.simulationName);
        VehicleID vehicleID = new VehicleID(simulationDataInDTO.vehicleID);
        TrackID trackID = new TrackID(simulationDataInDTO.trackID);

        Simulation simulation = simulationService.startSimulation(simulationName, vehicleID, trackID);

        Link selfLink = linkTo(methodOn(SimulationController.class).startSimulation(simulationDataInDTO)).withSelfRel();
        EntityModel<Simulation> response = EntityModel.of(simulation, selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

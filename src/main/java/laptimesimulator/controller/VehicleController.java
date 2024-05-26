package laptimesimulator.controller;

import laptimesimulator.domain.valueObject.*;
import laptimesimulator.service.VehicleService;
import laptimesimulator.utils.dto.inputDataDTO.VehicleDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import laptimesimulator.utils.vehicleParameters.VehicleParametersUtils;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    private VehicleService vehicleService;

    /**
     * Method to create a vehicle.
     *
     * @param vehicleDataInDTO is the vehicle data.
     * @return the vehicle data transfer object.
     */
    @PostMapping
    public ResponseEntity<EntityModel<VehicleDataOutDTO>> createVehicle(@RequestBody VehicleDataInDTO vehicleDataInDTO) {
        VehicleParameters vehicleParameters;

        try {
            vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataInDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        VehicleDataOutDTO vehicleDataOutDTO = vehicleService.createVehicle(vehicleParameters);

        Link selfLink = linkTo(methodOn(VehicleController.class).createVehicle(vehicleDataInDTO)).withSelfRel();
        Link getLink = linkTo(methodOn(VehicleController.class).getVehicleById(vehicleDataOutDTO.vehicleID)).withRel("get-vehicle");

        EntityModel<VehicleDataOutDTO> response = EntityModel.of(vehicleDataOutDTO, selfLink, getLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method to get all vehicles.
     *
     * @return the list of vehicles.
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<VehicleDataOutDTO>>> getVehicles() {
        List<VehicleDataOutDTO> vehicles = vehicleService.getVehicles();

        List<EntityModel<VehicleDataOutDTO>> response = vehicles.stream()
                .map(vehicle -> EntityModel.of(vehicle,
                        linkTo(methodOn(VehicleController.class).getVehicleById(vehicle.vehicleID)).withRel("get-vehicle")))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Method to get a vehicle by its ID.
     *
     * @param strVehicleID is the ID of the vehicle.
     * @return the vehicle data transfer object.
     */
    @GetMapping("/{strVehicleID}")
    public ResponseEntity<EntityModel<VehicleDataOutDTO>> getVehicleById(@PathVariable String strVehicleID) {
        if (strVehicleID == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null.");
        }

        VehicleID vehicleID = new VehicleID(strVehicleID);
        VehicleDataOutDTO vehicleDataOutDTO = vehicleService.getVehicle(vehicleID);

        Link selfLink = linkTo(methodOn(VehicleController.class).getVehicleById(strVehicleID)).withSelfRel();

        EntityModel<VehicleDataOutDTO> response = EntityModel.of(vehicleDataOutDTO, selfLink);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

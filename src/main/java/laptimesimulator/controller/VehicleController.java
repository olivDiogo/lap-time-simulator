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

        // Try to get the vehicle parameters from the vehicle data input transfer object
        VehicleParameters vehicleParameters;
        try {
            vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataInDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Try to create the vehicle
        VehicleDataOutDTO vehicleDataOutDTO;
        try {
             vehicleDataOutDTO = vehicleService.createVehicle(vehicleParameters);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Add links to the response
        Link selfLink = linkTo(methodOn(VehicleController.class).createVehicle(vehicleDataInDTO)).withRel("create-vehicle")
                .withTitle("Create a new vehicle")
                .withType("POST");
        Link getLink = linkTo(methodOn(VehicleController.class).getVehicleById(vehicleDataOutDTO.vehicleID)).withRel("get-vehicle")
                .withTitle("Get the created vehicle")
                .withType("GET");

        EntityModel<VehicleDataOutDTO> response = EntityModel.of(vehicleDataOutDTO, selfLink, getLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method to update a vehicle.
     *
     * @param strVehicleID is the ID of the vehicle.
     * @param vehicleDataInDTO is the vehicle data.
     * @return the vehicle data transfer object.
     */
    @PutMapping("/{strVehicleID}")
    public ResponseEntity<EntityModel<VehicleDataOutDTO>> updateVehicle(@PathVariable String strVehicleID, @RequestBody VehicleDataInDTO vehicleDataInDTO) {

        // Try to get the vehicle parameters from the vehicle data input transfer object
        VehicleID vehicleID = new VehicleID(strVehicleID);
        VehicleParameters vehicleParameters;
        try {
            vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataInDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Try to update the vehicle
        VehicleDataOutDTO vehicleDataOutDTO;
        try {
            vehicleDataOutDTO = vehicleService.updateVehicle(vehicleID, vehicleParameters);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Add links to the response
        Link selfLink = linkTo(methodOn(VehicleController.class).updateVehicle(strVehicleID, new VehicleDataInDTO())).withRel("update-vehicle")
                .withTitle("Update the vehicle")
                .withType("PUT");
        Link getLink = linkTo(methodOn(VehicleController.class).getVehicleById(strVehicleID)).withRel("get-vehicle")
                .withTitle("Get the updated vehicle")
                .withType("GET");

        EntityModel<VehicleDataOutDTO> response = EntityModel.of(vehicleDataOutDTO, selfLink, getLink);

        return ResponseEntity.status(HttpStatus.OK).body(response);
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
                        linkTo(methodOn(VehicleController.class).getVehicleById(vehicle.vehicleID)).withRel("get-vehicle")
                                .withTitle("Get the vehicle")
                                .withType("GET")))
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

        VehicleID vehicleID = new VehicleID(strVehicleID);
        VehicleDataOutDTO vehicleDataOutDTO = vehicleService.getVehicle(vehicleID);

        // Add link to update the vehicle
        Link updateLink = linkTo(methodOn(VehicleController.class).updateVehicle(strVehicleID, new VehicleDataInDTO())).withRel("update-vehicle")
                .withTitle("Update the vehicle")
                .withType("PUT");

        EntityModel<VehicleDataOutDTO> response = EntityModel.of(vehicleDataOutDTO, updateLink);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

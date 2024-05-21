package lapTimeSimulator.controller;

import jakarta.validation.Valid;
import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.service.VehicleService;
import lapTimeSimulator.utils.dto.VehicleDTO;
import lapTimeSimulator.utils.vehicleParameters.VehicleParametersUtils;
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
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final IMapper<Vehicle, VehicleDTO> vehicleMapper;

    public VehicleController(VehicleService vehicleService, IMapper<Vehicle, VehicleDTO> vehicleMapper) {
        if (vehicleService == null || vehicleMapper == null) {
            throw new IllegalArgumentException("Vehicle service and mapper cannot be null.");
        }
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    /**
     * Method to create a vehicle.
     *
     * @param vehicleDataDTO is the vehicle data.
     * @return the vehicle data transfer object.
     */
    @PostMapping
    public ResponseEntity<EntityModel<VehicleDTO>> createVehicle(@Valid @RequestBody VehicleDTO vehicleDataDTO) {
        if (vehicleDataDTO == null) {
            throw new IllegalArgumentException("Vehicle DTO cannot be null.");
        }

        VehicleParameters vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataDTO);

        Vehicle vehicle = vehicleService.createVehicle(vehicleParameters);
        VehicleDTO vehicleDTO = vehicleMapper.toDTO(vehicle);

        Link selfLink = linkTo(methodOn(VehicleController.class).createVehicle(vehicleDTO)).withSelfRel();

        EntityModel<VehicleDTO> response = EntityModel.of(vehicleDTO, selfLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

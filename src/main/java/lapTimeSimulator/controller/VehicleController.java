package lapTimeSimulator.controller;

import jakarta.validation.Valid;
import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.service.VehicleService;
import lapTimeSimulator.utils.dto.inputDataDTO.VehicleDataInDTO;
import lapTimeSimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
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
    private final IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper;

    public VehicleController(VehicleService vehicleService, IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper) {
        if (vehicleService == null || vehicleMapper == null) {
            throw new IllegalArgumentException("Vehicle service and mapper cannot be null.");
        }
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    /**
     * Method to create a vehicle.
     *
     * @param vehicleDataInDTO is the vehicle data.
     * @return the vehicle data transfer object.
     */
    @PostMapping
    public ResponseEntity<EntityModel<VehicleDataOutDTO>> createVehicle(@Valid @RequestBody VehicleDataInDTO vehicleDataInDTO) {
        if (vehicleDataInDTO == null) {
            throw new IllegalArgumentException("Vehicle DTO cannot be null.");
        }

        VehicleParameters vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataInDTO);

        Vehicle vehicle = vehicleService.createVehicle(vehicleParameters);
        VehicleDataOutDTO vehicleDTO = vehicleMapper.toDTO(vehicle);

        Link selfLink = linkTo(methodOn(VehicleController.class).createVehicle(vehicleDataInDTO)).withSelfRel();

        EntityModel<VehicleDataOutDTO> response = EntityModel.of(vehicleDTO, selfLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

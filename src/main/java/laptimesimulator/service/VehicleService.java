package laptimesimulator.service;

import laptimesimulator.ddd.IMapper;
import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.domain.valueObject.VehicleParameters;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class VehicleService {
    private IVehicleFactory vehicleFactory;
    private IRepository<VehicleID, Vehicle> vehicleRepository;
    private IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper;

    /**
     * Creates a vehicle and adds it to the repository.
     *
     * @param vehicleParameters The parameters of the vehicle.
     * @return The created vehicle.
     */
    public VehicleDataOutDTO createVehicle(VehicleParameters vehicleParameters) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleParameters);
        vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(vehicle);
    }

    /**
     * Gets all vehicles.
     *
     * @return The list of vehicles.
     */
    public List<VehicleDataOutDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicleMapper.toDTO(vehicles);
    }

    /**
     * Gets a vehicle by its ID.
     *
     * @param vehicleID The ID of the vehicle.
     * @return The vehicle.
     */
    public VehicleDataOutDTO getVehicle(VehicleID vehicleID) {
        Optional<Vehicle> vehicle = vehicleRepository.ofIdentity(vehicleID);

        if (vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        return vehicleMapper.toDTO(vehicle.get());
    }
}

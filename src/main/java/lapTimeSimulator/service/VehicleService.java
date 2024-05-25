package lapTimeSimulator.service;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.valueObject.VehicleParameters;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

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
}

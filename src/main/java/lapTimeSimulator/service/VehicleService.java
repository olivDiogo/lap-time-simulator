package lapTimeSimulator.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.valueObject.VehicleParameters;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class VehicleService {
    private final IVehicleFactory vehicleFactory;
    private final IRepository<VehicleID, Vehicle> vehicleRepository;

    /**
     * Constructs a new VehicleService instance with the specified vehicle factory and repository.
     *
     * @param vehicleFactory The vehicle factory. Must not be null.
     * @param vehicleRepository The vehicle repository. Must not be null.
     */
    public VehicleService(IVehicleFactory vehicleFactory, IRepository<VehicleID, Vehicle> vehicleRepository) {
        if (vehicleFactory == null || vehicleRepository == null) {
            throw new IllegalArgumentException("Vehicle factory and repository cannot be null.");
        }
        this.vehicleFactory = vehicleFactory;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Creates a vehicle and adds it to the repository.
     *
     * @param vehicleParameters The parameters of the vehicle.
     * @return The created vehicle.
     */
    public Vehicle createVehicle(@Valid VehicleParameters vehicleParameters) {
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleParameters);
        vehicleRepository.save(vehicle);
        return vehicle;
    }
}

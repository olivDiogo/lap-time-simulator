package laptimesimulator.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import laptimesimulator.ddd.IMapper;
import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.domain.valueObject.VehicleParameters;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

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

    /**
     * Updates a vehicle.
     *
     * @param vehicleID is the ID of the vehicle.
     * @param vehicleParameters is the parameters of the vehicle.
     * @return the updated vehicle.
     */
//    @Retryable(
//            value = { OptimisticLockingFailureException.class },
//            maxAttempts = 5,
//            backoff = @Backoff(delay = 200)
//    )
    @Transactional
    public VehicleDataOutDTO updateVehicle(VehicleID vehicleID, VehicleParameters vehicleParameters) {
        Optional<Vehicle> vehicle = vehicleRepository.ofIdentity(vehicleID);

        if (vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        Vehicle existingVehicle = vehicle.get();
        updateVehicleFields(existingVehicle, vehicleParameters);

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

        return vehicleMapper.toDTO(updatedVehicle);
    }

    /**
     * Updates the fields of a vehicle.
     * @param vehicle The vehicle to update.
     * @param vehicleParameters The parameters to update the vehicle with.
     */
    private void updateVehicleFields(Vehicle vehicle, VehicleParameters vehicleParameters) {
        vehicle.setVehicleName(vehicleParameters.getVehicleName());
        vehicle.setAeroModel(vehicleParameters.getAeroModel());
        vehicle.setBrakeModel(vehicleParameters.getBrakeModel());
        vehicle.setChassisModel(vehicleParameters.getChassisModel());
        vehicle.setPowertrainModel(vehicleParameters.getPowertrainModel());
        vehicle.setTransmissionModel(vehicleParameters.getTransmissionModel());
        vehicle.setTyreModel(vehicleParameters.getTyreModel());
    }
}

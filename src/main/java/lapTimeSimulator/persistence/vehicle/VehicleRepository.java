package lapTimeSimulator.persistence.vehicle;

import jakarta.validation.constraints.NotNull;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.persistence.assembler.IDataModelAssembler;
import lapTimeSimulator.persistence.dataModel.VehicleDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class VehicleRepository implements IVehicleRepository{
    @NotNull
    IVehicleSpringDataRepository repository;
    @NotNull
    IDataModelAssembler<Vehicle, VehicleDataModel> assembler;

    /**
     * Method to save a vehicle entity.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Vehicle save(Vehicle entity) {
        if(entity == null)
            throw new IllegalArgumentException("The vehicle must be not null.");

        VehicleDataModel dataModel = new VehicleDataModel(entity);
        repository.save(dataModel);

        return entity;
    }

    /**
     * Method to find all vehicle entities.
     *
     * @return a list with all vehicle entities.
     */
    @Override
    public List<Vehicle> findAll() {
        List<VehicleDataModel> listVehicleDataModelSaved = repository.findAll();
        return assembler.toDomain(listVehicleDataModelSaved);
    }

    /**
     * Method to find a vehicle entity by its unique identifier.
     */
    @Override
    public Optional<Vehicle> ofIdentity(VehicleID objectID) {
        Optional<VehicleDataModel> vehicleDataModel = repository.findById(objectID.getId());

        return vehicleDataModel.map(dataModel -> assembler.toDomain(dataModel));
    }

    /**
     * Method to check if a vehicle entity exists by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(VehicleID objectID) {
        return repository.existsById(objectID.getId());
    }
}

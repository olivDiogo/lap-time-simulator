package laptimesimulator.persistence.vehicle;

import laptimesimulator.persistence.dataModel.VehicleDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleSpringDataRepository extends JpaRepository<VehicleDataModel, String>{
}

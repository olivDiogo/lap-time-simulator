package lapTimeSimulator.persistence.vehicle;

import lapTimeSimulator.persistence.dataModel.VehicleDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleSpringDataRepository extends JpaRepository<VehicleDataModel, String>{
}

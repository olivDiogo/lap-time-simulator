package lapTimeSimulator.persistence.vehicle;

import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.vehicle.Vehicle;

public interface IVehicleRepository extends IRepository<VehicleID, Vehicle>{
}

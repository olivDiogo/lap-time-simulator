package laptimesimulator.persistence.vehicle;

import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.domain.vehicle.Vehicle;

public interface IVehicleRepository extends IRepository<VehicleID, Vehicle>{
}

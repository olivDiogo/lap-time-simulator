package lapTimeSimulator.domain.vehicle;

import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.valueObject.VehicleParameters;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory implements IVehicleFactory{
    @Override
    public Vehicle createVehicle(VehicleParameters vehicleParameters) {
        if (vehicleParameters == null) {
            throw new IllegalArgumentException("Vehicle parameters cannot be null.");
        }

        return new Vehicle(vehicleParameters);
    }

    @Override
    public Vehicle createVehicle(VehicleID vehicleID, VehicleParameters vehicleParameters) {
        if (vehicleID == null || vehicleParameters == null) {
            throw new IllegalArgumentException("Vehicle parameters cannot be null.");
        }

        return new Vehicle(vehicleID, vehicleParameters);
    }
}

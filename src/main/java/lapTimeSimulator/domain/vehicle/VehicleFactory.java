package lapTimeSimulator.domain.vehicle;

import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.valueObject.VehicleParameters;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory implements IVehicleFactory{
    @Override
    public Vehicle createVehicle(VehicleParameters vehicleParameters) {
        if (vehicleParameters == null || vehicleParameters.getVehicleName() == null || vehicleParameters.getAeroModel() == null ||
                vehicleParameters.getBrakeModel() == null || vehicleParameters.getChassisModel() == null ||
                vehicleParameters.getPowertrainModel() == null || vehicleParameters.getTransmissionModel() == null ||
                vehicleParameters.getTyreModel() == null) {
            throw new IllegalArgumentException("Vehicle parameters cannot be null.");
        }

        return new Vehicle(vehicleParameters);
    }

    @Override
    public Vehicle createVehicle(VehicleID vehicleID, VehicleParameters vehicleParameters) {
        if (vehicleID == null || vehicleParameters == null || vehicleParameters.getVehicleName() == null || vehicleParameters.getAeroModel() == null ||
                vehicleParameters.getBrakeModel() == null || vehicleParameters.getChassisModel() == null ||
                vehicleParameters.getPowertrainModel() == null || vehicleParameters.getTransmissionModel() == null ||
                vehicleParameters.getTyreModel() == null) {
            throw new IllegalArgumentException("Vehicle parameters cannot be null.");
        }

        return new Vehicle(vehicleID, vehicleParameters);
    }
}

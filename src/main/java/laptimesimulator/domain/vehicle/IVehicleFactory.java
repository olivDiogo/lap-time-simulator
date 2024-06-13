package laptimesimulator.domain.vehicle;

import laptimesimulator.domain.valueObject.*;


public interface IVehicleFactory {

    /**
     * Creates a new vehicle with the corresponding parameters.
     *
     * @param vehicleParameters The parameters of the vehicle. Must not be null.
     * @return The new vehicle instance.
     */
    Vehicle createVehicle(VehicleParameters vehicleParameters);

    /**
     * Creates a new vehicle with the corresponding parameters, including the vehicle ID.
     *
     * @param vehicleID The ID of the vehicle. Must not be null.
     * @param vehicleParameters The parameters of the vehicle. Must not be null.
     * @return The new vehicle instance.
     */
    Vehicle createVehicle(VehicleID vehicleID, VehicleParameters vehicleParameters, long version);

}

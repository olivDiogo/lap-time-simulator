package laptimesimulator.domain.vehicle;

import laptimesimulator.ddd.IAggregateRoot;
import laptimesimulator.domain.valueObject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Vehicle implements IAggregateRoot<VehicleID> {
    private final VehicleID vehicleID;
    private Name vehicleName;
    private AeroModel aeroModel;
    private BrakeModel brakeModel;
    private ChassisModel chassisModel;
    private PowertrainModel powertrainModel;
    private TransmissionModel transmissionModel;
    private TyreModel tyreModel;

    /**
     * Constructor of the class Vehicle
     *
     * @param vehicleParameters is the parameters of the vehicle.
     */
    Vehicle(VehicleParameters vehicleParameters) {
        // Validation of the parameters is done in the factory
        this.vehicleID = new VehicleID(UUID.randomUUID().toString());
        this.powertrainModel = vehicleParameters.getPowertrainModel();
        this.vehicleName = vehicleParameters.getVehicleName();
        this.aeroModel = vehicleParameters.getAeroModel();
        this.brakeModel = vehicleParameters.getBrakeModel();
        this.chassisModel = vehicleParameters.getChassisModel();
        this.transmissionModel = vehicleParameters.getTransmissionModel();
        this.tyreModel = vehicleParameters.getTyreModel();
    }

    /**
     * Constructor of the class Vehicle with a given ID
     *
     * @param vehicleID         is the ID of the vehicle.
     * @param vehicleParameters is the parameters of the vehicle.
     */
    Vehicle(VehicleID vehicleID, VehicleParameters vehicleParameters) {
        // Validation of the parameters is done in the factory
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleParameters.getVehicleName();
        this.aeroModel = vehicleParameters.getAeroModel();
        this.brakeModel = vehicleParameters.getBrakeModel();
        this.chassisModel = vehicleParameters.getChassisModel();
        this.powertrainModel = vehicleParameters.getPowertrainModel();
        this.transmissionModel = vehicleParameters.getTransmissionModel();
        this.tyreModel = vehicleParameters.getTyreModel();
    }
}
